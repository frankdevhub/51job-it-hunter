package frankdevhub.job.automatic.core.utils;

import frankdevhub.job.automatic.selenium.Query;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@Slf4j
@SuppressWarnings("all")
public class WebDriverUtils {

    private static ExpectedCondition<Boolean> pageTitleStartsWith(final String header) {
        return driver -> driver.getTitle().toLowerCase().startsWith(header.toLowerCase());
    }

    private static ExpectedCondition<Boolean> pageTitleContains(final String header) {
        return driver -> driver.getTitle().toLowerCase().contains(header.toLowerCase());
    }

    public static ExpectedCondition<Boolean> waitPageLoadComplete() {
        String function = "return document.readyState";
        return driver -> ((String) ((JavascriptExecutor) driver)
                .executeScript(function)).equals("complete");
    }

    public static synchronized WebElement findWebElement(Query query) {
        WebElement element = query.findWebElement();
        log.info(String.format("Find Query:[%s]", query.toString()));
        return element;
    }

    public static synchronized List<WebElement> findWebElements(Query query) {
        List<WebElement> list = query.findWebElements();
        log.info(String.format("Find Query:[%s]", query.toString()));
        return list;
    }

    public static synchronized void doWaitTitleContains(String header, WebDriverWait wait) {
        wait.until(pageTitleContains(header));
    }

    public static synchronized void doWaitTitleStartsWith(String header, WebDriverWait wait) {
        wait.until(pageTitleStartsWith(header));
    }

    public static synchronized void doWaitQuery(Query query, WebDriverWait wait) {
        wait.until((ExpectedCondition<WebElement>) driver -> query.findWebElement());
        log.info(String.format("Find Query:[%s]", query.toString()));
    }

    public static synchronized void doWaitCss(String css, WebDriverWait wait) {
        wait.until((ExpectedCondition<WebElement>) driver ->
                driver.findElement(By.cssSelector(css)));
        log.info(String.format("Find element by xPath:[%s]", css));
    }

    public static synchronized void doWaitId(String id, WebDriverWait wait) {
        wait.until((ExpectedCondition<WebElement>) driver -> driver.findElement(By.id(id)));
        log.info(String.format("Find Element with id:[%s]", id));
    }
}
