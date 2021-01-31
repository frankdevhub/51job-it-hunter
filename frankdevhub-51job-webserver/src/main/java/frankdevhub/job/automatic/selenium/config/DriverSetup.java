package frankdevhub.job.automatic.selenium.config;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * <p>Title:DriverSetup.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @author frankdevhub
 * @date:2019-04-21 01:19
 */

@SuppressWarnings("all")
public interface DriverSetup {
    /**
     * 构造浏览器驱动对象
     *
     * @param capabilities 驱动加载页面的策略
     * @param path         浏览器驱动路径
     * @return 浏览器驱动对象
     */
    RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities, String path);
}
