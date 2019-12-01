package frankdevhub.job.webauto.web.action;

import nyoibo.inkstone.upload.data.logging.Logger;
import nyoibo.inkstone.upload.data.logging.LoggerFactory;
import nyoibo.inkstone.upload.gui.ConsoleTextAreaListener;
import nyoibo.inkstone.upload.gui.InkstoneUploadConsole;
import nyoibo.inkstone.upload.message.MessageMethod;
import nyoibo.inkstone.upload.selenium.config.SeleniumInkstone;
import nyoibo.inkstone.upload.utils.WebDriverUtils;
import nyoibo.inkstone.upload.web.pages.InkstoneChapterPage;
import nyoibo.inkstone.upload.web.pages.InkstoneHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

public class InkstoneRawNovelService implements Runnable, ConsoleTextAreaListener {
    private final WebDriver driver;
    private final InkstoneHomePage inkstoneHomePage;
    private final InkstoneChapterPage inkstoneChapterPage;

    private final boolean needLogin;

    private final Logger LOGGER = LoggerFactory.getLogger(InkstoneRawNovelService.class);

    private Thread homePageThread;
    private Thread chapterThread;

    public InkstoneRawNovelService(boolean foreign, String bookUrl, String bookName,
                                   Map<String, String> bookCompareList, boolean needLogin,
                                   WebDriver driver, Map<String, String> chapterFileList) throws Exception {
        this.driver = driver;
        this.needLogin = needLogin;
        this.inkstoneHomePage = new InkstoneHomePage(foreign, driver);
        this.inkstoneChapterPage = new InkstoneChapterPage(driver, bookUrl, bookName, bookCompareList, foreign,
                chapterFileList);
    }

    @Override
    public void run() {
        homePageThread = new Thread(inkstoneHomePage);
        homePageThread.setDaemon(true);
        chapterThread = new Thread(inkstoneChapterPage);
        chapterThread.setDaemon(true);

        if (needLogin) {
            try {
                pushLog("start to navigate to inkstone homepage.");
                homePageThread.start();
                homePageThread.join();
                Thread.sleep(3000);
                chapterThread.start();
                chapterThread.join();
            } catch (Exception e) {
                e.printStackTrace();
                InkstoneUploadMainService.exceptionList.add(e);
            }
        } else {
            try {
                pushLog(LOGGER.begin().headerMethod(MessageMethod.EVENT).info("go to next chapter..."));
                Thread.sleep(2000);
                driver.get(SeleniumInkstone.INKSTONE_PRO_DASHBOARD);

                WebDriverUtils.waitPageLoadComplete();
                Thread.sleep(2000);
                LOGGER.begin().headerAction(MessageMethod.EVENT).info("start to do next chapter, loop to dashboard");

                WebDriverUtils.doWaitTitle(SeleniumInkstone.INKSTONE_DASHBOARD, new WebDriverWait(driver, 10, 1000));
                chapterThread.start();
                chapterThread.join();
                Thread.sleep(2000);

            } catch (Exception e) {
                e.printStackTrace();
                InkstoneUploadMainService.exceptionList.add(e);
            }
        }
        Thread.currentThread().interrupt();
        LOGGER.begin().headerAction(MessageMethod.EVENT).info("raw main thread kill complete");

    }

    @Override
    public void pushLog(String message) {
        InkstoneUploadConsole.consoleStr.add(message);
        InkstoneUploadConsole.flag = false;
    }
}
