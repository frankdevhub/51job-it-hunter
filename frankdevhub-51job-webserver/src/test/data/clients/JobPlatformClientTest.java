package data.clients;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import frankdevhub.job.automatic.core.constants.BusinessConstants;
import frankdevhub.job.automatic.core.utils.WebDriverUtils;
import frankdevhub.job.automatic.selenium.DriverBase;
import frankdevhub.job.automatic.selenium.config.ChromeConfiguration;
import frankdevhub.job.automatic.web.clients.JobPlatformClient;
import frankdevhub.job.automatic.web.clients.PlatformLinkBuilder;
import lombok.extern.slf4j.Slf4j;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SuppressWarnings("all")
public class JobPlatformClientTest {

    //测试页面的跳转链接地址
    private final String TEST_RESULT_PAGE = "https://search.51job.com/list/020000,000000,0000,00,9,99,java,2,1.html?" +
            "lang=c" +  //语言类型 lang
            "&stype=" + //stype
            "&postchannel=0000" + //postchannel
            "&workyear=99" +  //workyear 工作年限
            "&cotype=99" + //cotype
            "&degreefrom=99" +  //degreefrom 学历要求
            "&jobterm=99" + //jobterm
            "&companysize=99" +  //companysize 企业人数规模
            "&providesalary=99" +  //providesalary
            "&lonlat=0%2C0" + //lonlat
            "&radius=-1" + //radius
            "&ord_field=0" +  //ord_field
            "&confirmdate=9" +  //confirmdate
            "&fromType=" +  //fromType
            "&dibiaoid=0" +  //dibiaoid
            "&address=" + //address 工作岗位地址
            "&line=" + //line
            "&specialarea=00" + //specialarea 辖区编号
            "&from=" +  //from
            "&welfare=";  //welfare 薪资福利

    //测试解析的html格式字符串对象
    private final String TEST_CONTENT = "<img http://sss src=\"http://www.foo.com/a.png\">http://sss/";
    private final String SEARCH_RESULT_REGEX = "([0-9]+)(.html?)"; //匹配字符串中的html标签内容

    /**
     * 测试是否有权限读取缓存目录
     *
     * @throws IOException
     */
    @Test
    public void testAccessCacheDirectory() throws IOException {
        Resource res = new ClassPathResource("src/resources/cache/temp_cookie.dat");
        InputStream in = res.getInputStream();
        Assert.notNull(in, "resource document may not exist");
    }

    /**
     * 测试读取本地序列化的Cookie数据对象
     *
     * @throws IOException,ClassNotFoundException
     */
    @Test
    public void testReadTempCookieDocument() throws IOException, ClassNotFoundException {
        File temp = new File(new ClassPathResource("src/main/resources/cache/temp_cookie.dat").getPath());
        Assert.notNull(temp, "temp file not found");

        FileInputStream fis = new FileInputStream(temp);
        Assert.notNull(fis, "resource document may not exist");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Set<Cookie> cookies = (Set<Cookie>) ois.readObject();
        ois.close();
        fis.close();

        for (Cookie c : cookies) {
            log.info("name = " + c.getName());
            log.info("path = " + c.getPath());
            log.info("value = " + c.getValue());

            log.info("\n");
        }
    }

    /**
     * 测试读取本地序列化的session会话数据
     *
     * @throws Exception
     */
    @Test
    public void testGetPlatformCookie() throws Exception {
        ChromeConfiguration configuration = ChromeConfiguration.newInstance(false); //创建驱动配置实例,依据是否自动配置的策略
        configuration.setWebDriverPath(ChromeConfiguration.CHROME_DRIVER_PATH) //浏览器驱动路径
                .setSeleniumCacheFileName(ChromeConfiguration.DEFAULT_SELENIUM_CACHE_NAME) //默认的缓存文件目录名
                .setSeleniumBrowserCacheRoot(ChromeConfiguration.DEFAULT_WIN_SELENIUM_CACHE_ROOT); //默认的浏览器缓存目录
        //configuration.synchronizeSeleniumBrowserCache();
        DriverBase.instantiateDriverObject();

        log.info("using cache path: " + configuration.getSeleniumCacheDirectoryPath());
        WebDriver driver = DriverBase.getDriver(configuration.getSeleniumCacheDirectoryPath());
        Assert.notNull(driver, "web driver not found");
       /* //clear default history cookies
       log.info("clear all restored cookies");
        driver.manage().deleteAllCookies();*/
        //跳转到测试连接地址
        driver.get(BusinessConstants.JOB_PLATFORM_HOMEPAGE);
        //判断是否跳转到目标的首页
        WebDriverUtils.doWaitTitleContains("招聘", new WebDriverWait(driver, 3));
        log.info("navigate to platform homepage complete");
        log.info("login with user credential");
        //测试计量cookie对象的读取的时间
        log.info("start to get web cookie");
        Long start = System.currentTimeMillis();
        Set<Cookie> cookies = driver.manage().getCookies();
        Long end = System.currentTimeMillis();

        log.info("time cost: " + (end - start) + " ms");
        log.info("cookie properties:");
        for (Cookie c : cookies) {
            log.info("name = " + c.getName());
            log.info("path = " + c.getPath());
            log.info("value = " + c.getValue());
            log.info("\n");
        }
        //restore cookie to cache directory
        //加载并读取本地存储的会话文件
        File temp = new File(new ClassPathResource("/src/main/resources/cache/temp_cookie.dat").getPath());
        if (!temp.exists())
            temp.createNewFile(); //如果不存在则重新创建一个存储的临时文件
        FileOutputStream fos = new FileOutputStream(temp);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Assert.notNull(cookies, "cookies object should not be null");
        oos.writeObject(cookies); //序列化存储会话对象
        oos.flush(); //清空对象输出流
        fos.flush(); //清空文件输出流
        oos.close(); //关闭对象输出流
        fos.close(); //关闭文件输出流

    }

    /**
     * 测试解析返回的搜索结果集页面
     *
     * @throws IOException,XpathSyntaxErrorException
     */
    @Test
    public void testGetJobSearchResult() throws IOException, XpathSyntaxErrorException {
        JobPlatformClient client = new JobPlatformClient();
        client.getJobSearchResult(TEST_RESULT_PAGE);
    }

    /**
     * 测试解析html格式字符串获取源代码内容
     */
    @Test
    public void testRegexGroupReplace() {
        String content = TEST_CONTENT;
        log.info(content);
        String pattern = "<img\\s*([^>]*)\\s*src=\\\"(http://.*?/)(.*?)\\\"\\s*([^>]*)>(http://.*?/)";
        StringBuffer operatorStr = new StringBuffer(content);
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(content);
        while (m.find()) {
            operatorStr.replace(m.start(2), m.end(2), "/");
            m = p.matcher(operatorStr);
        }
        log.info(operatorStr.toString());
    }

    /**
     * 测试解析地址链接
     */
    @Test
    public void testPageUrlRegex() {
        String url = TEST_RESULT_PAGE;
        Matcher matcher = Pattern.compile(SEARCH_RESULT_REGEX).matcher(url);
        if (matcher.find()) {
            String matcher_0 = matcher.group(0);
            String matcher_1 = matcher.group(1);
            String matcher_2 = matcher.group(2);
            log.info("matcher_0 = " + matcher_0);
            log.info("matcher_1 = " + matcher_1);
            log.info("matcher_2 = " + matcher_2);
        } else {
            throw new RuntimeException("search result page url can not match regex example");
        }
    }

    /**
     * 测试解析获取上一页或下一页的目标页面地址
     */
    @Test
    public void testGetPreviousAndNextPageUrl() {
        String url = TEST_RESULT_PAGE;
        StringBuffer previousIndexUrl = new StringBuffer(url);
        Matcher matcher = Pattern.compile(SEARCH_RESULT_REGEX).matcher(url);
        if (matcher.find()) {
            String currentIndex = matcher.group(1); //当前页的索引
            String previousIndex = new Integer(Integer.parseInt(currentIndex) - 1).toString(); //上一页的索引
            String nextIndex = new Integer(Integer.parseInt(currentIndex) + 1).toString(); //下一页的索引
            String previousIndexUrlStr, nextIndexUrlStr;
            previousIndexUrlStr = previousIndexUrl.replace(matcher.start(1), matcher.end(1), previousIndex).toString();
            nextIndexUrlStr = previousIndexUrl.replace(matcher.start(1), matcher.end(1), nextIndex).toString();

            log.info("previous url: ");
            log.info(previousIndexUrlStr);
            log.info("next url: ");
            log.info(nextIndexUrlStr);
        } else {
            throw new RuntimeException("search result page url can not match regex example");
        }
    }

    private class DefaultDataPatrolThread extends Thread {
        private final ExecutorService cachedThreadPool = Executors.newCachedThreadPool(); //扫描网页的线程池
        private final JobPlatformClient client = new JobPlatformClient(); //客户端业务逻辑对象
        private String url; //页面链接

        public DefaultDataPatrolThread(String url) {
            Assert.notNull(url, "cannot find url");
            this.url = url;
        }

        @Override
        public void run() {
            log.info("[defaultDataPatrolService --> task::thread]thread name = "
                    + Thread.currentThread().getName());
            log.info("default data patrol thread start");
            while (true) {
                try {
                    log.info("@current url = " + url);
                    client.restorePageJobSearchResult(url, cachedThreadPool);
                    //依据链接规则获取下一页的链接
                    url = PlatformLinkBuilder.getNextResultPage(url);
                    log.info("@next url = " + url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 默认的扫描业务逻辑
     *
     * @param url 页面链接
     * @throws InterruptedException
     */
    public void defaultDataPatrolService(String url) throws InterruptedException {
        Runnable task = () -> {
            log.info("[defaultDataPatrolService --> task]thread name = "
                    + Thread.currentThread().getName());
            Thread t = new JobPlatformClientTest.DefaultDataPatrolThread(url);
            t.setDaemon(true);
            t.start();
        };
        Thread t = new Thread(task);
        log.info("thread t->name =" + t.getName());
        t.setDaemon(true);
        t.start();
        Thread.sleep(200L);
    }
}
