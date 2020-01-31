import frankdevhub.job.automatic.core.constants.BusinessConstants;
import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import frankdevhub.job.automatic.selenium.DriverBase;
import frankdevhub.job.automatic.selenium.config.ChromeConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * <p>Title:@ClassName ChromeConfigurationTest.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/1/31 1:11
 * @Version: 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class ChromeConfigurationTest {

    private final Logger LOGGER = LoggerFactory.getLogger(ChromeConfigurationTest.class);
    private static final String SELENIUM_TEST_CACHE_DIRECTORY_NAME = "junit-selenium-test";
    private static final String SELENIUM_TEST_CACHE_PATH = "C:/Automation/junit-selenium-test";

    private WebDriver driver = null;

    private ChromeConfiguration configuration = new ChromeConfiguration();

    private void setSeleniumChromeCacheDirectory() throws IOException {
        configuration.deleteHistorySeleniumBrowserCache()
                .setSeleniumBrowserCache(configuration.DEFAULT_WIN_CHROME_CACHE_PATH, SELENIUM_TEST_CACHE_DIRECTORY_NAME);
    }

    @Test
    public void testChromeDriverNavigateToWebPage() throws Exception {
        Long start = System.currentTimeMillis();

        LOGGER.begin().info("run test method {{testChromeDriverNavigateToWebPage}} start");
        DriverBase.instantiateDriverObject();
        driver = DriverBase.getDriver(SELENIUM_TEST_CACHE_PATH);


        Long current = System.currentTimeMillis();
        System.out.println(String.format("Chrome Driver instance initialize complete, cost:[%f]s", (current - start) / 1000));
        System.out.println("navigate to test web site page");
        driver.get(BusinessConstants.JOB_PLATFORM_HOMEPAGE);

        LOGGER.begin().info("run test method {{testChromeDriverNavigateToWebPage}} complete");
    }


   /* @Test
    public void testSeleniumChromeCacheConfiguration() throws IOException {
        LOGGER.begin().info("run test method {{testSeleniumChromeCacheConfiguration}} start");
        setSeleniumChromeCacheDirectory();
        LOGGER.begin().info("run test method {{testSeleniumChromeCacheConfiguration}} complete");
    }*/

    @Before
    public void init() {
        LOGGER.begin().info("invoke {{ChromeConfigurationTest:: init()}}");

    }

}
