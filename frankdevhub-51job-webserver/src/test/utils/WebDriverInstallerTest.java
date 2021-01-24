package utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>Title:@ClassName WebDriverInstallerTest.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/13 5:17
 * @Version: 1.0
 */

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class WebDriverInstallerTest {

    @Test
    public void testGetOperatingSystem() {
        log.info("runt test method {{testGetOperatingSystem}} start");
        String info = System.getProperty("os.name").toUpperCase();
        System.out.println("System.getProperty(\"os.name\") = " + info);
        log.info("runt test method {{testGetOperatingSystem}} complete");
    }

    @Test
    public void testGetSystemArchitecture() {
        log.info("runt test method {{testGetSystemArchitecture}} start");
        String info = System.getProperty("os.arch").toUpperCase();
        System.out.println("System.getProperty(\"os.arch\") = " + info);
        log.info("runt test method {{testGetSystemArchitecture}} complete");
    }

    @Test
    public void testGetAllChromeDriverResources() {
        log.info("runt test method {{testGetAllChromeDriverResources}} start");
        log.info("runt test method {{testGetAllChromeDriverResources}} complete");
    }
}
