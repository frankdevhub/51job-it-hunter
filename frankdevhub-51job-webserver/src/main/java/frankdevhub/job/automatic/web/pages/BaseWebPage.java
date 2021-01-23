package frankdevhub.job.automatic.web.pages;

import frankdevhub.job.automatic.selenium.DriverBase;
import frankdevhub.job.automatic.selenium.config.ChromeConfiguration;
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
@SuppressWarnings("all")
public class BaseWebPage {
    private WebDriver driver;
    private ChromeConfiguration configuration;
    private Boolean isAutoConfig;

    public BaseWebPage(Boolean isAutoConfig) {
        initDriver(isAutoConfig);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public BaseWebPage setDriver(WebDriver driver) {
        this.driver = driver;
        return this;
    }

    public ChromeConfiguration getConfiguration() {
        return configuration;
    }

    public BaseWebPage setConfiguration(ChromeConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    public Boolean getIsAutoConfig() {
        return isAutoConfig;
    }

    public BaseWebPage setIsAutoConfig(Boolean isAutoConfig) {
        this.isAutoConfig = isAutoConfig;
        return this;
    }

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
