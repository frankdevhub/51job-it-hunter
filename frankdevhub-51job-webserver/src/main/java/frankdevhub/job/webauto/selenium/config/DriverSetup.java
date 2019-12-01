package frankdevhub.job.webauto.selenium.config;

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

public interface DriverSetup {
    RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities, String path);
}
