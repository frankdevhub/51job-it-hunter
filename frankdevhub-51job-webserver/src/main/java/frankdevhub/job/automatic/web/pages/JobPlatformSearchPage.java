package frankdevhub.job.automatic.web.pages;

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
import frankdevhub.job.automatic.selenium.AssignDriver;
import frankdevhub.job.automatic.selenium.Query;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import tk.mybatis.mapper.util.Assert;

import java.util.List;
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
public class JobPlatformSearchPage extends BaseWebPage {

    private String location;
    private Integer pageNum;
    private Integer pageSize;
    private final String jobKeyword;
    private final Query searchBox;
    private final Query submitBtn;
    private final Query searchResultList;

    private final SnowflakeGenerator snowflakeGenerator;

    private ExecutorService threadPool;

    private final Logger LOGGER = LoggerFactory.getLogger(JobPlatformSearchPage.class);

    private JobSearchResultRepository getSearchResultRepository() {
        return SpringUtils.getBean(JobSearchResultRepository.class);
    }

    public JobPlatformSearchPage(Boolean isAutoConfig, String jobKeyword) {
        super(isAutoConfig);

        this.pageNum = 0;
        this.pageSize = 0;

        Assert.notNull(jobKeyword, BusinessConstants.JOB_SEARCH_KEYWORD_NULL);
        this.jobKeyword = jobKeyword;
        this.searchBox = new Query().defaultLocator(By.xpath(SeleniumConstants.INPUT_SEARCH_XPATH));
        this.submitBtn = new Query().defaultLocator(By.xpath(SeleniumConstants.SUBMIT_SEARCH_XPATH));
        this.searchResultList = new Query().defaultLocator(By.xpath(SeleniumConstants.SEARCH_RESULT_LIST_XPATH));

        this.snowflakeGenerator = new SnowflakeGenerator();

        this.threadPool = Executors.newSingleThreadExecutor();
    }

    private void initSearchPage() {
        LOGGER.begin().info("invoke {{JobPlatformSearchPage::initSearchPage()}}");
        AssignDriver.initQueryObjects(this, (RemoteWebDriver) getDriver());
    }

    private void setJobResourceLocation() {
        LOGGER.begin().info("invoke {{JobPlatformSearchPage::setJobResourceLocation()}}");
    }

    private void navigateToPlatformHomePage() {
        LOGGER.begin().info("invoke {{JobPlatformSearchPage::navigateToPlatformHomePage()}}");

        getDriver().get(BusinessConstants.JOB_PLATFORM_HOMEPAGE);
        WebDriverWait wait = new WebDriverWait(getDriver(), 5, 100);

        WebDriverUtils.doWaitTitleContains(BusinessConstants.JOB_PLATFORM_HOMEPAGE_TITLE_KEY, wait);

        LOGGER.begin().info("navigate to www.51job.com success");
    }

    private void inputSearchQuery() throws InterruptedException {

        LOGGER.begin().info("locate search box element");
        WebElement searchBoxElement = WebDriverUtils.findWebElement(searchBox);
        Thread.sleep(500);

        LOGGER.begin().info(String.format("input search keyWord [" + this.jobKeyword + "]"));
        searchBoxElement.sendKeys(this.jobKeyword);
        LOGGER.begin().info("input search keyWord complete");
    }

    private void submitSearchKeyword() throws InterruptedException {

        LOGGER.begin().info("invoke {{JobPlatformSearchPage::submitSearchKeyword()}}");
        LOGGER.begin().info("submit search keyword");

        WebElement submitBtnElement = WebDriverUtils.findWebElement(submitBtn);
        Thread.sleep(500);
        submitBtnElement.click();

        LOGGER.begin().info("wait and navigate to search result list page");
        WebDriverWait wait = new WebDriverWait(getDriver(), 5, 100);
        WebDriverUtils.doWaitTitleContains(jobKeyword, wait);

        LOGGER.begin().info("navigate to search result list page success");
    }

    private class ParseSearchResultRow extends Thread {

        private JobSearchResult result;

        public ParseSearchResultRow(JobSearchResult result) {
            this.result = result;
        }

        private void parseAndRestore() {
            if (null == result)
                return;

            JobSearchResultRepository repository = getSearchResultRepository();
            int count = repository.selectCountByMarkId(result);
            if (count <= 0) {
                System.out.println("do insert result record");

                Long id = snowflakeGenerator.generateKey();
                result.setId(id).setKeyId(id);
                repository.insertSelective(result);
            } else {
                System.out.println("do update result record");
                repository.updateByPrimaryKeySelective(result);
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

    private JobSearchResult parseSearchResult(WebElement row) throws BusinessException, IllegalAccessException, InterruptedException {
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
                .setResourceUrl(jobDescriptionElement.getAttribute(SeleniumConstants.ATTRIBUTE_HREF))
                .setSearchKeyword(this.jobKeyword);

        String salaryRangeText = null == salaryRangeElement.getText() ? "" : salaryRangeElement.getText();
        if (StringUtils.isNotEmpty(salaryRangeText.trim())) {
            SalaryRangeTextUtils utils = new SalaryRangeTextUtils(salaryRangeElement.getText());
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
            row.findElement(By.xpath(SeleniumConstants.RESULT_JD_CAMPUS_ONLY_XPATH));
            Thread.sleep(500L);
            result.setIsCampusOnly(true);
        } catch (Exception e) {
            result.setIsCampusOnly(false);
        }

        try {
            row.findElement(By.xpath(SeleniumConstants.RESULT_JD_INTERNSHIP_ONLY_XPATH));
            Thread.sleep(500L);
            result.setIsInternshipPosition(true);
        } catch (Exception e) {
            result.setIsInternshipPosition(false);
        }

        //TODO
        result.setSalaryNeedNegotiation(false);

        //set company name referred property
        result.setCompanyName(companyNameElement.getAttribute(SeleniumConstants.ATTRIBUTE_TITLE).trim());

        //set job location referred property
        result.setLocation(jobLocationElement.getText().trim());

        //set job publish date referred property
        String publishDate = publishDateElement.getText().trim();
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

    private void parseSearchResults(List<WebElement> rows) throws IllegalAccessException, BusinessException, InterruptedException {
        for (WebElement row : rows) {
            JobSearchResult result = parseSearchResult(row);
            Thread t = new ParseSearchResultRow(result);
            //TODO
            threadPool.execute(t);
            LOGGER.begin().info("restore result thread submit success");
        }
    }

    private void parseSearchResultPage() throws BusinessException, IllegalAccessException, InterruptedException {
        LOGGER.begin().info("invoke {{JobPlatformSearchPage::parseSearchResult()}}");
        LOGGER.begin().info("locate search result list");

        List<WebElement> resultList = WebDriverUtils.findWebElements(searchResultList);
        parseSearchResults(resultList);

        LOGGER.begin().info("parse current page list complete");
    }

    private void nextPage() {
        LOGGER.begin().info("invoke {{JobPlatformSearchPage::nextPage()}}");
    }

    public void startSearchResultPatrol() throws InterruptedException, IllegalAccessException, BusinessException {
        LOGGER.begin().info("invoke {{JobPlatformSearchPage::startSearchResultPatrol()}}");

        initSearchPage();
        navigateToPlatformHomePage();
        inputSearchQuery();
        submitSearchKeyword();

        parseSearchResultPage();
    }

}
