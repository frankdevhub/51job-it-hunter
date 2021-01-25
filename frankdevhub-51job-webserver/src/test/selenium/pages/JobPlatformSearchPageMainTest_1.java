package selenium.pages;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

/**
 * <p>Title:@ClassName JobPlatformSearchPageMainTest_1.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/13 22:36
 * @Version: 1.0
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class JobPlatformSearchPageMainTest_1 {

    //测试页面跳转地址
    private final String TEST_RESULT_PAGE = "https://search.51job.com/list/020000,000000,0000,00,9,99,java,2,1.html?" +
            "lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99" +
            "&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0" +
            "&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";

    private String pageContext = ""; //测试页面的内容

    /**
     * 测试前置数据:获取目标页面的源码字符串对象
     *
     * @throws IOException
     */
    @Before
    public void init() throws IOException {
        log.info("invoke {{JobPlatformSearchPageMainTest_1::init()}}");

        Long start = System.currentTimeMillis();
        String responseText = null;
        CloseableHttpClient httpClient = null;
        try {
            // httpClient = HttpClientBuilder.create().build();
            httpClient = HttpClients.createDefault(); //创建http客户端对象
            HttpGet httpGet = new HttpGet(TEST_RESULT_PAGE); //请求网页信息
            httpGet.setHeader("Content-Type", "text/html; charset=GBK"); //请求网页对象的编码格式
            CloseableHttpResponse response = httpClient.execute(httpGet); //获取返回对象
            HttpEntity responseEntity = response.getEntity();
            responseText = EntityUtils.toString(responseEntity, "GBK"); //读取返回内容的编码格式

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.close();
        }

        Long end = System.currentTimeMillis();
        log.info(String.format("time cost: %s sec", (end - start) / 1000));
        log.info(responseText);

        this.pageContext = responseText;

    }

    /**
     * jsoup解析页面测试
     */
    @Test
    public void testParseByUsingJSoupSupport() {
        log.info("run test method {{testParseByUsingJSoupSupport}} start");

        Document document = Jsoup.parse(this.pageContext); //页面对象转换为Dom文档对象
        String title = document.getElementsByTag("title").html(); //获取页面标题内容
        log.info("test :[getElementsByTag]");
        log.info("title = " + title);
        log.info("test :[get element by JSoup xpath]");
        JXDocument jxDocument = new JXDocument(document); //页面对象转换为JXDom文档对象
        try {
            //xpath解析获取目标链接的集合
            List<JXNode> jxNodes = jxDocument.selN("//div[@class=el]/p/span/a");
            log.info("get jxNodes count = " + jxNodes.size());
            for (JXNode jn : jxNodes) {
                Element el = jn.getElement();
                log.info("title =  " + el.attr("title")); //输出对象的html属性
            }
        } catch (XpathSyntaxErrorException e) {
            e.printStackTrace();
        }
        log.info("run test method {{testParseByUsingJSoupSupport}} complete");
    }
}
