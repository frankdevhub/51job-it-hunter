package frankdevhub.job.automatic.web.pages;

import frankdevhub.job.automatic.core.constants.BusinessConstants;
import frankdevhub.job.automatic.core.constants.SeleniumConstants;
import frankdevhub.job.automatic.core.exception.BusinessException;
import frankdevhub.job.automatic.core.parser.SalaryRangeTextUtils;
import frankdevhub.job.automatic.core.utils.SpringUtils;
import frankdevhub.job.automatic.core.utils.WebDriverUtils;
import frankdevhub.job.automatic.entities.JobSearchResult;
import frankdevhub.job.automatic.selenium.AssignDriver;
import frankdevhub.job.automatic.selenium.Query;
import frankdevhub.job.automatic.service.JobSearchResultService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import tk.mybatis.mapper.util.Assert;

import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


//此方法已过期,页面定位方式已失效
@Slf4j
@SuppressWarnings("all")
public class JobPlatformSearchPage extends BaseWebPage {

    private String location; //职位地点
    private Integer pageNum; //第几页
    private Integer pageSize; //每页的大小
    private final String keyword; //搜索关键字
    private final Query searchBox; //搜索框控件对象
    private final Query submitBtn; //查询提交按钮
    private final Query searchResultList; //查询返回结果集列表页面
    private final Query pageNavigator; //页面分页控件栏
    private final Integer CPU_CAPBILITY; //CPU性能

    // private final Lock PAGE_SCOPE_LOCK; //页面锁对象,防止当前页面所有对象没有解析完后直接跳转至下一页致使原获取的对象生命周期结束无法解析

    private ThreadPoolExecutor threadPool; //扫描解析的线程池对象

    private JobSearchResultService getJobSearchResultService() {
        return SpringUtils.getBean(JobSearchResultService.class);
    }

    public JobPlatformSearchPage(Boolean isAutoConfig, String keyword) {
        super(isAutoConfig);
        Assert.notNull(keyword, BusinessConstants.JOB_SEARCH_KEYWORD_NULL); // 搜索关键字不能为空
        this.pageNum = 0; //分页参数:第几页
        this.pageSize = 0; //分页参数:每页大小
        this.keyword = keyword; //搜索关键字
        this.searchBox = new Query().defaultLocator(By.xpath(SeleniumConstants.INPUT_SEARCH_XPATH)); //搜索框控件
        this.submitBtn = new Query().defaultLocator(By.xpath(SeleniumConstants.SUBMIT_SEARCH_XPATH)); //提交按钮控件
        this.searchResultList = new Query().defaultLocator(By.xpath(SeleniumConstants.SEARCH_RESULT_LIST_XPATH)); //搜索返回结果集列表控件
        this.pageNavigator = new Query().defaultLocator(By.xpath(SeleniumConstants.RESULT_PAGE_NAVIGATOR_XPATH)); //页面分页跳转控件
        this.CPU_CAPBILITY = Runtime.getRuntime().availableProcessors();
        this.threadPool = new ThreadPoolExecutor(2 * CPU_CAPBILITY + 1, Integer.MAX_VALUE, 100, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), new CustomizableThreadFactory("jd-extract-pool"),
                new ThreadPoolExecutor.CallerRunsPolicy()); //解析每一个列表页面的线程对象所在的线程池
        //this.PAGE_SCOPE_LOCK = new ReentrantLock();
    }

    /**
     * 初始化页面对象,配置浏览器驱动
     */
    private void initSearchPage() {
        AssignDriver.initQueryObjects(this, (RemoteWebDriver) getDriver());
    }

    /**
     * 输入设置搜索的职位地点
     */
    private void setJobResourceLocation() {
    }

    /**
     * 跳转至首页
     */
    private void navigateToPlatformHomePage() {
        //默认跳转至上海地区
        getDriver().get(BusinessConstants.JOB_PLATFORM_HOMEPAGE);
        WebDriverWait wait = new WebDriverWait(getDriver(), 5, 100);
        WebDriverUtils.doWaitTitleContains(BusinessConstants.JOB_PLATFORM_HOMEPAGE_TITLE_KEY, wait);
        log.info("navigate to www.51job.com success");
    }

    /**
     * 初始化xpath关联控件对象
     *
     * @throws InterruptedException
     */
    private void inputSearchQuery() throws InterruptedException {
        log.info("locate search box element");
        WebElement searchBoxElement = WebDriverUtils.findWebElement(searchBox);
        Thread.sleep(500);
        log.info(String.format("input search keyWord [" + this.keyword + "]"));
        searchBoxElement.sendKeys(this.keyword);
        log.info("input search keyWord complete");
    }

    /**
     * 提交搜索的关键字
     *
     * @throws InterruptedException
     */
    private void submitSearchKeyword() throws InterruptedException {
        log.info("submit search keyword");
        //提交搜索关键字的控件对象
        WebElement submitBtnElement = WebDriverUtils.findWebElement(submitBtn);
        Thread.sleep(500);
        submitBtnElement.click();
        //提交搜索关键字并异步等待新页面加载完
        log.info("wait and navigate to search result list page");
        WebDriverWait wait = new WebDriverWait(getDriver(), 5, 100);
        WebDriverUtils.doWaitTitleContains(this.keyword, wait);
        log.info("navigate to search result list page success");
    }

    @Data
    private class ParseSearchResultRow extends Thread {

        private JobSearchResult result; //扫描的职位信息

        public ParseSearchResultRow(JobSearchResult result) {
            this.result = result;
        }

        /**
         * 解析搜索返回的结果集并入库
         */
        private void parseAndRestore() {
            Assert.notNull(result, "cannot find result");
            //查询校验是否存在已经入库的搜索职位记录
            log.info("markId = {}", result.getMarkId());
            int count = getJobSearchResultService().selectCountByMarkId(result.getMarkId());
            if (count <= 0) {
                //新增未入库的数据
                log.info("do insert result record");
                result.doCreateEntity();
                getJobSearchResultService().insertSelective(result);
            } else {
                //更新未入库的数据与扫描批次信息
                log.info("do update result record");
                result.doUpdateEntity();
                getJobSearchResultService().updateByPrimaryKeySelective(result);
            }
        }

        @Override
        public void run() {
            log.info("submitted restore thread start to run");
            parseAndRestore();
        }
    }

    /**
     * 逐行解析搜索返回的岗位信息
     *
     * @param row 页面列表行对象
     * @return 职位搜索返回的结果集
     * @throws BusinessException,IllegalAccessException
     */
    private JobSearchResult parseSearchResult(WebElement row) throws BusinessException, IllegalAccessException {

        WebElement jobDescriptionElement = row.findElement(By.xpath(SeleniumConstants.RESULT_JD_NAME_XPATH)); //职位描述
        WebElement companyNameElement = row.findElement(By.xpath(SeleniumConstants.RESULT_COMPANY_NAME_XPATH)); //职位所在企业
        WebElement salaryRangeElement = row.findElement(By.xpath(SeleniumConstants.RESULT_SALARY_RANGE_XPATH)); //薪资范围描述
        WebElement publishDateElement = row.findElement(By.xpath(SeleniumConstants.RESULT_JD_PUBLISH_DATE_XPATH));//职位发布日期
        WebElement jobLocationElement = row.findElement(By.xpath(SeleniumConstants.RESULT_JD_LOCATION_XPATH)); //职位地点
        //常用非空字段进行非空校验
        Assert.notNull(jobDescriptionElement, "job description element cannot be found on this row"); //职位描述
        Assert.notNull(companyNameElement, "company name element cannot be found on this row"); //职位所在企业
        Assert.notNull(publishDateElement, "publish date element cannot be found on this row"); //职位发布日期
        Assert.notNull(jobLocationElement, "job location element cannot be found on this row"); //职位地点

        JobSearchResult result = new JobSearchResult(); //构建职位搜索对象
        result.setJobTitle(jobDescriptionElement.getText()) //职位描述信息
                .setLinkUrl(jobDescriptionElement.getAttribute(SeleniumConstants.ATTRIBUTE_HREF)); //职位详情平台链接地址
        //职位薪资范围的描述性字符串
        String salaryRangeText = null == salaryRangeElement.getText() ? "" : salaryRangeElement.getText();
        log.info("salaryRangeText = {}", salaryRangeText);
        //如果描述内容不为空则解析获取薪资范围以及计量单位
        if (StringUtils.isNotEmpty(salaryRangeText.trim())) {
            SalaryRangeTextUtils utils = new SalaryRangeTextUtils(salaryRangeElement.getText());
            utils.parse();
            result.setSalaryNumericUnit(utils.getNumericUnit()) //薪资数值计量单位
                    .setSalaryRangeMin(utils.getMinimizeValue()) //薪资范围的最小值
                    .setSalaryRangeMax(utils.getMaximumValue()) //薪资范围的最大值
                    .setSalaryTimeUnit(utils.getTimeUnit()) //薪资计量的时间单位
                    .setIsDefineByDay(utils.isUnitByDay()) //是否以天为单位计量
                    .setIsDefineByMonth(utils.isUnitByMonth()) //是否以月为单位计量
                    .setIsDefineByYear(utils.isUnitByYear()) //是否以年为单位计量
                    .setIsDefineByK(utils.isUnitByThousand()) //是否以千位数计量
                    .setIsDefineByW(utils.isUnitByTenThousand()); //是否以万位数计量
        }
        result.setSalaryRangeChars(salaryRangeText);
        //判断是否是校招职位
        try {
            row.findElement(By.xpath(SeleniumConstants.RESULT_JD_CAMPUS_ONLY_XPATH));
            Thread.sleep(500L); //异步等待元素被捕获
            result.setIsCampusOnly(true);
        } catch (Exception e) {
            result.setIsCampusOnly(false);
        }
        //判断是内部推荐岗位
        try {
            row.findElement(By.xpath(SeleniumConstants.RESULT_JD_INTERNSHIP_ONLY_XPATH));
            Thread.sleep(500L); //异步等待元素被捕获
            result.setIsInternshipPos(true);
        } catch (Exception e) {
            result.setIsInternshipPos(false);
        }
        //薪资是否可以面议
        result.setIsSalaryNegotiable(false);
        result.setCompanyName(companyNameElement.getAttribute(SeleniumConstants.ATTRIBUTE_TITLE).trim()); //职位所在企业名称
        result.setLocation(jobLocationElement.getText().trim()); //职位地点位置
        String publishDate = publishDateElement.getText().trim(); //职位发布日期
        log.info("publishDate = {}", publishDate);
        //若发布日期存在则进行解析
        if (null != publishDate) {
            //替换中文字符 2020-01-15 发布-> 2020-01-15
            publishDate = publishDate.replaceAll("[\u4E00-\u9FA5]", "");
            int month = Integer.parseInt(publishDate.split("-")[0]);
            int day = Integer.parseInt(publishDate.split("-")[1]);
            result.setPublishDateChar(publishDate) //职位发布日期
                    .setPublishDateDayNumeric(day) //职位发布时间(天)
                    .setPublishDateMonthNumeric(month); //职位发布时间(月)
        }
        result.generateMarkId();//生成新的唯一标识
        return result;
    }

    /**
     * 逐行解析搜索返回的岗位信息
     *
     * @param row 页面列表行对象
     */
    private void parseSearchResults(List<WebElement> rows) {
        Assert.notEmpty(rows, "rows cannot be found");
        for (WebElement row : rows) {
            try {
                JobSearchResult result = parseSearchResult(row);
                Thread t = new ParseSearchResultRow(result);
                threadPool.execute(t);
                log.info("restore result thread submit success");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 逐行解析搜索返回的岗位信息
     */
    private void parseSearchResultPage() {
        log.info("locate search result list");
        //搜索页面返回的结果集列表对象
        List<WebElement> resultList = WebDriverUtils.findWebElements(searchResultList);
        this.pageSize = resultList.size();
        parseSearchResults(resultList);
        log.info("parse current page list complete");
    }

    /**
     * 跳转到结果集页面的下一页
     *
     * @throws BusinessException
     */
    private void turnToNextPage() throws BusinessException {
        log.info("invoke {{JobPlatformSearchPage::nextPage()}}");
        log.info("locate search result page navigator");
        //获取页面分页控件对象
        List<WebElement> navigators = pageNavigator.findWebElements();
        Assert.notEmpty(navigators, "cannot find navigators");
        //校验是否捕获到分页导航控件
        if (null == navigators || navigators.size() == 0)
            throw new RuntimeException("invalid element may be located as search result page navigator");

        //遍历翻页控件校验是否可以进行下一页跳转
        WebElement nextPage = null;
        for (WebElement nav : navigators) {
            String name = nav.getAttribute(SeleniumConstants.ATTRIBUTE_CLASS);
            if (null != name && "next".equals(name))
                nextPage = nav;
        }
        if (null == nextPage)
            throw new BusinessException(BusinessConstants.NEXT_PAGE_NOT_AVAILABLE);
        //校验下一页的跳转链接并跳转
        //点击翻页控件触发翻页
        nextPage.click();
        log.info("navigate to next search result page");
        WebDriverWait wait = new WebDriverWait(getDriver(), 5, 100);
        WebDriverUtils.doWaitTitleContains(this.keyword, wait);
        log.info("navigate to next search result page success");
        //累计分页数累加
        this.pageNum++;
    }

    /**
     * 解析当前搜索结果集列表页面
     */
    private void parseCurrentSearchResultPage() {
        try {
            parseSearchResultPage(); //解析当前页面列表集合
            turnToNextPage(); //跳转至下一页
            parseCurrentSearchResultPage(); //解析跳转后当前视图内的列表页
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * 关键字搜索职位并解析返回的列表页
     *
     * @throws InterruptedException
     */
    public void startSearchResultPatrol() throws InterruptedException {
        initSearchPage(); //初始化页面基类对象
        navigateToPlatformHomePage(); //导航跳转到首页
        inputSearchQuery(); //初始化页面相关的控件
        submitSearchKeyword(); //提交搜索关键字
        parseCurrentSearchResultPage(); //解析搜索返回的结果集列表并入库

        //异步等待线程池执行完毕
        //TODO:异步等待,防止主线程运行完后强制关闭了线程池导致解析数据丢失
    }

}
