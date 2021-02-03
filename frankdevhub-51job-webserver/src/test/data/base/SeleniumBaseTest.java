package data.base;

import frankdevhub.job.automatic.JobWebAutoService;
import frankdevhub.job.automatic.core.utils.SpringUtils;
import frankdevhub.job.automatic.selenium.DriverBase;
import frankdevhub.job.automatic.web.pages.JobPlatformSearchPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;

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
@SpringBootTest(classes = JobWebAutoService.class)
public class SeleniumBaseTest {

    private WebDriver driver; //浏览器驱动对象
    private static final String SELENIUM_TEST_CACHE_PATH = "C:/Automation/junit-selenium-test"; //本地测试默认配置的浏览器缓存地址

    private JobPlatformSearchPage getJobPlatformSearchPage() {
        return SpringUtils.getBean(JobPlatformSearchPage.class);
    }

    /**
     * 初始化测试驱动对象的配置
     *
     * @throws Exception
     */
    @Before
    public void init() throws Exception {
        Long start = System.currentTimeMillis();
        DriverBase.instantiateDriverObject();
        driver = DriverBase.getDriver(SELENIUM_TEST_CACHE_PATH); //实例化浏览器驱动对象
        Long current = System.currentTimeMillis();
        //测试计量并输出浏览器驱动加载的时间
        log.info(String.format("Chrome Driver instance initialize complete, cost:%s sec", (current - start) / 1000));
    }

    /**
     * 测试环境下进行页面解析
     *
     * @throws InterruptedException
     */
    @Test
    public void testSpringBootEnv() throws InterruptedException {
        //测试页面扫描解析流程
        getJobPlatformSearchPage().startSearchResultPatrol();
    }

}
