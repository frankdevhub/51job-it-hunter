package data.clients;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import frankdevhub.job.automatic.web.clients.PlatformHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;

/**
 * @Title: JobCompanyProfileTest
 * @Description: 测试企业介绍信息解析
 * @date: 2021/2/1 11:55
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */

@Slf4j
@SuppressWarnings("all")
public class JobCompanyProfileTest {

    private final String TEST_PAGE = "https://jobs.51job.com/all/co2265273.html"; //测试公司信息链接

    /**
     * 测试HttpClient获取企业信息介绍页面html源码字符串
     *
     * @throws IOException
     */
    @Test
    public void testGetPageHtmlTextByHttpCLient() throws IOException {
        PlatformHttpClient.getPageHtmlText(TEST_PAGE);
    }


    /**
     * 测试Htmlunit获取企业信息介绍页面html源码字符串
     */
    @Test
    public void testGetPageHtmlTextByHtmlUnit() throws IOException {

        WebClient webClient = new WebClient(BrowserVersion.CHROME); //新建一个模拟谷歌Chrome浏览器的浏览器客户端对象
        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);//是否启用CSS, 因为不需要展现页面, 所以不需要启用
        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX
        //获取页面对象
        HtmlPage page = webClient.getPage(TEST_PAGE);
        webClient.close();
        webClient.waitForBackgroundJavaScript(30000);//异步JS执行需要耗时,所以这里线程要阻塞30秒,等待异步JS执行结束
        //获取页面对象的字符串源码
        String context = page.asXml();//直接将加载完成的页面转换成xml格式的字符串
        System.out.println(context);

    }

    /**
     * 获取企业信息介绍页面相关信息
     *
     * @throws IOException
     */
    @Test
    public void testGetPageContext() throws IOException {
        String context = PlatformHttpClient.getPageHtmlText(TEST_PAGE);
    }
}
