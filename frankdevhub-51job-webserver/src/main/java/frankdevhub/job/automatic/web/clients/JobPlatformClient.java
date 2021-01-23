package frankdevhub.job.automatic.web.clients;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import frankdevhub.job.automatic.core.constants.BusinessConstants;
import frankdevhub.job.automatic.core.constants.SeleniumConstants;
import frankdevhub.job.automatic.core.exception.BusinessException;
import frankdevhub.job.automatic.core.generators.snowflake.SnowflakeGenerator;
import frankdevhub.job.automatic.core.utils.SalaryRangeTextUtils;
import frankdevhub.job.automatic.core.utils.SpringUtils;
import frankdevhub.job.automatic.core.utils.WebDriverUtils;
import frankdevhub.job.automatic.entities.JobSearchResult;
import frankdevhub.job.automatic.selenium.DriverBase;
import frankdevhub.job.automatic.selenium.config.ChromeConfiguration;
import frankdevhub.job.automatic.service.JobSearchResultService;
import frankdevhub.job.automatic.service.impl.JobSearchResultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.util.Assert;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title:@ClassName JobPlatformClient.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/23 3:32
 * @Version: 1.0
 */

@Slf4j
@SuppressWarnings("all")
public class JobPlatformClient {

    @Autowired
    private JobSearchResultService jobSearchResultService;

    public Set<Cookie> getPlatformCookie(ChromeConfiguration configuration) throws Exception {
        DriverBase.instantiateDriverObject();

        log.info("using cache path: " + configuration.getSeleniumCacheDirectoryPath());
        WebDriver driver = DriverBase.getDriver(configuration.getSeleniumCacheDirectoryPath());
        driver.get(BusinessConstants.JOB_PLATFORM_HOMEPAGE);
        WebDriverUtils.doWaitTitleContains("招聘", new WebDriverWait(driver, 3));

        log.info("navigate to platform homepage complete");
        log.info("start to get web cookie");
        Long start = System.currentTimeMillis();
        Set<Cookie> cookies = driver.manage().getCookies();
        Long end = System.currentTimeMillis();

        log.info("time cost: " + (end - start) + " ms");
        log.info("cookie properties:");
        for (Cookie c : cookies) {
            log.info("name = " + c.getName());
            log.info("path = " + c.getPath());
            log.info("value = " + c.getValue());
            log.info("\n");
        }
        return cookies;
    }

    private String getPageHtmlText(String url) throws IOException {

        String pageContext = null;
        CloseableHttpClient httpClient = null;

        Long start = System.currentTimeMillis();
        try {
            // httpClient = HttpClientBuilder.create().build();
            httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Content-Type", "text/html; charset=GBK");
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity responseEntity = response.getEntity();
            pageContext = EntityUtils.toString(responseEntity, "GBK");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        Long end = System.currentTimeMillis();
        log.info(String.format("time cost: %s sec", (end - start) / 1000));
        log.info(pageContext);
        return pageContext;
    }

    protected String getPreviousResultPage(String url) {
        String regex = "([0-9]+)(.html?)";
        Matcher matcher = Pattern.compile(regex).matcher(url);
        String index;
        String previous;
        if (matcher.find()) {
            index = matcher.group(1);
            previous = new Integer(Integer.parseInt(index) - 1).toString();
        } else {
            throw new RuntimeException("url regex cannot match page url");
        }

        StringBuffer buffer = new StringBuffer(url);
        buffer.replace(matcher.start(1), matcher.end(1), previous);
        String previousPage = buffer.toString();

        log.info("previous page url = " + previousPage);

        return previousPage;
    }

    protected String getNextResultPage(String url) {
        String regex = "([0-9]+)(.html?)";
        Matcher matcher = Pattern.compile(regex).matcher(url);
        String index;
        String next;

        if (matcher.find()) {
            index = matcher.group(1);
            next = new Integer(Integer.parseInt(index) + 1).toString();
        } else {
            throw new RuntimeException("url regex cannot match page url");
        }

        StringBuffer buffer = new StringBuffer(url);
        buffer.replace(matcher.start(1), matcher.end(1), next);
        String nextPage = buffer.toString();

        log.info("next page url = " + nextPage);

        return nextPage;
    }

    private String getSearchKeyword(String url) {
        String regex = "(.*),([0-9]+),([0-9]+)(.html?)";
        Matcher matcher = Pattern.compile(regex).matcher(url);
        if (matcher.find()) {
            return matcher.group(1);
        } else
            throw new RuntimeException();
    }

    private class JobSearchResultRestoreThread extends Thread {

        private String pageUrl;
        private List<JobSearchResult> results;
        private JobSearchResultService service;

        public JobSearchResultRestoreThread setResults(List<JobSearchResult> results) {
            this.results = results;
            return this;
        }

        public JobSearchResultRestoreThread setRepository(JobSearchResultService service) {
            this.service = service;
            return this;
        }

        protected JobSearchResultRestoreThread(List<JobSearchResult> results, JobSearchResultService service) {
            this.results = results;
            this.service = service;
        }

        protected JobSearchResultRestoreThread(List<JobSearchResult> results, JobSearchResultService service, String pageUrl) {
            this.results = results;
            this.service = service;
            this.pageUrl = pageUrl;
        }

        private void restoreJobSearchResults(List<JobSearchResult> results) {
            if (null == this.service)
                throw new RuntimeException("repository should not be null");

            for (JobSearchResult result : results) {
                try {
                    if (null == result.getMarkId()) {
                        continue;
                    }
                    int markId = result.getMarkId();
                    int count = service.selectCountByMarkId(markId);
                    if (count == 0) {
                        String id = UUID.randomUUID().toString();
                        result.setId(id);
                        service.insertSelective(result);
                    } else {
                        JobSearchResult res = service.selectByMarkId(markId);
                        service.updateByPrimaryKeySelective(res);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void run() {
            log.info("job search result restore thread start");
            if (StringUtils.isNotEmpty(this.pageUrl)) {
                log.info("page url = " + pageUrl);
            }
            if (null == results) {
                return;
            }
            restoreJobSearchResults(results);
        }

    }

    private void restoreJobSearchResult(List<JobSearchResult> results, ExecutorService service) {
        Runnable task = new JobSearchResultRestoreThread(results, jobSearchResultService);
        log.info("submit task to executor service pool");
        service.submit(task);
    }

    private JobSearchResult parseSearchResult(JXNode row, String jobKeyword) throws XpathSyntaxErrorException, BusinessException, IllegalAccessException {

        JXNode jobDescriptionNode = row.sel(SeleniumConstants.RESULT_JD_NAME_XPATH).get(0);
        JXNode companyNameNode = row.sel(SeleniumConstants.RESULT_COMPANY_NAME_XPATH).get(0);
        JXNode salaryRangeNode = row.sel(SeleniumConstants.RESULT_SALARY_RANGE_XPATH).get(0);
        JXNode publishDateNode = row.sel(SeleniumConstants.RESULT_JD_PUBLISH_DATE_XPATH).get(0);
        JXNode jobLocationNode = row.sel(SeleniumConstants.RESULT_JD_LOCATION_XPATH).get(0);

        Assert.notNull(jobDescriptionNode, "job description node cannot be found on this row");
        Assert.notNull(companyNameNode, "company name node cannot be found on this row");
        Assert.notNull(publishDateNode, "publish date node cannot be found on this row");
        Assert.notNull(jobLocationNode, "job location node cannot be found on this row");

        JobSearchResult result = new JobSearchResult();
        result.setJobTitle(jobDescriptionNode.getElement().attr(SeleniumConstants.ATTRIBUTE_TITLE))
                .setLinkUrl(jobDescriptionNode.getElement().attr(SeleniumConstants.ATTRIBUTE_HREF));

        String salaryRangeText = null == salaryRangeNode.getTextVal() ? "" : salaryRangeNode.getTextVal();
        if (StringUtils.isNotEmpty(salaryRangeText.trim())) {
            SalaryRangeTextUtils utils = new SalaryRangeTextUtils(salaryRangeNode.getTextVal());
            utils.parse();
            //职位薪资属性
            result.setSalaryNumericUnit(utils.getNumericUnit())
                    .setSalaryRangeMin(utils.getMinimizeValue())
                    .setSalaryRangeMax(utils.getMaximumValue())
                    .setSalaryTimeUnit(utils.getTimeUnit())
                    .setIsDefineByDay(utils.isUnitByDay())
                    .setIsDefineByMonth(utils.isUnitByMonth())
                    .setIsDefineByYear(utils.isUnitByYear())
                    .setIsDefineByK(utils.isUnitByThousand())
                    .setIsDefineByW(utils.isUnitByTenThousand());

        }
        result.setSalaryRangeChars(salaryRangeText);
        //判断是否是校招职位
        try {
            row.sel(SeleniumConstants.RESULT_JD_CAMPUS_ONLY_XPATH).get(0);
            result.setIsCampusOnly(true);
        } catch (Exception e) {
            result.setIsCampusOnly(false);
        }
        //判断是内部推荐岗位
        try {
            row.sel(SeleniumConstants.RESULT_JD_INTERNSHIP_ONLY_XPATH).get(0);
            result.setIsInternshipPos(true);
        } catch (Exception e) {
            result.setIsInternshipPos(false);
        }
        result.setIsSalaryNegotiable(false);
        result.setCompanyName(companyNameNode.getElement().attr(SeleniumConstants.ATTRIBUTE_TITLE).trim());
        result.setLocation(jobLocationNode.getElement().childNodes().get(0).outerHtml().trim());
        //职位发布日期以及其他属性
        String publishDate = publishDateNode.getElement().childNodes().get(0).outerHtml().trim();
        int month = Integer.parseInt(publishDate.split("-")[0]);
        int day = Integer.parseInt(publishDate.split("-")[1]);
        result.setPublishDateChar(publishDate)
                .setPublishDateDayNumeric(day)
                .setPublishDateMonthNumeric(month);
        //生成hashCode和唯一标识
        int markId = result.hashCode();
        result.setMarkId(markId);
        return result;
    }


    public List<JobSearchResult> getJobSearchResult(String url) throws IOException, XpathSyntaxErrorException {

        String pageContext = getPageHtmlText(url);
        String jobKeyword = getSearchKeyword(url);

        List<JobSearchResult> results = new ArrayList<>();
        Document document = Jsoup.parse(pageContext);
        JXDocument jxDocument = new JXDocument(document);
        List<JXNode> rows = jxDocument.selN(SeleniumConstants.SEARCH_RESULT_LIST_XPATH);
        for (JXNode row : rows) {
            try {
                JobSearchResult result = parseSearchResult(row, jobKeyword);
                results.add(result);
            } catch (BusinessException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        log.info("get search result entity list complete");
        return results;
    }

    public void restorePageJobSearchResult(String url, ExecutorService service) throws IOException, XpathSyntaxErrorException {
        List<JobSearchResult> results = getJobSearchResult(url);
        log.info("results size = " + results.size());
        restoreJobSearchResult(results, service);
    }

}
