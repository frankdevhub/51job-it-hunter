package frankdevhub.job.automatic.selenium.config;

import lombok.extern.slf4j.Slf4j;

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

@Slf4j
@SuppressWarnings("all")
public class WebDriverInstaller {

    /**
     * 获取本地操作系统
     */
    public String getOperatingSystem() {
        //转换为大写字符串格式
        return System.getProperty("os.name").toUpperCase();
    }

    /**
     * 获取本地操作系统下安装的所有浏览器
     */
    public List[] getInstalledWebBrowsers() {
        return null;
    }

    /**
     * 获取本地操作系统下安装的所有浏览器的版本
     */
    public String getInstalledWebBrowserVersion() {
        return null;
    }

}
