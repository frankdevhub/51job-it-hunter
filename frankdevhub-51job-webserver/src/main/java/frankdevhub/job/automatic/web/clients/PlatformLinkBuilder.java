package frankdevhub.job.automatic.web.clients;

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
 * @Title: PlatformLinkBuilder
 * @Description: 链接地址构造工具
 * @date: 2021/1/29 22:56
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */

@Slf4j
@SuppressWarnings("all")
public class PlatformLinkBuilder {
    /**
     * 获取页面对象的字符串
     *
     * @param url 页面链接地址
     * @return 页面对象的字符串
     * @throws IOException
     */
    public static String getPageHtmlText(String url) throws IOException {

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
        String regex = "(.*),([0-9]+),([0-9]+)(.html?)";
        Matcher matcher = Pattern.compile(regex).matcher(url);

        if (matcher.find()) {
            return matcher.group(1);
        } else
            throw new RuntimeException();
    }
}
