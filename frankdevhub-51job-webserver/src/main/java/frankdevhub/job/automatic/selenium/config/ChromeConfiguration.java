package frankdevhub.job.automatic.selenium.config;

import frankdevhub.job.automatic.core.constants.BusinessConstants;
import frankdevhub.job.automatic.core.exception.BusinessException;
import org.apache.commons.io.FileUtils;
import tk.mybatis.mapper.util.Assert;

import java.io.File;
import java.io.FileNotFoundException;
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

    public static final String DEFAULT_WIN_CHROME_CACHE_PATH = "C:/Users/Administrator/AppData/Local/Google/Chrome/User Data";
    public static final String DEFAULT_WIN_SELENIUM_CACHE_ROOT_PATH = "C:/Automation/";

    private String seleniumBrowserCacheDirectoryRootPath = null;
    private String seleniumCacheFileName = null;

    public ChromeConfiguration setSeleniumBrowserCacheDirectoryRootPath(String path) {
        this.seleniumBrowserCacheDirectoryRootPath = path;
        return this;
    }

    public ChromeConfiguration setSeleniumCacheFileName(String fileName) {
        this.seleniumCacheFileName = fileName;
        return this;
    }

    private void isSeleniumBrowserCacheDirectoryExist(File directory) throws BusinessException {
        if (!directory.exists())
            throw new BusinessException(BusinessConstants.SELENIUM_CACHE_DIRECTORY_ROOT_NOT_EXISTS);
    }

    private String getSeleniumCacheDirectoryPath() {
        return this.seleniumBrowserCacheDirectoryRootPath + "/" + this.seleniumCacheFileName;
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
    public Boolean getCacheDirectoryLockedStatus() throws FileNotFoundException {

        String path = getSeleniumCacheDirectoryPath();
        RandomAccessFile temp = new RandomAccessFile(new File(path), "rw");
        try {
            temp.close();
        } catch (IOException e) {
            e.printStackTrace();
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public String setSeleniumBrowserCache(String browserCachePath, String cacheFileName) throws IOException, BusinessException {
        Assert.notNull(seleniumBrowserCacheDirectoryRootPath, BusinessConstants.SELENIUM_CACHE_DIRECTORY_ROOT_PATH_NULL);
        Assert.notNull(seleniumCacheFileName, BusinessConstants.SELENIUM_CACHE_FILE_NAME_NULL);
        String directoryCopyName = seleniumBrowserCacheDirectoryRootPath + cacheFileName;

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
