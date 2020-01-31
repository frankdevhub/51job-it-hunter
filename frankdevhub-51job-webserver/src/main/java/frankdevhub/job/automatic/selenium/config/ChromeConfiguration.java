package frankdevhub.job.automatic.selenium.config;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * <p>Title:ChromeConfiguration.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @author frankdevhub
 * @date:2019-05-12 17:28
 */

public class ChromeConfiguration implements SeleniumBrowserConfiguration {

    public static final String DEFAULT_WIN_CHROME_CACHE_PATH = "C:/Users/Administrator/AppData/Local/Google/Chrome/User Data";
    public static final String DEFAULT_WIN_SELENIUM_CACHE_PATH = "C:/Automation/";

    @Override
    public String setSeleniumCacheDirectoryName(String threadName) {
        StringBuilder builder = new StringBuilder();
        long time = System.currentTimeMillis();
        String timeStr = Long.toString(time);

        String directoryName = builder.append(timeStr).append("-").append(threadName).toString();
        return directoryName;
    }

    @Override
    public String getDefaultBrowserCachePath() {
        return DEFAULT_WIN_CHROME_CACHE_PATH;
    }

    @Override
    public String getSystemBrowserCachePath() {
        return null;
    }

    @Override
    public String setSeleniumBrowserCache(String browserCachePath, String directoryName) throws IOException {
        String directoryCopyName = DEFAULT_WIN_SELENIUM_CACHE_PATH + directoryName;

        System.out.println(String.format("chrome cache directory location:[%s]", browserCachePath));
        System.out.println(String.format("copy chrome cache directory location:[%s]", directoryCopyName));

        File browserCacheDirectory = new File(browserCachePath);
        File browserCacheCopyDirectory = new File(directoryCopyName);

        System.out.println("start to copy cache directory from source path to dest path");
        FileUtils.copyDirectory(browserCacheDirectory, browserCacheCopyDirectory);
        System.out.println(String.format("copy complete, directory location:[%s]", directoryCopyName));

        return directoryCopyName;
    }

    @Override
    public ChromeConfiguration deleteHistorySeleniumBrowserCache() throws IOException {
        File cacheFolder = new File(DEFAULT_WIN_SELENIUM_CACHE_PATH);
        File[] files = cacheFolder.listFiles();
        for (File file : files) {
            FileUtils.forceDelete(file);
        }
        return this;
    }

}
