package frankdevhub.job.automatic.selenium.config;

import java.io.IOException;

public interface SeleniumBrowserConfiguration {
    String setSeleniumCacheDirectoryName(String threadName);

    String getDefaultBrowserCachePath();

    String getSystemBrowserCachePath();

    String setSeleniumBrowserCache(String broswerCachePath, String directoryName) throws IOException;

    ChromeConfiguration deleteHistorySeleniumBrowserCache() throws IOException;
}
