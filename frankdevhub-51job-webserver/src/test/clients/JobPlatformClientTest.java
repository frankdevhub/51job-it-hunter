package clients;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import frankdevhub.job.automatic.core.constants.BusinessConstants;
import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import frankdevhub.job.automatic.core.utils.WebDriverUtils;
import frankdevhub.job.automatic.selenium.DriverBase;
import frankdevhub.job.automatic.selenium.config.ChromeConfiguration;
import frankdevhub.job.automatic.web.clients.JobPlatformClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.util.Assert;

import java.io.*;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title:@ClassName JobPlatformClientTest.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/23 11:23
 * @Version: 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class JobPlatformClientTest {

    private final Logger LOGGER = LoggerFactory.getLogger(JobPlatformClientTest.class);

    private final String TEST_RESULT_PAGE = "https://search.51job.com/list/020000,000000,0000,00,9,99,java,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
    private final String TEST_CONTENT = "<img http://sss src=\"http://www.foo.com/a.png\">http://sss/";
    private final String SEARCH_RESULT_REGEX = "([0-9]+)(.html?)";

    @Test
    public void testAccessCacheDirectory() throws IOException {
        LOGGER.begin().info("run test method {{testAccessCacheDirectory}} start");
        Resource res = new ClassPathResource("src/resources/cache/temp_cookie.dat");
        InputStream in = res.getInputStream();
        Assert.notNull(in, "resource document may not exist");

        LOGGER.begin().info("run test method {{testAccessCacheDirectory}} end");
    }

    @Test
    public void testReadTempCookieDocument() throws IOException, ClassNotFoundException {
        LOGGER.begin().info("run test method {{testReadTempCookieDocument}} start");
        File temp = new File(new ClassPathResource("src/main/resources/cache/temp_cookie.dat").getPath());
        Assert.notNull(temp, "temp file not found");

        FileInputStream fis = new FileInputStream(temp);
        Assert.notNull(fis, "resource document may not exist");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Set<Cookie> cookies = (Set<Cookie>) ois.readObject();
        ois.close();
        fis.close();

        for (Cookie c : cookies) {
            System.out.println("name = " + c.getName());
            System.out.println("path = " + c.getPath());
            System.out.println("value = " + c.getValue());

            System.out.println("\n");
        }

        LOGGER.begin().info("run test method {{testReadTempCookieDocument}} end");
    }

    //cookies when not login
    //***************************************************************************************************************************************
    // navigate to platform homepage complete
    //start to get web cookie
    //time cost: 103 ms
    //cookie properties:
    //name = nsearch
    //path = /
    //value = jobarea%3D%26%7C%26ord_field%3D%26%7C%26recentSearch0%3D%26%7C%26recentSearch1%3D%26%7C%26recentSearch2%3D%26%7C%26recentSearch3%3D%26%7C%26recentSearch4%3D%26%7C%26collapse_expansion%3D
    //
    //name = search
    //path = /
    //value = jobarea%7E%60020000%7C%21ord_field%7E%600%7C%21recentSearch0%7E%60020000%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FAjava%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21recentSearch1%7E%60020000%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FA%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21recentSearch2%7E%60020000%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FA%C9%CF%BA%A3%BD%DD%C0%FB%BB%F5%D4%CB%D3%D0%CF%DE%B9%AB%CB%BE%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21recentSearch3%7E%60360000%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FA%C9%CF%BA%A3%BD%DD%C0%FB%BB%F5%D4%CB%D3%D0%CF%DE%B9%AB%CB%BE%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21
    //
    //name = 51job
    //path = /
    //value = cenglish%3D0%26%7C%26
    //
    //name = guid
    //path = /
    //value = 5006ef6ea365e3268bd6afde0846a758
    //
    //**********************************************************************************************************************//

    @Test
    public void testGetPlatformCookie() throws Exception {
        LOGGER.begin().info("run test method {{testGetPlatformCookie}} start");
        ChromeConfiguration configuration = ChromeConfiguration.newInstance(false);
        configuration.setWebDriverPath(ChromeConfiguration.CHROME_DRIVER_PATH)
                .setSeleniumCacheFileName(ChromeConfiguration.DEFAULT_SELENIUM_CACHE_NAME)
                .setSeleniumBrowserCacheRoot(ChromeConfiguration.DEFAULT_WIN_SELENIUM_CACHE_ROOT);
        //configuration.synchronizeSeleniumBrowserCache();
        DriverBase.instantiateDriverObject();

        System.out.println("using cache path: " + configuration.getSeleniumCacheDirectoryPath());
        WebDriver driver = DriverBase.getDriver(configuration.getSeleniumCacheDirectoryPath());
        Assert.notNull(driver, "web driver not found");
       /* //clear default history cookies
        System.out.println("clear all restored cookies");
        driver.manage().deleteAllCookies();*/

        driver.get(BusinessConstants.JOB_PLATFORM_HOMEPAGE);
        WebDriverUtils.doWaitTitleContains("招聘", new WebDriverWait(driver, 3));

        System.out.println("navigate to platform homepage complete");
        System.out.println("login with user credential");

        System.out.println("start to get web cookie");
        Long start = System.currentTimeMillis();
        Set<Cookie> cookies = driver.manage().getCookies();
        Long end = System.currentTimeMillis();

        System.out.println("time cost: " + (end - start) + " ms");
        System.out.println("cookie properties:");
        for (Cookie c : cookies) {
            System.out.println("name = " + c.getName());
            System.out.println("path = " + c.getPath());
            System.out.println("value = " + c.getValue());

            System.out.println("\n");
        }

        //restore cookie to cache directory
        File temp = new File(new ClassPathResource("/src/main/resources/cache/temp_cookie.dat").getPath());
        if (!temp.exists())
            temp.createNewFile();
        FileOutputStream fos = new FileOutputStream(temp);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Assert.notNull(cookies, "cookies object should not be null");
        oos.writeObject(cookies);
        oos.flush();
        fos.flush();
        oos.close();
        fos.close();

        LOGGER.begin().info("run test method {{testGetPlatformCookie}} complete");
    }

    @Test
    public void testGetJobSearchResult() throws IOException, XpathSyntaxErrorException {
        LOGGER.begin().info("run test method {{testGetJobSearchResult}} start");
        JobPlatformClient client = new JobPlatformClient();
        client.getJobSearchResult(TEST_RESULT_PAGE);

        LOGGER.begin().info("run test method {{testGetJobSearchResult}} complete");
    }

    @Test
    public void testRegexGroupReplace() {
        String content = TEST_CONTENT;

        System.out.println(content);

        String pattern = "<img\\s*([^>]*)\\s*src=\\\"(http://.*?/)(.*?)\\\"\\s*([^>]*)>(http://.*?/)";
        StringBuffer operatorStr = new StringBuffer(content);
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(content);
        while (m.find()) {
            operatorStr.replace(m.start(2), m.end(2), "/");
            m = p.matcher(operatorStr);
        }

        System.out.println(operatorStr);
    }

    @Test
    public void testPageUrlRegex() {
        LOGGER.begin().info("run test method {{testGetPreviousPageUrl}} start");
        String url = TEST_RESULT_PAGE;
        Matcher matcher = Pattern.compile(SEARCH_RESULT_REGEX).matcher(url);
        if (matcher.find()) {
            String matcher_0 = matcher.group(0);
            String matcher_1 = matcher.group(1);
            String matcher_2 = matcher.group(2);

            System.out.println("matcher_0 = " + matcher_0);
            System.out.println("matcher_1 = " + matcher_1);
            System.out.println("matcher_2 = " + matcher_2);
        } else
            throw new RuntimeException("search result page url can not match regex example");

        LOGGER.begin().info("run test method {{testGetPreviousPageUrl}} complete");
    }

    @Test
    public void testGetPreviousAndNextPageUrl() {
        LOGGER.begin().info("run test method {{testGetPreviousAndNextPageUrl}} start");

        String url = TEST_RESULT_PAGE;
        StringBuffer previousIndexUrl = new StringBuffer(url);
        Matcher matcher = Pattern.compile(SEARCH_RESULT_REGEX).matcher(url);

        if (matcher.find()) {

            String currentIndex = matcher.group(1);
            String previousIndex = new Integer(Integer.parseInt(currentIndex) - 1).toString();
            String nextIndex = new Integer(Integer.parseInt(currentIndex) + 1).toString();

            String previousIndexUrlStr, nextIndexUrlStr;
            previousIndexUrlStr = previousIndexUrl.replace(matcher.start(1), matcher.end(1), previousIndex).toString();
            nextIndexUrlStr = previousIndexUrl.replace(matcher.start(1), matcher.end(1), nextIndex).toString();

            System.out.println("previous url: ");
            System.out.println(previousIndexUrlStr);

            System.out.println("next url: ");
            System.out.println(nextIndexUrlStr);

        } else
            throw new RuntimeException("search result page url can not match regex example");

        LOGGER.begin().info("run test method {{testGetPreviousAndNextPageUrl}} complete");
    }
}
