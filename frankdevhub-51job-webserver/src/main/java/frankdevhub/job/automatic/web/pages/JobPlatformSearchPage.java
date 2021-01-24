package frankdevhub.job.automatic.web.pages;

import frankdevhub.job.automatic.core.constants.BusinessConstants;
import frankdevhub.job.automatic.core.constants.SeleniumConstants;
import frankdevhub.job.automatic.core.exception.BusinessException;
import frankdevhub.job.automatic.core.utils.SalaryRangeTextUtils;
import frankdevhub.job.automatic.core.utils.WebDriverUtils;
import frankdevhub.job.automatic.entities.JobSearchResult;
import frankdevhub.job.automatic.selenium.AssignDriver;
import frankdevhub.job.automatic.selenium.Query;
import frankdevhub.job.automatic.service.JobSearchResultService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.util.Assert;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>Title:@ClassName JobPlatformSearchPage.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/4 21:46
 * @Version: 1.0
 */

@Slf4j
@Component
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
    private ExecutorService threadPool; //扫描解析的线程池对象

    @Autowired
    private JobSearchResultService jobSearchResultService;

    public JobPlatformSearchPage(Boolean isAutoConfig, String keyword) {
        super(isAutoConfig);
        Assert.notNull(keyword, BusinessConstants.JOB_SEARCH_KEYWORD_NULL); // 搜索关键字不能为空
        this.pageNum = 0;
        this.pageSize = 0;
        this.keyword = keyword;
        this.searchBox = new Query().defaultLocator(By.xpath(SeleniumConstants.INPUT_SEARCH_XPATH));
        this.submitBtn = new Query().defaultLocator(By.xpath(SeleniumConstants.SUBMIT_SEARCH_XPATH));
        this.searchResultList = new Query().defaultLocator(By.xpath(SeleniumConstants.SEARCH_RESULT_LIST_XPATH));
        this.pageNavigator = new Query().defaultLocator(By.xpath(SeleniumConstants.RESULT_PAGE_NAVIGATOR_XPATH));
        this.threadPool = Executors.newSingleThreadExecutor();
    }

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
        getDriver().get(BusinessConstants.JOB_PLATFORM_HOMEPAGE);
        WebDriverWait wait = new WebDriverWait(getDriver(), 5, 100);
        WebDriverUtils.doWaitTitleContains(BusinessConstants.JOB_PLATFORM_HOMEPAGE_TITLE_KEY, wait);
        log.info("navigate to www.51job.com success");
    }

    private void inputSearchQuery() throws InterruptedException {
        log.info("locate search box element");
        WebElement searchBoxElement = WebDriverUtils.findWebElement(searchBox);
        Thread.sleep(500);

        log.info(String.format("input search keyWord [" + this.keyword + "]"));
        searchBoxElement.sendKeys(this.keyword);
        log.info("input search keyWord complete");
    }

    private void submitSearchKeyword() throws InterruptedException {
        log.info("submit search keyword");

        WebElement submitBtnElement = WebDriverUtils.findWebElement(submitBtn);
        Thread.sleep(500);
        submitBtnElement.click();

        log.info("wait and navigate to search result list page");
        WebDriverWait wait = new WebDriverWait(getDriver(), 5, 100);
        WebDriverUtils.doWaitTitleContains(this.keyword, wait);
        log.info("navigate to search result list page success");
    }

    private class ParseSearchResultRow extends Thread {

        private JobSearchResult result;

        public ParseSearchResultRow(JobSearchResult result) {
            this.result = result;
        }

        private void parseAndRestore() {
            if (null == result)
                return;
            int count = jobSearchResultService.selectCountByMarkId(result.getMarkId());
            if (count <= 0) {
                System.out.println("do insert result record");
                String id = UUID.randomUUID().toString();
                result.setId(id);
                jobSearchResultService.insertSelective(result);
            } else {
                System.out.println("do update result record");
                jobSearchResultService.updateByPrimaryKeySelective(result);
            }
        }

        public JobSearchResult getResult() {
            return result;
        }

        public ParseSearchResultRow setResult(JobSearchResult result) {
            this.result = result;
            return this;
        }


        @Override
        public void run() {
            System.out.println("submitted restore thread start to run");
            parseAndRestore();
        }
    }

    /**
     * 逐行解析搜索返回的岗位信息
     *
     * @param row 页面列表行对象
     * @return 职位搜索返回的结果集
     * @throws BusinessException
     * @throws IllegalAccessException
     */
    private JobSearchResult parseSearchResult(WebElement row) throws BusinessException, IllegalAccessException {
        WebElement jobDescriptionElement = row.findElement(By.xpath(SeleniumConstants.RESULT_JD_NAME_XPATH));
        WebElement companyNameElement = row.findElement(By.xpath(SeleniumConstants.RESULT_COMPANY_NAME_XPATH));
        WebElement salaryRangeElement = row.findElement(By.xpath(SeleniumConstants.RESULT_SALARY_RANGE_XPATH));
        WebElement publishDateElement = row.findElement(By.xpath(SeleniumConstants.RESULT_JD_PUBLISH_DATE_XPATH));
        WebElement jobLocationElement = row.findElement(By.xpath(SeleniumConstants.RESULT_JD_LOCATION_XPATH));

        Assert.notNull(jobDescriptionElement, "job description element cannot be found on this row");
        Assert.notNull(companyNameElement, "company name element cannot be found on this row");
        Assert.notNull(publishDateElement, "publish date element cannot be found on this row");
        Assert.notNull(jobLocationElement, "job location element cannot be found on this row");

        JobSearchResult result = new JobSearchResult();
        result.setJobTitle(jobDescriptionElement.getText())
                .setLinkUrl(jobDescriptionElement.getAttribute(SeleniumConstants.ATTRIBUTE_HREF));
        String salaryRangeText = null == salaryRangeElement.getText() ? "" : salaryRangeElement.getText();

        if (StringUtils.isNotEmpty(salaryRangeText.trim())) {
            SalaryRangeTextUtils utils = new SalaryRangeTextUtils(salaryRangeElement.getText());
            utils.parse();
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
            row.findElement(By.xpath(SeleniumConstants.RESULT_JD_CAMPUS_ONLY_XPATH));
            Thread.sleep(500L);
            result.setIsCampusOnly(true);
        } catch (Exception e) {
            result.setIsCampusOnly(false);
        }
        //判断是内部推荐岗位
        try {
            row.findElement(By.xpath(SeleniumConstants.RESULT_JD_INTERNSHIP_ONLY_XPATH));
            Thread.sleep(500L);
            result.setIsInternshipPos(true);
        } catch (Exception e) {
            result.setIsInternshipPos(false);
        }

        //TODO
        result.setIsSalaryNegotiable(false);
        result.setCompanyName(companyNameElement.getAttribute(SeleniumConstants.ATTRIBUTE_TITLE).trim());
        result.setLocation(jobLocationElement.getText().trim());
        String publishDate = publishDateElement.getText().trim();
        int month = Integer.parseInt(publishDate.split("-")[0]);
        int day = Integer.parseInt(publishDate.split("-")[1]);
        result.setPublishDateChar(publishDate)
                .setPublishDateDayNumeric(day)
                .setPublishDateMonthNumeric(month);

        int markId = result.hashCode();
        result.setMarkId(markId);

        System.out.print(result.toString());

        return result;
    }

    private void parseSearchResults(List<WebElement> rows) {
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

    private void parseSearchResultPage() {
        log.info("locate search result list");
        List<WebElement> resultList = WebDriverUtils.findWebElements(searchResultList);
        this.pageSize = resultList.size();
        parseSearchResults(resultList);
        log.info("parse current page list complete");
    }

    private void turnToNextPage() throws BusinessException {
        log.info("invoke {{JobPlatformSearchPage::nextPage()}}");
        log.info("locate search result page navigator");

        List<WebElement> navigators = pageNavigator.findWebElements();
        if (null == navigators || navigators.size() == 0)
            throw new RuntimeException("invalid element may be located as search result page navigator");

        WebElement nextPageButton = navigators.size() == 1 ? navigators.get(0) : navigators.get(1);
        String nextPageUrl = nextPageButton.getAttribute(SeleniumConstants.ATTRIBUTE_HREF);
        if (null == nextPageUrl)
            throw new BusinessException(BusinessConstants.NEXT_PAGE_NOT_AVAILABLE);

        Assert.notEmpty(nextPageUrl, "next page url should not be null");
        this.getDriver().get(nextPageUrl);
        log.info("navigate to next search result page");
        WebDriverWait wait = new WebDriverWait(getDriver(), 5, 100);
        WebDriverUtils.doWaitTitleContains(this.keyword, wait);

        log.info("navigate to next search result page success");
        this.pageNum++;
    }

    private void parseCurrentSearchResultPage() {
        parseSearchResultPage();
        try {
            turnToNextPage();
            parseCurrentSearchResultPage();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public void startSearchResultPatrol() throws InterruptedException {
        initSearchPage();
        navigateToPlatformHomePage();
        inputSearchQuery();
        submitSearchKeyword();
        parseCurrentSearchResultPage();
    }

}
