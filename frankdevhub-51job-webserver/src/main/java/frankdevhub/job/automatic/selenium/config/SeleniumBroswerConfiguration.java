package frankdevhub.job.automatic.selenium.config;

import java.io.IOException;

public interface SeleniumBroswerConfiguration {
    String setSeleniumCacheDirectoryName(String threadName);

    String getDefaultBrowserCachePath();

    String setSeleniumBrowserCache(String broswerCachePath, String directoryName) throws IOException;

    void deleteHistorySeleniumBrowserCache() throws IOException;
}
