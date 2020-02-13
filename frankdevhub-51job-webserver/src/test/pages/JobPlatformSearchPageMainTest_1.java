package pages;

import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jdom2.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

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

@RunWith(SpringJUnit4ClassRunner.class)
public class JobPlatformSearchPageMainTest_1 {

    private static final String TEST_RESULT_PAGE = "https://search.51job.com/list/020000,000000,0000,00,9,99,java,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
    private final Logger LOGGER = LoggerFactory.getLogger(JobPlatformSearchPageMainTest_1.class);
    private String pageContext = "";

    @Before
    public void init() throws IOException {
        LOGGER.begin().info("invoke {{JobPlatformSearchPageMainTest_1::init()}}");

        Long start = System.currentTimeMillis();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(TEST_RESULT_PAGE);
        httpGet.setHeader("Content-Type", "text/html; charset=GBK");

        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity responseEntity = response.getEntity();
        String responseText = EntityUtils.toString(responseEntity, "GBK");

        Long end = System.currentTimeMillis();
        System.out.println(String.format("time cost: %s sec", (end - start) / 1000));

        System.out.println(responseText);

        this.pageContext = responseText;
    }

    @Test
    public void testParseByUsingJDomSupport() throws ParserConfigurationException, IOException, SAXException {
        LOGGER.begin().info("run test method {{testParseByUsingJDomSupport}} start");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
        String context = this.pageContext;
        InputStream inputStream = new ByteArrayInputStream(context.getBytes());

        //TODO
        //[Fatal Error] :10:119: The reference to entity "keyword" must end with the ';' delimiter.
        Document document = (Document) db.parse(inputStream);

        LOGGER.begin().info("run test method {{testParseByUsingJDomSupport}} complete");
    }

}
