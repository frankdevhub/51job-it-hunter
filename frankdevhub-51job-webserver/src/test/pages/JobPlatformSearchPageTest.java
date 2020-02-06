package pages;

import frankdevhub.job.automatic.core.constants.SeleniumConstants;
import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import frankdevhub.job.automatic.core.utils.WebDriverUtils;
import frankdevhub.job.automatic.selenium.AssignDriver;
import frankdevhub.job.automatic.selenium.DriverBase;
import frankdevhub.job.automatic.selenium.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * <p>Title:@ClassName JobPlatformSearchPageTest.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/6 21:48
 * @Version: 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class JobPlatformSearchPageTest {

    private static final String TEST_RESULT_PAGE_URL = "https://search.51job.com/list/020000,000000,0000,00,9,99,%2520,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
    private static final String SELENIUM_TEST_CACHE_PATH = "C:/Automation/junit-selenium-test";

    private WebDriver driver;
    private Query searchResultList;

    private final Logger LOGGER = LoggerFactory.getLogger(JobPlatformSearchPageTest.class);

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

    @Before
    public void init() throws Exception {
        LOGGER.begin().info("invoke {{JobPlatformSearchPageTest::testParseResultPage()}}");
        Long start = System.currentTimeMillis();
        DriverBase.instantiateDriverObject();
        driver = DriverBase.getDriver(SELENIUM_TEST_CACHE_PATH);

        Long current = System.currentTimeMillis();
        System.out.println(String.format("Chrome Driver instance initialize complete, cost:%s sec", (current - start) / 1000));
        System.out.println("navigate to test web site page");
        driver.get(TEST_RESULT_PAGE_URL);

        System.out.println("init query elements start");
        searchResultList = new Query().defaultLocator(By.xpath(SeleniumConstants.SEARCH_RESULT_LIST_XPATH));

        AssignDriver.initQueryObjects(this, (RemoteWebDriver) driver);
        System.out.println("init query elements complete");
    }


    @Test
    public void testParseResultPage() {
        LOGGER.begin().info("run test method {{testParseResultPage}} start");


        LOGGER.begin().info("run test method {{testParseResultPage}} complete");
    }
}
