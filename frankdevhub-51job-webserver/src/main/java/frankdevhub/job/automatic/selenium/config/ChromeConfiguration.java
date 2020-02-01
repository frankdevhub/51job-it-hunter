package frankdevhub.job.automatic.selenium.config;

import frankdevhub.job.automatic.core.constants.BusinessConstants;
import frankdevhub.job.automatic.core.exception.BusinessException;
import org.apache.commons.io.FileUtils;
import tk.mybatis.mapper.util.Assert;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

    private final Lock userLock = new ReentrantLock();

    public static final String DEFAULT_WIN_CHROME_CACHE_PATH = "C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\User Data";
    public static final String DEFAULT_WIN_SELENIUM_CACHE_ROOT = "C:\\Automation\\";
    private static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + File.separator + "chromedriver.exe";

    private String seleniumBrowserCacheRoot = null;
    private String seleniumCacheFileName = null;
    private String webDriverPath = null;

    public ChromeConfiguration setSeleniumBrowserCacheRoot(String path) {
        this.seleniumBrowserCacheRoot = path;
        return this;
    }

    public ChromeConfiguration setSeleniumCacheFileName(String fileName) {
        this.seleniumCacheFileName = fileName;
        return this;
    }

    public ChromeConfiguration setWebDriverPath(String webDriverPath) {
        this.webDriverPath = webDriverPath;
        return this;
    }

    private void isSeleniumBrowserCacheDirectoryExist(File directory) throws BusinessException {
        if (!directory.exists())
            throw new BusinessException(BusinessConstants.SELENIUM_CACHE_ROOT_NOT_EXISTS);
    }

    private String getSeleniumCacheDirectoryPath() {
        return this.seleniumBrowserCacheRoot + File.pathSeparator + this.seleniumCacheFileName;
    }

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
    public Boolean getCacheDirectoryLockedStatus() throws IOException {
        Assert.notNull(webDriverPath, BusinessConstants.SELENIUM_WEB_DRIVER_PATH_NULL);
        System.out.println("web driver: " + webDriverPath);

        File driver = new File(webDriverPath);
        try {
            RandomAccessFile temp = new RandomAccessFile(driver, "rw");
            temp.close();
        } catch (IOException e) {
            e.printStackTrace();
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public String setSeleniumBrowserCache(String browserCachePath, String cacheFileName) throws IOException, BusinessException {
        Assert.notNull(seleniumBrowserCacheRoot, BusinessConstants.SELENIUM_CACHE_ROOT_NULL);
        Assert.notNull(seleniumCacheFileName, BusinessConstants.SELENIUM_CACHE_FILE_NAME_NULL);
        String directoryCopyName = seleniumBrowserCacheRoot + cacheFileName;

        System.out.println(String.format("chrome cache directory location:[%s]", browserCachePath));
        System.out.println(String.format("copy chrome cache directory location:[%s]", directoryCopyName));

        File browserCacheDirectory = new File(browserCachePath);
        File browserCacheCopyDirectory = new File(directoryCopyName);
        isSeleniumBrowserCacheDirectoryExist(browserCacheCopyDirectory);

        System.out.println("start to copy cache directory from source path to dest path");
        FileUtils.copyDirectory(browserCacheDirectory, browserCacheCopyDirectory);
        System.out.println(String.format("copy complete, directory location:[%s]", directoryCopyName));

        return directoryCopyName;
    }

    @Override
    public ChromeConfiguration deleteHistorySeleniumBrowserCache() throws IOException {
        File cacheFolder = new File(getSeleniumCacheDirectoryPath());
        File[] files = cacheFolder.listFiles();
        for (File file : files) {
            FileUtils.forceDelete(file);
        }
        return this;
    }

}
