package data.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

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
@SuppressWarnings("all")
public class WebDriverInstallerTest {

    /**
     * 测试获取本地操作系统
     */
    @Test
    public void testGetOperatingSystem() {
        String info = System.getProperty("os.name").toUpperCase();
        log.info("System.getProperty(\"os.name\") = " + info);
    }

    /**
     * 测试获取本地操作系统
     */
    @Test
    public void testGetSystemArchitecture() {
        String info = System.getProperty("os.arch").toUpperCase();
        log.info("System.getProperty(\"os.arch\") = " + info);
    }

}
