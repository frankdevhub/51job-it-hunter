package frankdevhub.job.automatic.selenium.config;

import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;

import java.util.List;

/**
 * <p>Title:@ClassName WebDriverInstaller.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/9 2:16
 * @Version: 1.0
 */
public class WebDriverInstaller {

    private final Logger LOGGER = LoggerFactory.getLogger(WebDriverInstaller.class);

    public String getOperatingSystem() {
        return System.getProperty("os.name").toUpperCase();
    }

    public List[] getInstalledWebBrowsers() {
        return null;
    }

    public String getInstalledWebBrowserVersion() {
        return null;
    }
}
