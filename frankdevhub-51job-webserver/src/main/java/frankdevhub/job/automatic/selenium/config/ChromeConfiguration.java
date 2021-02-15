package frankdevhub.job.automatic.selenium.config;

import frankdevhub.job.automatic.core.constants.BusinessConstants;
import frankdevhub.job.automatic.core.exception.BusinessException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
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


@Slf4j
@Data
@SuppressWarnings("all")
public class ChromeConfiguration implements SeleniumConfiguration {

    private final Lock configLock = new ReentrantLock();

    //Chrome浏览器默认windows操作系统下的缓存目录位置
    public static final String DEFAULT_WIN_CHROME_CACHE_PATH = "C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\User Data";
    //Selenium浏览器驱动缓存的目录位置
    public static final String DEFAULT_WIN_SELENIUM_CACHE_ROOT = "C:\\Automation";
    //本地环境下配置的浏览器驱动的路径
    public static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + File.separator + "chromedriver.exe";
    //Selenium浏览器驱动的缓存目录名称
    public static final String DEFAULT_SELENIUM_CACHE_NAME = "junit-selenium-test";

    private String seleniumBrowserCacheRoot = null;
    private String seleniumCacheFileName = null;
    private String webDriverPath = null;
    private String systemBrowserCachePath = null;

    public ChromeConfiguration() {

    }

    /**
     * 浏览器驱动配置
     *
     * @param seleniumBrowserCacheRoot 浏览器驱动的缓存路径
     * @param seleniumCacheFileName    浏览器驱动缓存的文件名
     * @param webDriverPath            浏览器驱动存储路径
     */
    private ChromeConfiguration(String seleniumBrowserCacheRoot, String seleniumCacheFileName,
                                String webDriverPath) {

        this.seleniumBrowserCacheRoot = seleniumBrowserCacheRoot;
        this.seleniumCacheFileName = seleniumCacheFileName;
        this.webDriverPath = webDriverPath;

        log.info("web browser cache configuration properties:");
        log.info("seleniumBrowserCacheRoot = " + seleniumBrowserCacheRoot);
        log.info("seleniumCacheFileName = " + seleniumCacheFileName);
        log.info("\n\n");
    }

    /**
     * 获取浏览器驱动配置实例
     *
     * @param isAutoConfig 是否自动装配
     * @return 浏览器驱动配置
     */
    public static ChromeConfiguration newInstance(Boolean isAutoConfig) {
        //TODO
        if (isAutoConfig) {
            return null;
        } else {
            //TODO:自动装配默认配置
            //return new ChromeConfiguration();
            return new ChromeConfiguration(DEFAULT_WIN_SELENIUM_CACHE_ROOT, DEFAULT_SELENIUM_CACHE_NAME, CHROME_DRIVER_PATH);
        }
        //return new ChromeConfiguration(DEFAULT_WIN_SELENIUM_CACHE_ROOT, DEFAULT_SELENIUM_CACHE_NAME, CHROME_DRIVER_PATH);
    }

    /**
     * 校验判断浏览器缓存目录是否存在
     *
     * @param directory 浏览器缓存目录路径
     * @throws BusinessException 缓存目录不存在时的业务异常
     */
    private void isSeleniumBrowserCacheDirectoryExist(File directory) throws BusinessException {
        log.info("use selenium browser cache as:");
        log.info(directory.getAbsolutePath());
        if (!directory.exists()) {
            throw new BusinessException(BusinessConstants.SELENIUM_CACHE_ROOT_NOT_EXISTS);
        }
    }

    /**
     * 获取浏览器缓存目录的路径
     */
    public String getSeleniumCacheDirectoryPath() {
        return this.seleniumBrowserCacheRoot + File.separator + this.seleniumCacheFileName;
    }

    /**
     * 配置缓存的目录
     *
     * @param threadName 驱动进程的名称
     * @return 驱动进程对应的缓存目录名
     */
    @Override
    public String setSeleniumCacheDirectoryName(String threadName) {
        StringBuilder builder = new StringBuilder();
        long time = System.currentTimeMillis();
        String timeStr = Long.toString(time);

        //方法名加时间戳和间隔符号生成对应驱动实例的缓存的存储目录名称
        String directoryName = builder.append(timeStr).append("-").append(threadName).toString();
        return directoryName;
    }

    /**
     * 获取驱动缓存的路径
     */
    @Override
    public String getDefaultBrowserCache() {
        return DEFAULT_WIN_CHROME_CACHE_PATH;
    }

    /**
     * 自动获取本地驱动缓存的路径
     *
     * @return 本地操作系统下的浏览器缓存路径
     */
    @Override
    public String searchSystemBrowserCache() {
        String path = null;
        this.systemBrowserCachePath = path;
        return null;
    }

    /**
     * 获取驱动缓存的读写权限
     *
     * @return 文件对象是否占用
     * @throws BusinessException,IOException
     */
    @Override
    public Boolean getCacheDirectoryLockedStatus() {
        Assert.notNull(webDriverPath, BusinessConstants.SELENIUM_WEB_DRIVER_PATH_NULL);
        log.info("web driver: " + webDriverPath);

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

    /**
     * 获取驱动缓存的路径与目录名称
     *
     * @param browserCachePath 浏览器缓存存储路径
     * @param cacheFileName    浏览器缓存目录名称
     * @return 依据规则生成的缓存目录的路径
     * @throws BusinessException,IOException
     */
    @Override
    public String setSeleniumBrowserCache(String browserCachePath, String cacheFileName) throws IOException, BusinessException {
        Assert.notNull(seleniumBrowserCacheRoot, BusinessConstants.SELENIUM_CACHE_ROOT_NULL);
        Assert.notNull(seleniumCacheFileName, BusinessConstants.SELENIUM_CACHE_FILE_NAME_NULL);
        String directoryCopyName = seleniumBrowserCacheRoot + File.separator + cacheFileName;

        log.info(String.format("chrome cache directory location:[%s]", browserCachePath));
        log.info(String.format("copy chrome cache directory location:[%s]", directoryCopyName));

        File browserCacheDirectory = new File(browserCachePath);
        File browserCacheCopyDirectory = new File(directoryCopyName);
        isSeleniumBrowserCacheDirectoryExist(browserCacheCopyDirectory);

        log.info("start to copy cache directory from source path to dest path");
        FileUtils.copyDirectory(browserCacheDirectory, browserCacheCopyDirectory);
        log.info(String.format("copy complete, directory location:[%s]", directoryCopyName));

        return directoryCopyName;
    }

    /**
     * 清除浏览器缓存历史数据
     *
     * @return 浏览器驱动配置对象
     * @throws IOException
     */
    @Override
    public ChromeConfiguration deleteSeleniumBrowserCacheHistory() throws IOException {
        File cacheFolder = new File(getSeleniumCacheDirectoryPath());
        File[] files = cacheFolder.listFiles();
        for (File file : files) {
            FileUtils.forceDelete(file);
        }
        return this;
    }

    /**
     * 同步本地浏览器缓存至测试驱动下的浏览器缓存
     */
    @Override
    public void synchronizeSeleniumBrowserCache() {
        try {
            configLock.lock();
            Long start = System.currentTimeMillis();
            setSeleniumBrowserCache(this.seleniumBrowserCacheRoot, this.seleniumCacheFileName);
            Long end = System.currentTimeMillis();

            log.info("synchronized browser cache complete, cost: "
                    + (end - start) / 1000 + "sec");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            configLock.unlock();
        }
    }

}
