//package frankdevhub.job.webauto.web.pages;
//
//import nyoibo.inkstone.upload.data.logging.Logger;
//import nyoibo.inkstone.upload.data.logging.LoggerFactory;
//import nyoibo.inkstone.upload.gui.ConsoleTextAreaListener;
//import nyoibo.inkstone.upload.gui.ErrorDialogUtils;
//import nyoibo.inkstone.upload.gui.InkstoneUploadConsole;
//import nyoibo.inkstone.upload.message.MessageMethod;
//import nyoibo.inkstone.upload.selenium.AssignDriver;
//import nyoibo.inkstone.upload.selenium.Query;
//import nyoibo.inkstone.upload.selenium.config.SeleniumInkstone;
//import nyoibo.inkstone.upload.utils.WebDriverUtils;
//import nyoibo.inkstone.upload.web.action.InkstoneUploadMainService;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class InkstoneHomePage implements Runnable, ConsoleTextAreaListener {
//    private final String accountName;
//    private final String accountPwd;
//    private final Logger LOGGER = LoggerFactory.getLogger(InkstoneHomePage.class);
//    private Query signIntoBtn;
//    private Query selectEmailLoginBtn;
//    private Query accountNameInput;
//    private Query accountPwdInput;
//    private Query submitBtn;
//    private WebDriver driver;
//
//    public InkstoneHomePage(boolean foreign, WebDriver driver) {
//        this.driver = driver;
//        if (foreign) {
//            this.accountName = SeleniumInkstone.INKSTONE_ACCOUNT_NAME_EN;
//            this.accountPwd = SeleniumInkstone.INKSTONE_ACCOUNT_PWD_CN;
//        } else {
//            this.accountName = SeleniumInkstone.INKSTONE_ACCOUNT_NAME_CN;
//            this.accountPwd = SeleniumInkstone.INKSTONE_ACCOUNT_PWD_CN;
//        }
//
//        signIntoBtn = new Query()
//                .defaultLocator(By.cssSelector("[class='" + SeleniumInkstone.INKSTONE_SIGN_BTN_CLASS + "']"));
//        accountNameInput = new Query().defaultLocator(By.name(SeleniumInkstone.INKSTONE_LOGIN_INPUT_EMAIL_NAME));
//        accountPwdInput = new Query().defaultLocator(By.name(SeleniumInkstone.INKSTONE_LOGIN_INPUT_PWD_NAME));
//        selectEmailLoginBtn = new Query()
//                .defaultLocator(By.cssSelector("[class='" + SeleniumInkstone.INKSTONE_LOGIN_PANEL_EMAIL_CLASS + "']"));
//        submitBtn = new Query().defaultLocator(By.id(SeleniumInkstone.INKSTONE_LOGIN_SUBMIT_ID));
//
//        AssignDriver.initQueryObjects(this, (RemoteWebDriver) this.driver);
//        pushLog(LOGGER.begin().headerAction(MessageMethod.EVENT).info("Init InkstoneHomePage Thread complete."));
//
//        InkstoneUploadMainService.currentChapterName = "InkstoneLogin";
//    }
//
//    private void login() throws Exception {
//
//        InkstoneUploadMainService.process.put(InkstoneUploadMainService.currentChapterName, 10);
//        pushLog(LOGGER.begin().headerMethod(MessageMethod.EVENT).info("navigate to homepage"));
//        driver.get(SeleniumInkstone.INKSTONE);
//
//        try {
//            WebDriverWait wait = new WebDriverWait(driver, 5, 100);
//            WebDriverUtils.doWaitTitle(SeleniumInkstone.INKSTONE_HOME_TITLE, wait);
//
//            Thread.sleep(2000);
//            InkstoneUploadMainService.process.put(InkstoneUploadMainService.currentChapterName, 20);
//            signIntoBtn.findWebElement().click();
//            Thread.sleep(2000);
//            driver.switchTo().frame(SeleniumInkstone.INKSTONE_MAIL_LOGIN_FRAME_ID);
//            selectEmailLoginBtn.findWebElement().click();
//            Thread.sleep(500);
//            LOGGER.begin().headerAction(MessageMethod.EVENT).info("switch to login iframe");
//
//            WebDriverUtils.findWebElement(accountNameInput).clear();
//            WebDriverUtils.findWebElement(accountNameInput).sendKeys(this.accountName);
//
//            WebDriverUtils.findWebElement(accountPwdInput).clear();
//            WebDriverUtils.findWebElement(accountPwdInput).sendKeys(this.accountPwd);
//
//            WebDriverUtils.findWebElement(submitBtn).click();
//
//        } catch (Exception e) {
//            // another login page start
//
//            // another login page end
//            JavascriptExecutor jsExec = (JavascriptExecutor) driver;
//            String function = "return document.readyState";
//            String code = (String) jsExec.executeScript(function);
//
//            if (!code.isEmpty() && code.equals("complete")) {
//                String title = driver.getTitle();
//                if (title.equals(SeleniumInkstone.INKSTONE_DASHBOARD)) {
//                    return;
//                }
//            } else {
//                throw e;
//            }
//
//        }
//        return;
//    }
//
//    @Override
//    public void run() {
//        try {
//            login();
//        } catch (Exception e) {
//            LOGGER.begin().headerAction(MessageMethod.ERROR)
//                    .error(String.format("Error at page:[%s]", driver.getTitle()));
//            LOGGER.begin().headerAction(MessageMethod.ERROR).error(e.getMessage());
//
//            InkstoneUploadMainService.exceptionList.add(e);
//            e.printStackTrace();
//            new ErrorDialogUtils(InkstoneUploadConsole.display).openErrorDialog("InkstoneUploadMainService Error", e);
//        }
//
//    }
//
//    @Override
//    public void pushLog(String message) {
//        InkstoneUploadConsole.consoleStr.add(message);
//    }
//
//}
