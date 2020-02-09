package frankdevhub.job.automatic.selenium;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * <p>Title:Query.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @author frankdevhub
 * @date:2019-04-21 16:05
 */

public class Query {
    private RemoteWebDriver driver;
    private String currentType;
    private By defaultLocator;
    private HashMap<String, By> customLocators = new HashMap();
    private boolean isAppiumDriver;

    public Query defaultLocator(By locator) {
        this.defaultLocator = locator;

        return this;
    }

    public Query addSpecificLocator(String browser, By locator) {
        this.customLocators.put(browser.toUpperCase(), locator);

        return this;
    }

    public Query usingDriver(RemoteWebDriver driverObject) {
        if (null != driverObject) {
            this.driver = driverObject;
            Object automationName = this.driver.getCapabilities().getCapability("automationName");
            this.isAppiumDriver = ((null != automationName)
                    && (automationName.toString().toLowerCase().equals("appium")));
            this.currentType = this.driver.getCapabilities().getBrowserName();
            if ((this.isAppiumDriver) && ((null == this.currentType) || (this.currentType.isEmpty()))) {
                this.currentType = this.driver.getCapabilities().getCapability("platformName").toString();
            }
        } else {
            throw new NullPointerException("Driver object is null!");
        }
        return this;
    }

    public WebElement findWebElement() {
        return this.driver.findElement(by());
    }

    public MobileElement findMobileElement() {
        if (this.isAppiumDriver) {
            return (MobileElement) this.driver.findElement(by());
        }
        throw new UnsupportedOperationException("You don't seem to be using Appium!");
    }

    public List<WebElement> findWebElements() {
        return this.driver.findElements(by());
    }

    public List<MobileElement> findMobileElements() {
        if (this.isAppiumDriver) {
            List<WebElement> elementsFound = this.driver.findElements(by());
            List<MobileElement> mobileElementsToReturn = new ArrayList();
            for (WebElement element : elementsFound) {
                mobileElementsToReturn.add((MobileElement) element);
            }
            return mobileElementsToReturn;
        }
        throw new UnsupportedOperationException("You don't seem to be using Appium!");
    }

    public Select findSelectElement() {
        return new Select(findWebElement());
    }

    public By by() {
        checkDriverIsSet();
        By locatorToReturn = (By) this.customLocators.getOrDefault(this.currentType.toUpperCase(), this.defaultLocator);

        return checkLocatorIsNotNull(locatorToReturn);
    }

    private By checkLocatorIsNotNull(By locator) {
        if (null == locator) {
            throw new IllegalStateException(
                    String.format("Unable to detect valid by for '%s' and a default by has not been set!",
                            new Object[]{this.currentType}));
        }
        return locator;
    }

    boolean checkDriverIsSet() {
        if (null == this.driver) {
            throw new IllegalStateException(
                    "Driver object has not been set... You must call 'Query.initQueryObject(driver);'!");
        }
        return true;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        Query query = (Query) o;
        return (this.isAppiumDriver == query.isAppiumDriver) && (Objects.equals(this.driver, query.driver))
                && (Objects.equals(this.currentType, query.currentType))
                && (Objects.equals(this.defaultLocator, query.defaultLocator))
                && (Objects.equals(this.customLocators, query.customLocators));
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.driver, this.currentType, this.defaultLocator, this.customLocators,
                Boolean.valueOf(this.isAppiumDriver)});
    }

}
