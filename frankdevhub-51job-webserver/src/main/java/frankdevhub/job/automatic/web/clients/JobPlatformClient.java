package frankdevhub.job.automatic.web.clients;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import frankdevhub.job.automatic.core.constants.BusinessConstants;
import frankdevhub.job.automatic.core.constants.SeleniumConstants;
import frankdevhub.job.automatic.core.exception.BusinessException;
import frankdevhub.job.automatic.core.parser.SalaryRangeTextUtils;
import frankdevhub.job.automatic.core.utils.SpringUtils;
import frankdevhub.job.automatic.core.utils.WebDriverUtils;
import frankdevhub.job.automatic.entities.JobSearchResult;
import frankdevhub.job.automatic.selenium.DriverBase;
import frankdevhub.job.automatic.selenium.config.ChromeConfiguration;
import frankdevhub.job.automatic.service.JobSearchResultService;
import lombok.Data;
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
import tk.mybatis.mapper.util.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
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

    //源码window.__SEARCH_RESULT__=句柄处开始
    private final String DATA_JSON_REGEX = "window.__SEARCH_RESULT__\\s?=\\s?(?<context>\\{.*\\})</script>";

    private JobSearchResultService getJobSearchResultService() {
        return SpringUtils.getBean(JobSearchResultService.class);
    }

    /**
     * 获取缓存的会话信息,直接登录
     *
     * @param configuration Chrome浏览器配置对象
     * @return 客户端浏览器会话对象
     * @throws Exception
     */
    public Set<Cookie> getPlatformCookie(ChromeConfiguration configuration) throws Exception {
        DriverBase.instantiateDriverObject();

        log.info("using cache path: " + configuration.getSeleniumCacheDirectoryPath());
        WebDriver driver = DriverBase.getDriver(configuration.getSeleniumCacheDirectoryPath());
        driver.get(BusinessConstants.JOB_PLATFORM_HOMEPAGE_SH);
        WebDriverUtils.doWaitTitleContains(BusinessConstants.JOB_PLATFORM_HOMEPAGE_TITLE_KEY_1, new WebDriverWait(driver, 3));
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

    /**
     * 获取页面对象的字符串
     *
     * @param url 页面链接地址
     * @return 页面对象的字符串
     * @throws IOException
     */
    private String getPageHtmlText(String url) throws IOException {
        Assert.notNull(url, "url cannot be found");
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

    /**
     * 获取上一页页面对象的字符串
     *
     * @param url 页面链接地址
     * @return 页面对象的字符串
     * @throws IOException
     */
    public String getPreviousResultPage(String url) {
        String regex = "([0-9]+)(.html?)"; //匹配网页元素对象
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

    /**
     * 获取下一页页面对象的字符串
     *
     * @param url 页面链接地址
     * @return 页面对象的字符串
     */
    public String getNextResultPage(String url) {
        String regex = "([0-9]+)(.html?)"; //匹配网页元素对象
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


    /**
     * 获取页面对象的字符串
     *
     * @param url 页面链接地址
     * @return 页面对象的字符串
     */
    public String getSearchKeyword(String url) {
        String regex = "(.*),([0-9]+),([0-9]+)(.html?)";
        Matcher matcher = Pattern.compile(regex).matcher(url);
        if (matcher.find()) {
            return matcher.group(1);
        } else
            throw new RuntimeException();
    }

    @Data
    private class JobSearchResultRestoreThread extends Thread {

        private String pageUrl;  //列表页面的链接地址
        private List<JobSearchResult> results; //返回的搜索集合对象
        private JobSearchResultService service; //解析返回的搜索集合的服务

        /**
         * 构建页面返回的结果集对象
         *
         * @param results 页面返回的结果集对象
         * @return 页面扫描的线程对象
         */
        public JobSearchResultRestoreThread setResults(List<JobSearchResult> results) {
            this.results = results;
            return this;
        }

        /**
         * 构建页面返回的结果集对象
         *
         * @param service 页面返回的结果集存储服务
         * @return 页面扫描的线程对象
         */
        protected JobSearchResultRestoreThread setRepository(JobSearchResultService service) {
            this.service = service;
            return this;
        }

        /**
         * 构建页面返回的结果集对象
         *
         * @param results 页面返回的结果集对象
         * @param service 页面返回的结果集存储服务
         * @return 页面扫描的线程对象
         */
        protected JobSearchResultRestoreThread(List<JobSearchResult> results, JobSearchResultService service) {
            this.results = results;
            this.service = service;
        }

        /**
         * 构建页面返回的结果集对象
         *
         * @param results 页面返回的结果集对象
         * @param service 页面返回的结果集存储服务
         * @param pageUrl 页面链接地址
         * @return 页面扫描的线程对象
         */
        protected JobSearchResultRestoreThread(List<JobSearchResult> results, JobSearchResultService service, String pageUrl) {
            this.results = results;
            this.service = service;
            this.pageUrl = pageUrl;
        }

        private void restoreJobSearchResults(List<JobSearchResult> results) {
            if (null == this.service) {
                throw new RuntimeException("repository should not be null");
            }
            for (JobSearchResult result : results) {
                try {
                    if (null == result.getMarkId()) //如果唯一标识为空则跳过
                        continue;
                    int markId = result.getMarkId(); //生成搜索结果集的唯一标识
                    int count = service.selectCountByMarkId(markId);
                    if (count == 0) {
                        String id = UUID.randomUUID().toString();
                        result.setId(id);
                        result.doCreateEntity();
                        service.insertSelective(result);
                    } else {
                        JobSearchResult res = service.selectByMarkId(markId); //查询校验是否已有入库的结果集
                        result.doUpdateEntity();
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

    /**
     * 解析并存储返回的结果集对象
     *
     * @param results 页面返回的结果集对象
     * @param service 页面返回的结果集存储服务
     */
    private void restoreJobSearchResult(List<JobSearchResult> results, ExecutorService service) {
        Runnable task = new JobSearchResultRestoreThread(results, getJobSearchResultService());
        log.info("submit task to executor service pool");
        service.submit(task);
    }

    /**
     * 解析结果集对象
     *
     * @param row     页面行对象
     * @param keyword 搜索关键字
     * @return 页面搜索结果集对象
     * @throws XpathSyntaxErrorException,BusinessException,IllegalAccessException
     */
    private JobSearchResult parseSearchResult(JXNode row, String keyword) throws XpathSyntaxErrorException, BusinessException, IllegalAccessException {
        log.info("parseSearchResult start");
        JXNode jobDescriptionNode = row.sel(SeleniumConstants.RESULT_JD_NAME_XPATH).get(0); //职位信息描述
        JXNode companyNameNode = row.sel(SeleniumConstants.RESULT_COMPANY_NAME_XPATH).get(0); //公司名称
        JXNode salaryRangeNode = row.sel(SeleniumConstants.RESULT_SALARY_RANGE_XPATH).get(0); //薪资范围
        JXNode publishDateNode = row.sel(SeleniumConstants.RESULT_JD_PUBLISH_DATE_XPATH).get(0); //职位发布日期
        JXNode jobLocationNode = row.sel(SeleniumConstants.RESULT_JD_LOCATION_XPATH).get(0); //职位地点信息

        Assert.notNull(jobDescriptionNode, "job description node cannot be found on this row"); //校验职位信息描述
        Assert.notNull(companyNameNode, "company name node cannot be found on this row"); //校验职位所在公司名称不能为空
        Assert.notNull(publishDateNode, "publish date node cannot be found on this row"); //校验职位发布日期不能为空
        Assert.notNull(jobLocationNode, "job location node cannot be found on this row"); //校验职位地点不能为空

        JobSearchResult result = new JobSearchResult();

        result.setJobTitle(jobDescriptionNode.getElement().attr(SeleniumConstants.ATTRIBUTE_TITLE)) //职位名称
                .setLinkUrl(jobDescriptionNode.getElement().attr(SeleniumConstants.ATTRIBUTE_HREF)); //职位描述的详情链接

        String salaryRangeText = null == salaryRangeNode.getTextVal() ? "" : salaryRangeNode.getTextVal();
        result.setSalaryRangeChars(salaryRangeText);
        log.info("salaryRangeText = {}", salaryRangeText);

        if (StringUtils.isNotEmpty(salaryRangeText.trim())) {
            SalaryRangeTextUtils utils = new SalaryRangeTextUtils(salaryRangeNode.getTextVal()); //解析薪资范围描述
            utils.parse();
            result.setSalaryNumericUnit(utils.getNumericUnit()) //薪资数值单位
                    .setSalaryRangeMin(utils.getMinimizeValue()) //薪资范围的最小值
                    .setSalaryRangeMax(utils.getMaximumValue()) //薪资范围的最大值
                    .setSalaryTimeUnit(utils.getTimeUnit()) //薪资的计量时间
                    .setIsDefineByDay(utils.isUnitByDay()) //薪资是否以天为单位进行计量
                    .setIsDefineByMonth(utils.isUnitByMonth()) //薪资是否以月为单位进行计量
                    .setIsDefineByYear(utils.isUnitByYear()) //薪资是否以年为单位进行计量
                    .setIsDefineByK(utils.isUnitByThousand()) //薪资是否以千位数进行计量
                    .setIsDefineByW(utils.isUnitByTenThousand()); //薪资是否以万位数进行计量
        }
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
        result.setIsSalaryNegotiable(false); //薪资是否可商议
        result.setCompanyName(companyNameNode.getElement().attr(SeleniumConstants.ATTRIBUTE_TITLE).trim());
        result.setLocation(jobLocationNode.getElement().childNodes().get(0).outerHtml().trim());
        //职位发布日期以及其他属性
        String publishDate = publishDateNode.getElement().childNodes().get(0).outerHtml().trim();
        //替换中文字符 2020-01-15 发布-> 2020-01-15
        publishDate = publishDate.replaceAll("[\u4E00-\u9FA5]+", ""); //过滤替换中文字符例如"发布"
        int month = Integer.parseInt(publishDate.split("-")[0]); //职位发布日期(月)
        int day = Integer.parseInt(publishDate.split("-")[1]);//职位发布日期(天)
        result.setPublishDateChar(publishDate) //职位发布日期(字符串)
                .setPublishDateDayNumeric(day) //职位发布日期(天)
                .setPublishDateMonthNumeric(month); //职位发布日期(月)
        result.generateMarkId(); //生成hashCode和唯一标识
        log.info("result markId  = {}", result.getMarkId());
        return result;
    }

    /**
     * 解析结果集对象
     *
     * @param url 页面链接地址(含有关键字)
     * @return 页面搜索结果集对象
     * @throws XpathSyntaxErrorException,BusinessException,IllegalAccessException
     */
    public List<JobSearchResult> getJobSearchResult(String url) throws IOException, XpathSyntaxErrorException {
        String pageContext = getPageHtmlText(url); //获取页面DOM对象的字符串格式

        Assert.notNull(pageContext, "pageContext cannot be found");
        String keyword = getSearchKeyword(url); //从链接中提取关键字
        Assert.notNull(keyword, "keyword cannot be found");
        log.info("keyword  = {}", keyword);

        //解析后的结合
        List<JobSearchResult> results = new ArrayList<>();
        Document document = Jsoup.parse(pageContext);
        //转为DOM文档对象进行解析
        JXDocument jxDocument = new JXDocument(document);
        //定位职位搜索返回的结果集列表
        List<JXNode> rows = jxDocument.selN(SeleniumConstants.SEARCH_RESULT_LIST_XPATH);
        log.info("rows.size  = {}", rows.size());
        //如果jsoup返回的报文可以获取列表信息
        if (null != rows && rows.size() > 5) {
            //逐行解析返回的职位信息
            for (JXNode row : rows) {
                try {
                    JobSearchResult result = parseSearchResult(row, keyword); //解析职位信息对象
                    results.add(result);
                } catch (BusinessException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } else {
            //如果不能获取则依据规则匹配页面加载js中的json对象
            //过滤去除空格换行符
            String ctx = pageContext.replaceAll("\\n", "");
            Pattern p = Pattern.compile(DATA_JSON_REGEX);
            Matcher m = p.matcher(ctx);
            String j = null;
            if (m.find()) {
                j = m.group("context");
            }
            Assert.notNull(j, "data json cannot be found");
            log.info(j);
            //解析返回的json对象
            JobSearchResult result = parseJsonData(j);
        }

        log.info("get search result entity list complete");
        return results;
    }

    /**
     * 解析结果集对象
     *
     * @param url 平台网页源码加载时的json字符串
     * @return 页面搜索结果集对象
     */
    private JobSearchResult parseJsonData(String json) {
        JobSearchResult result = new JobSearchResult();
        //TODO
        return result;
    }

    /**
     * 解析结果集对象
     *
     * @param url     页面链接地址
     * @param service 线程池对象
     * @return 页面搜索结果集对象
     * @throws XpathSyntaxErrorException,BusinessException,IllegalAccessException
     */
    public void restorePageJobSearchResult(String url, ExecutorService service) throws IOException, XpathSyntaxErrorException {
        List<JobSearchResult> results = getJobSearchResult(url);
        log.info("results size = " + results.size());
        //子线程解析获取的搜索结果集对象并存储
        restoreJobSearchResult(results, service);
    }

}
