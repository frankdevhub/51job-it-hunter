package frankdevhub.job.automatic.selenium.config;

import frankdevhub.job.automatic.core.exception.BusinessException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface SeleniumBrowserConfiguration {
    String setSeleniumCacheDirectoryName(String threadName);

    String getDefaultBrowserCachePath();

    String getSystemBrowserCachePath();

    Boolean getCacheDirectoryLockedStatus() throws BusinessException, FileNotFoundException;

    String setSeleniumBrowserCache(String browserCachePath, String cacheFileName) throws IOException, BusinessException;

    ChromeConfiguration deleteHistorySeleniumBrowserCache() throws IOException;
}
