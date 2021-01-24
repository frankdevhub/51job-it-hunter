package base;

import frankdevhub.job.automatic.core.utils.WebDriverUtils;
import frankdevhub.job.automatic.selenium.AssignDriver;
import frankdevhub.job.automatic.selenium.DriverBase;
import frankdevhub.job.automatic.selenium.Query;
import lombok.extern.slf4j.Slf4j;
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
 * <p>Title:@ClassName SeleniumBaseTest.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/16 22:42
 * @Version: 1.0
 */

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class SeleniumBaseTest {

    private WebDriver driver;
    private Query elementsList;
    private static final String SELENIUM_TEST_CACHE_PATH = "C:/Automation/junit-selenium-test";
    private static final String TEST_PAGE_URL = "file:///C:/Users/Administrator/Desktop/WebMail%20_%20Powered%20by%20Winmail%20Server.html";

    @Before
    public void init() throws Exception {
        Long start = System.currentTimeMillis();
        DriverBase.instantiateDriverObject();
        driver = DriverBase.getDriver(SELENIUM_TEST_CACHE_PATH);
        Long current = System.currentTimeMillis();
        log.info(String.format("Chrome Driver instance initialize complete, cost:%s sec", (current - start) / 1000));
        log.info("navigate to test web site page");
        driver.get(TEST_PAGE_URL);

        log.info("init query elements start");
        this.elementsList = new Query().defaultLocator(By.xpath("//div[@class='bbit-tree-node-el bbit-tree-node-leaf']"));
        AssignDriver.initQueryObjects(this, (RemoteWebDriver) driver);
        log.info("init query elements complete");
    }

    @Test
    public void testGetElement() throws InterruptedException {
        log.info("run test method {{testGetElement}} start");
        Thread.sleep(2000L);
        driver.switchTo().frame("expressaddress_iframe");
        Thread.sleep(500L);
        List<WebElement> elements = WebDriverUtils.findWebElements(this.elementsList);
        log.info("value :" + elements.size());
        for (WebElement el : elements)
            log.info(el.getAttribute("title"));

        log.info("run test method {{testGetElement}} complete");
    }

    @Test
    public void testSpringBootEnv() {

    }

}
