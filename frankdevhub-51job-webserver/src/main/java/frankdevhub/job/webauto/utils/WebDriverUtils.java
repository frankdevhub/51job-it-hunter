package frankdevhub.job.webauto.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import frankdevhub.job.webauto.selenium.Query;

public class WebDriverUtils {

    private static ExpectedCondition<Boolean> pageTitleStartsWith(final String header) {
        return driver -> driver.getTitle().toLowerCase().startsWith(header.toLowerCase());
    }

    public static ExpectedCondition<Boolean> waitPageLoadComplete() {
        String function = "return document.readyState";
        return driver -> ((String) ((JavascriptExecutor) driver)
                .executeScript(function)).equals("complete");
    }

    public static synchronized WebElement findWebElement(Query query) {
        WebElement element = query.findWebElement();
        System.out.println(String.format("Find Query:[%s]", query.toString()));
        return element;
    }

    public static synchronized void doWaitTitle(String header, WebDriverWait wait) {

        wait.until(pageTitleStartsWith(header));
    }

    public static synchronized void doWaitQuery(Query query, WebDriverWait wait) {
        wait.until((ExpectedCondition<WebElement>) driver -> query.findWebElement());
        System.out.println(String.format("Find Query:[%s]", query.toString()));
    }

    public static synchronized void doWaitCss(String css, WebDriverWait wait) {
        wait.until((ExpectedCondition<WebElement>) driver ->
                driver.findElement(By.cssSelector(css)));
        System.out.println(String.format("Find element by xPath:[%s]", css));
    }

    public static synchronized void doWaitId(String id, WebDriverWait wait) {
        wait.until((ExpectedCondition<WebElement>) driver -> driver.findElement(By.id(id)));

        System.out.println(String.format("Find Element with id:[%s]", id));
    }
}
