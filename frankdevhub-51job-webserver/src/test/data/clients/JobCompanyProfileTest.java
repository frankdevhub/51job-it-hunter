package data.clients;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import frankdevhub.job.automatic.web.clients.PlatformWebClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import tk.mybatis.mapper.util.Assert;

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
        PlatformWebClient.getPageHtmlText(TEST_PAGE);
    }

    /**
     * 测试Htmlunit获取企业信息介绍页面html源码字符串
     *
     * @param url 企业介绍链接
     */

    private void testGetPageHtmlTextByHtmlUnit(String url) throws IOException {

        WebClient webClient = new WebClient(BrowserVersion.CHROME); //新建一个模拟谷歌Chrome浏览器的浏览器客户端对象
        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);//是否启用CSS, 因为不需要展现页面, 所以不需要启用
        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX
        //获取页面对象
        HtmlPage page = webClient.getPage(url);
        webClient.waitForBackgroundJavaScript(3000);//异步JS执行需要耗时,所以这里线程要阻塞30秒,等待异步JS执行结束

        //获取页面对象的字符串源码
        String context = page.asXml();//直接将加载完成的页面转换成xml格式的字符串
        //System.out.println(context);
        //测试获取公司信息介绍
        //div class = 'tCompany_center clearfix'
        HtmlDivision div = page.getFirstByXPath("//div[@class='tCompany_center clearfix']");
        Assert.notNull(div, "cannot find element by path '//div[@class='tCompany_center clearfix']'");
        //获取html源码
        String ctx = div.asXml();
        //测试调用js获取下一页
        //javascript:onPage('2');
        //ScriptResult scriptResult = page.executeJavaScript("onPage('2')");
        //Assert.notNull(scriptResult, "cannot find scriptResult");
        //page.refresh(); //页面对象刷新
        //context = page.asXml();
        System.out.println(ctx);

        //释放浏览器对象
        webClient.close();

    }


    /**
     * 测试Htmlunit获取企业信息介绍页面html源码字符串
     */
    @Test
    public void testGetPageHtmlTextByHtmlUnit() throws IOException {
        //https://jobs.51job.com/all/co142799.html
        //https://jobs.51job.com/all/co5726201.html
        //https://jobs.51job.com/all/co5937475.html
        //https://jobs.51job.com/all/co5567865.html
        //https://jobs.51job.com/all/co5235448.html
        //https://jobs.51job.com/all/co2758227.html
        String example = "https://jobs.51job.com/all/co5440648.html";
        testGetPageHtmlTextByHtmlUnit(TEST_PAGE);
    }

}
