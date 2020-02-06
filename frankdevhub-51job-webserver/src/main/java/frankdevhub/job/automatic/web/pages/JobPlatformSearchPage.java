package frankdevhub.job.automatic.web.pages;

import frankdevhub.job.automatic.core.constants.BusinessConstants;
import frankdevhub.job.automatic.core.constants.SeleniumConstants;
import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import frankdevhub.job.automatic.core.utils.SpringUtils;
import frankdevhub.job.automatic.core.utils.WebDriverUtils;
import frankdevhub.job.automatic.repository.JobSearchResultRepository;
import frankdevhub.job.automatic.selenium.AssignDriver;
import frankdevhub.job.automatic.selenium.Query;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import tk.mybatis.mapper.util.Assert;

import java.util.List;

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
    private final String jobKeyword;
    private final Query searchBox;
    private final Query submitBtn;
    private final Query searchResultList;

    private final Logger LOGGER = LoggerFactory.getLogger(JobPlatformSearchPage.class);

    private JobSearchResultRepository getSearchResultRepository() {
        return SpringUtils.getBean(JobSearchResultRepository.class);
    }

    public JobPlatformSearchPage(Boolean isAutoConfig, String jobKeyword) {
        super(isAutoConfig);

        Assert.notNull(jobKeyword, BusinessConstants.JOB_SEARCH_KEYWORD_NULL);
        this.jobKeyword = jobKeyword;
        this.searchBox = new Query().defaultLocator(By.xpath(SeleniumConstants.INPUT_SEARCH_XPATH));
        this.submitBtn = new Query().defaultLocator(By.xpath(SeleniumConstants.SUBMIT_SEARCH_XPATH));
        this.searchResultList = new Query().defaultLocator(By.xpath(SeleniumConstants.SEARCH_RESULT_LIST_XPATH));
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

        WebDriverUtils.doWaitTitle(BusinessConstants.JOB_PLATFORM_HOMEPAGE_TITLE_KEY, wait);

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
        WebDriverUtils.doWaitTitle(jobKeyword, wait);

        LOGGER.begin().info("navigate to search result list page success");
    }

    private void parseSearchResult(WebElement element) {
        parseSearchResult(element);
    }

    private void parseSearchResults(List<WebElement> elements) {

    }

    private void parseSearchResultPage() {
        LOGGER.begin().info("invoke {{JobPlatformSearchPage::parseSearchResult()}}");
        LOGGER.begin().info("locate search result list");

        List<WebElement> resultList = WebDriverUtils.findWebElements(searchResultList);
        for (WebElement element : resultList)
            parseSearchResult(element);

        LOGGER.begin().info("parse current page list complete");
    }

    private void nextPage() {
        LOGGER.begin().info("invoke {{JobPlatformSearchPage::nextPage()}}");
    }

    public void startSearchResultPatrol() {
        LOGGER.begin().info("invoke {{JobPlatformSearchPage::startSearchResultPatrol()}}");
    }

}
