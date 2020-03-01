package frankdevhub.job.automatic.web.clients;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import frankdevhub.job.automatic.core.constants.BusinessConstants;
import frankdevhub.job.automatic.core.constants.SeleniumConstants;
import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import frankdevhub.job.automatic.core.exception.BusinessException;
import frankdevhub.job.automatic.core.generators.snowflake.SnowflakeGenerator;
import frankdevhub.job.automatic.core.utils.SalaryRangeTextUtils;
import frankdevhub.job.automatic.core.utils.SpringUtils;
import frankdevhub.job.automatic.core.utils.WebDriverUtils;
import frankdevhub.job.automatic.entities.JobSearchResult;
import frankdevhub.job.automatic.repository.JobSearchResultRepository;
import frankdevhub.job.automatic.selenium.DriverBase;
import frankdevhub.job.automatic.selenium.config.ChromeConfiguration;
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
import tk.mybatis.mapper.util.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
public class JobPlatformClient {

    private final Logger LOGGER = LoggerFactory.getLogger(JobPlatformClient.class);

    private final SnowflakeGenerator snowflakeGenerator = new SnowflakeGenerator();

    private JobSearchResultRepository getSearchResultRepository() {
        return SpringUtils.getBean(JobSearchResultRepository.class);
    }

    public Set<Cookie> getPlatformCookie(ChromeConfiguration configuration) throws Exception {
        DriverBase.instantiateDriverObject();

        System.out.println("using cache path: " + configuration.getSeleniumCacheDirectoryPath());
        WebDriver driver = DriverBase.getDriver(configuration.getSeleniumCacheDirectoryPath());
        driver.get(BusinessConstants.JOB_PLATFORM_HOMEPAGE);
        WebDriverUtils.doWaitTitleContains("招聘", new WebDriverWait(driver, 3));

        System.out.println("navigate to platform homepage complete");
        System.out.println("start to get web cookie");
        Long start = System.currentTimeMillis();
        Set<Cookie> cookies = driver.manage().getCookies();
        Long end = System.currentTimeMillis();

        System.out.println("time cost: " + (end - start) + " ms");
        System.out.println("cookie properties:");
        for (Cookie c : cookies) {
            System.out.println("name = " + c.getName());
            System.out.println("path = " + c.getPath());
            System.out.println("value = " + c.getValue());
            System.out.println("\n");
        }
        return cookies;
    }

    private String getPageHtmlText(String url) throws IOException {
        LOGGER.begin().info("invoke {{JobPlatformClient::getPageHtmlText()}}");

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
        System.out.println(String.format("time cost: %s sec", (end - start) / 1000));

        System.out.println(pageContext);


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

        System.out.println("previous page url = " + previousPage);

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

        System.out.println("next page url = " + nextPage);

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
        private JobSearchResultRepository repository;

        public JobSearchResultRestoreThread setResults(List<JobSearchResult> results) {
            this.results = results;
            return this;
        }

        public JobSearchResultRestoreThread setRepository(JobSearchResultRepository repository) {
            this.repository = repository;
            return this;
        }

        protected JobSearchResultRestoreThread(List<JobSearchResult> results, JobSearchResultRepository repository) {
            this.results = results;
            this.repository = repository;
        }

        protected JobSearchResultRestoreThread(List<JobSearchResult> results, JobSearchResultRepository repository, String pageUrl) {
            this.results = results;
            this.repository = repository;
            this.pageUrl = pageUrl;
        }

        private void restoreJobSearchResults(List<JobSearchResult> results) {
            if (null == this.repository)
                throw new RuntimeException("repository should not be null");

            for (JobSearchResult result : results) {
                try {
                    if (null == result.getMarkId())
                        continue;
                    int count = repository.selectCountByMarkId(result);
                    if (count == 0) {
                        //TODO if duplicate id ?
                        Long id = snowflakeGenerator.generateKey();
                        result.setId(id).setKeyId(id);
                        repository.insertSelective(result);
                    } else {
                        JobSearchResult res = repository.selectByMarkId(result.getMarkId());
                        repository.updateByPrimaryKeySelective(res);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void run() {
            System.out.println("job search result restore thread start");
            if (StringUtils.isNotEmpty(this.pageUrl))
                System.out.println("page url = " + pageUrl);
            if (null == results)
                return;
            restoreJobSearchResults(results);
        }

    }

    private void restoreJobSearchResult(List<JobSearchResult> results, ExecutorService service) {
        Runnable task = new JobSearchResultRestoreThread(results, getSearchResultRepository());
        System.out.println("submit task to executor service pool");
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
                .setResourceUrl(jobDescriptionNode.getElement().attr(SeleniumConstants.ATTRIBUTE_HREF))
                .setSearchKeyword(jobKeyword);

        String salaryRangeText = null == salaryRangeNode.getTextVal() ? "" : salaryRangeNode.getTextVal();
        if (StringUtils.isNotEmpty(salaryRangeText.trim())) {
            SalaryRangeTextUtils utils = new SalaryRangeTextUtils(salaryRangeNode.getTextVal());
            utils.parse();
            //set salary range referred properties
            result.setSalaryNumericUnit(utils.getNumericUnit())
                    .setSalaryMinNumeric(utils.getMinimizeValue())
                    .setSalaryMaxNumeric(utils.getMaximumValue())
                    .setSalaryTimeUnit(utils.getTimeUnit())
                    .setIsUnitByDay(utils.isUnitByDay())
                    .setIsUnitByMonth(utils.isUnitByMonth())
                    .setIsUnitByYear(utils.isUnitByYear())
                    .setIsUnitByThousand(utils.isUnitByThousand())
                    .setIsUnitByTenThousand(utils.isUnitByTenThousand());

        }
        result.setSalaryRange(salaryRangeText);

        //set job description campus only, salary negotiable ,internship only referred property
        try {
            row.sel(SeleniumConstants.RESULT_JD_CAMPUS_ONLY_XPATH).get(0);
            result.setIsCampusOnly(true);
        } catch (Exception e) {
            result.setIsCampusOnly(false);
        }

        try {
            row.sel(SeleniumConstants.RESULT_JD_INTERNSHIP_ONLY_XPATH).get(0);
            result.setIsInternshipPosition(true);
        } catch (Exception e) {
            result.setIsInternshipPosition(false);
        }

        //TODO
        result.setSalaryNeedNegotiation(false);

        //set company name referred property
        result.setCompanyName(companyNameNode.getElement().attr(SeleniumConstants.ATTRIBUTE_TITLE).trim());

        //set job location referred property
        result.setLocation(jobLocationNode.getElement().childNodes().get(0).outerHtml().trim());

        //set job publish date referred property
        String publishDate = publishDateNode.getElement().childNodes().get(0).outerHtml().trim();
        int month = Integer.parseInt(publishDate.split("-")[0]);
        int day = Integer.parseInt(publishDate.split("-")[1]);

        result.setPublishDate(publishDate);
        result.setPublishDayOfMonth(day)
                .setPublishMonth(month);

        //set hashcode as mark id
        int markId = result.hashCode();
        result.setMarkId(markId);

        //print result referred properties to console
        System.out.print(result.toString());

        return result;
    }


    public List<JobSearchResult> getJobSearchResult(String url) throws IOException, XpathSyntaxErrorException {
        LOGGER.begin().info("invoke {{JobPlatformClient::getJobSearchResult()}}");

        String pageContext = getPageHtmlText(url);
        String jobKeyword = getSearchKeyword(url);

        System.out.println("get search result page context complete");
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
        System.out.println("get search result entity list complete");
        return results;
    }

    public void restorePageJobSearchResult(String url, ExecutorService service) throws IOException, XpathSyntaxErrorException {
        List<JobSearchResult> results = getJobSearchResult(url);
        System.out.println("results size = " + results.size());
        restoreJobSearchResult(results, service);
    }

}
