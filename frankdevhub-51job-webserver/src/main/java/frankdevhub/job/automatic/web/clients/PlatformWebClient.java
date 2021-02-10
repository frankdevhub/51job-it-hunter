package frankdevhub.job.automatic.web.clients;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import frankdevhub.job.automatic.core.constants.BusinessConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import tk.mybatis.mapper.util.Assert;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: PlatformWebClient
 * @Description: 链接地址构造工具
 * @date: 2021/1/29 22:56
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */

@Slf4j
@SuppressWarnings("all")
public class PlatformWebClient {

    /**
     * 构建浏览器客户端获取页面对象
     *
     * @param url   网页地址
     * @param await 异步阻塞等待js加载完毕的时间
     * @throws IOException
     * @reutrn 浏览器客户端实例
     */
    public static HtmlPage getWebPage(String url, Long await) throws IOException {
        log.info("getWebPage, url  ={}, await = {}", url, await);
        Assert.notNull(url, "cannot find url");

        WebClient webClient = new WebClient(BrowserVersion.CHROME); //新建一个模拟谷歌Chrome浏览器的浏览器客户端对象
        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);//是否启用CSS, 因为不需要展现页面, 所以不需要启用
        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX
        //获取页面对象
        HtmlPage page = webClient.getPage(url);
        //默认没有配置则异步阻塞等待3秒钟
        if (null == await) {
            await = 3000L;
        }
        webClient.waitForBackgroundJavaScript(await);
        //返回配置完的浏览器客户端实例
        //注意:返回的浏览器对象仍未释放
        return page;
    }

    /**
     * Http请求获取页面对象的字符串
     *
     * @param url 页面链接地址
     * @return 页面对象的字符串
     * @throws IOException
     */
    public static String getPageHtmlText(String url) throws IOException {
        log.info("getPageHtmlText, url  ={}", url);
        Assert.notNull(url, "url cannot be found");
        String pageContext = null;
        CloseableHttpClient httpClient = null;
        Long start = System.currentTimeMillis();

        try {
            // httpClient = HttpClientBuilder.create().build();
            httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Content-Type", "text/html; charset=GBK");
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity responseEntity = response.getEntity();
            pageContext = EntityUtils.toString(responseEntity, "GBK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        Long end = System.currentTimeMillis();
        log.info(String.format("time cost: %s sec", (end - start) / 1000));
        log.info(pageContext);

        return pageContext;
    }


    /**
     * 获取上一页页面对象的字符串
     *
     * @param url 页面链接地址
     * @return 页面对象的字符串
     * @throws IOException
     */
    public static String getPreviousResultPage(String url) {
        log.info("getPreviousResultPage, url  ={}", url);
        //匹配网页元素对象
        String regex = "([0-9]+)(.html?)";
        Matcher matcher = Pattern.compile(regex).matcher(url);
        String index; //当前页数索引
        String previous; //上一页链接地址

        if (matcher.find()) {
            index = matcher.group(1);
            previous = new Integer(Integer.parseInt(index) - 1).toString();
        } else {
            throw new RuntimeException("url regex cannot match page url");
        }

        StringBuffer buffer = new StringBuffer(url);
        buffer.replace(matcher.start(1), matcher.end(1), previous);
        String previousPage = buffer.toString();
        log.info("previous page url = " + previousPage);

        return previousPage;
    }


    /**
     * 获取下一页页面对象的字符串
     *
     * @param url 页面链接地址
     * @return 页面对象的字符串
     */
    public static String getNextResultPage(String url) {
        log.info("getNextResultPage, url  ={}", url);
        //匹配网页元素对象
        String regex = "([0-9]+)(.html?)";
        Matcher matcher = Pattern.compile(regex).matcher(url);
        String index; //当前页数索引
        String next; //下一页链接地址

        if (matcher.find()) {
            index = matcher.group(1);
            next = new Integer(Integer.parseInt(index) + 1).toString();
        } else {
            throw new RuntimeException("url regex cannot match page url");
        }

        StringBuffer buffer = new StringBuffer(url);
        buffer.replace(matcher.start(1), matcher.end(1), next);
        String nextPage = buffer.toString();
        log.info("next page url = " + nextPage);

        return nextPage;
    }


    /**
     * 获取页面对象的字符串
     *
     * @param url 页面链接地址
     * @return 页面对象的字符串
     */
    public static String getSearchKeyword(String url) {
        log.info("getSearchKeyword, url  ={}", url);
        Assert.notNull(url, "cannot find url");

        //提取链接中的关键字的正则表达式
        String regex = BusinessConstants.DEFAULT_HHTP_LINK_KEYWORD_REGEX;
        Matcher matcher = Pattern.compile(regex).matcher(url);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            throw new RuntimeException("cannot match search keyword, url = " + url + "");
        }
    }

    /**
     * 获取页面链接对应的唯一标识字段
     *
     * @param url 页面链接地址
     */
    public static String getPageUnionId(String url) {
        log.info("getPageUnionId, url  ={}", url);
        Assert.notNull(url, "cannot find url");

        //提取链接中唯一标识的正则表达式
        String regex = BusinessConstants.DEFAULT_HTTP_LINK_MARK_REGEX;
        Matcher matcher = Pattern.compile(regex).matcher(url);
        if (matcher.find()) {
            return matcher.group("key");
        } else {
            throw new RuntimeException("cannot match union id url =" + url + "");
        }
    }

    public static String getPlatformQueryUrl() {
        String url = null;
        return url;
    }


}
