package frankdevhub.job.automatic.selenium.config;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

@Slf4j
@SuppressWarnings("all")
public class DriverFactory {

    private RemoteWebDriver driver; //浏览器驱动
    private DriverType selectedDriverType; //自定义的浏览器类型

    private final String operatingSystem = System.getProperty("os.name").toUpperCase(); //本地操作系统
    private final String systemArchitecture = System.getProperty("os.arch"); //操作系统版本
    private final boolean useRemoteWebDriver = Boolean.getBoolean("remoteDriver"); //是否配置驱动服务器
    private static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + File.separator + "chromedriver.exe"; //浏览器驱动存放路径

    public DriverFactory() {

        //配置浏览器驱动的加载路径
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        log.info("webdriver.chrome.driver = " + CHROME_DRIVER_PATH);
        //默认使用Chrome浏览器
        DriverType driverType = DriverType.CHROME;
        //根据浏览器配置获取对应的驱动类型
        String browser = System.getProperty("browser", driverType.toString()).toUpperCase();
        try {
            driverType = DriverType.valueOf(browser); //驱动类型
        } catch (IllegalArgumentException ignored) {
            log.error("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            log.error("No driver specified, defaulting to '" + driverType + "'...");
        }
        selectedDriverType = driverType;
    }

    /**
     * 构造浏览器驱动对象
     *
     * @param path 浏览器驱动路径
     * @return 浏览器驱动对象
     * @throws Exception
     */
    public RemoteWebDriver getDriver(String path) throws Exception {
        if (null == driver) {
            //初始化浏览器驱动
            instantiateWebDriver(selectedDriverType, path);
        }
        return driver;
    }

    /**
     * 获取浏览器驱动
     */
    public RemoteWebDriver getStoredDriver() {
        return driver;
    }

    /**
     * 关闭浏览器驱动
     */
    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null; //释放资源
        }
    }

    /**
     * 初始化浏览器驱动
     *
     * @param driverType 浏览器驱动的类型
     * @param path       浏览器驱动路径
     * @throws IOException
     */
    private void instantiateWebDriver(DriverType driverType, String path) throws IOException {
        log.info(" ");
        log.info("Local Operating System: " + operatingSystem);
        log.info("Local Architecture: " + systemArchitecture);
        log.info("Selected Browser: " + selectedDriverType);
        log.info("Connecting to Selenium Grid: " + useRemoteWebDriver);
        log.info(" ");

        //默认使用Chrome浏览器
        //驱动加载页面的策略配置
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities = DesiredCapabilities.chrome();
        //自动过滤弹出框以及DOM懒加载
        desiredCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        driver = driverType.getWebDriverObject(desiredCapabilities, path);

        desiredCapabilities.setCapability("pageLoadStrategy", "none");

    }
}
