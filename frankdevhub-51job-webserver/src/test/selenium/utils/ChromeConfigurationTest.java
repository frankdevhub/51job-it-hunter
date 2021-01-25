package selenium.utils;

import frankdevhub.job.automatic.core.constants.BusinessConstants;
import frankdevhub.job.automatic.core.exception.BusinessException;
import frankdevhub.job.automatic.selenium.DriverBase;
import frankdevhub.job.automatic.selenium.config.ChromeConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;

/**
 * <p>Title:@ClassName selenium.utils.ChromeConfigurationTest.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/1/31 1:11
 * @Version: 1.0
 */

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class ChromeConfigurationTest {

    private static final String SELENIUM_TEST_CACHE_DIRECTORY_NAME = "junit-selenium-test"; //本地的浏览器缓存文件名
    private static final String SELENIUM_TEST_CACHE_PATH = "C:/Automation/junit-selenium-test"; //本地浏览器驱动缓存路径
    private static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + File.separator + "chromedriver.exe"; //本地客户端的浏览器驱动路径

    private WebDriver driver = null; //浏览器驱动对象

    private ChromeConfiguration configuration = ChromeConfiguration.newInstance(false); //浏览器驱动配置对象

    private void setSeleniumChromeCacheDirectory() throws IOException, BusinessException {
        configuration.deleteHistorySeleniumBrowserCache()
                .setSeleniumBrowserCache(configuration.DEFAULT_WIN_CHROME_CACHE_PATH, SELENIUM_TEST_CACHE_DIRECTORY_NAME);
    }

    @Test
    public void testGetCacheDirectoryLockedStatus() {
        log.info("run test method {{testGetCacheDirectoryLockedStatus}} start");
        configuration.setSeleniumBrowserCacheRoot(ChromeConfiguration.DEFAULT_WIN_SELENIUM_CACHE_ROOT)
                .setSeleniumCacheFileName(SELENIUM_TEST_CACHE_DIRECTORY_NAME)
                .setWebDriverPath(CHROME_DRIVER_PATH);

        Long start = System.currentTimeMillis();
        Boolean lock = configuration.getCacheDirectoryLockedStatus();
        Long end = System.currentTimeMillis();

        System.out.println(String.format("cache directory lock status = %s, cost: %s sec", lock.toString(), (end - start) / 1000));
        log.info("run test method {{testGetCacheDirectoryLockedStatus}} complete");
    }

    @Test
    public void testChromeDriverNavigateToWebPage() throws Exception {
        Long start = System.currentTimeMillis();

        log.info("run test method {{testChromeDriverNavigateToWebPage}} start");
        DriverBase.instantiateDriverObject();
        driver = DriverBase.getDriver(SELENIUM_TEST_CACHE_PATH);

        Long current = System.currentTimeMillis();
        System.out.println(String.format("Chrome Driver instance initialize complete, cost:%s sec", (current - start) / 1000));
        System.out.println("navigate to test web site page");
        driver.get(BusinessConstants.JOB_PLATFORM_HOMEPAGE);

        log.info("run test method {{testChromeDriverNavigateToWebPage}} complete");
    }


    @Test
    public void testSeleniumChromeCacheConfiguration() throws IOException, BusinessException {
        log.info("run test method {{testSeleniumChromeCacheConfiguration}} start");
        setSeleniumChromeCacheDirectory();
        log.info("run test method {{testSeleniumChromeCacheConfiguration}} complete");
    }

}
