package base;

import frankdevhub.job.automatic.selenium.DriverBase;
import frankdevhub.job.automatic.selenium.Query;
import frankdevhub.job.automatic.web.pages.JobPlatformSearchPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    @Autowired
    private static JobPlatformSearchPage jobPlatformSearchPage;

    /**
     * 初始化测试驱动对象的配置
     *
     * @throws Exception
     */
    @Before
    public void init() throws Exception {
        Long start = System.currentTimeMillis();
        DriverBase.instantiateDriverObject();
        driver = DriverBase.getDriver(SELENIUM_TEST_CACHE_PATH);
        Long current = System.currentTimeMillis();
        log.info(String.format("Chrome Driver instance initialize complete, cost:%s sec", (current - start) / 1000));
    }

    /**
     * 测试环境下进行页面解析
     *
     * @throws InterruptedException
     */
    @Test
    public void testSpringBootEnv() throws InterruptedException {
        jobPlatformSearchPage.startSearchResultPatrol();
    }

}
