package frankdevhub.job.automatic.web.pages;

import frankdevhub.job.automatic.selenium.DriverBase;
import frankdevhub.job.automatic.selenium.config.ChromeConfiguration;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

/**
 * <p>Title:@ClassName BaseWebPage.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/4 22:04
 * @Version: 1.0
 */

@Slf4j
@Data
@SuppressWarnings("all")
public class BaseWebPage {
    private WebDriver driver; //浏览器驱动对象
    private ChromeConfiguration configuration; //Chrome浏览器驱动配置
    private Boolean isAutoConfig; //是否自动装配

    public BaseWebPage(Boolean isAutoConfig) {
        initDriver(isAutoConfig);
    }

    /**
     * 初始化浏览器驱动对象
     *
     * @param isAutoConfig 是否自动配置
     */
    public void initDriver(Boolean isAutoConfig) {
        try {
            this.configuration = ChromeConfiguration.newInstance(isAutoConfig);
            this.isAutoConfig = isAutoConfig;
            String seleniumCacheDirectoryPath = configuration.getSeleniumCacheDirectoryPath();
            DriverBase.instantiateDriverObject();
            log.info("configuration path set as: " + seleniumCacheDirectoryPath);
            this.driver = DriverBase.getDriver(seleniumCacheDirectoryPath);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
