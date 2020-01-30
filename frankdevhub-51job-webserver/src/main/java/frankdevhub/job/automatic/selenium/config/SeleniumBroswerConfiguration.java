package frankdevhub.job.automatic.selenium.config;

import java.io.IOException;

public interface SeleniumBroswerConfiguration {
    String setSeleniumCachDirectoryName(String threadName);

    String getDefaultBroswerCachePath();

    String setSeleniumBroswerCache(String broswerCachePath, String directoryName) throws IOException;

    void deleteHistorySeleniumBroswerCache() throws IOException;
}
