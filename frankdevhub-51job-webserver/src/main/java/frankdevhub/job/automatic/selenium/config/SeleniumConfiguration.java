package frankdevhub.job.automatic.selenium.config;

import frankdevhub.job.automatic.core.exception.BusinessException;

import java.io.IOException;

/**
 * @Title: SeleniumBrowserConfiguration
 * @Description: 驱动配置
 * @date: 2021/1/31 12:18
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */

@SuppressWarnings("all")
public interface SeleniumConfiguration {

    /**
     * 配置缓存的目录
     *
     * @param threadName 驱动进程的名称
     * @return 驱动进程对应的缓存目录名
     */
    String setSeleniumCacheDirectoryName(String threadName);

    /**
     * 获取驱动缓存的路径
     */
    String getDefaultBrowserCache();

    /**
     * 自动获取本地驱动缓存的路径
     *
     * @return 本地操作系统下的浏览器缓存路径
     */
    String searchSystemBrowserCache();

    /**
     * 获取驱动缓存的读写权限
     *
     * @return 文件对象是否占用
     * @throws BusinessException,IOException
     */
    Boolean getCacheDirectoryLockedStatus() throws BusinessException, IOException;

    /**
     * 获取驱动缓存的路径与目录名称
     *
     * @throws BusinessException,IOException
     */
    String setSeleniumBrowserCache(String browserCachePath, String cacheFileName) throws IOException, BusinessException;

    /**
     * 清除浏览器缓存历史数据
     *
     * @return 浏览器驱动配置对象
     * @throws IOException
     */
    ChromeConfiguration deleteSeleniumBrowserCacheHistory() throws IOException;

    /**
     * 同步本地浏览器缓存至测试驱动下的浏览器缓存
     */
    void synchronizeSeleniumBrowserCache();
}
