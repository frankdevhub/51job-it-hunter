package pages;

import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

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

    private final Logger LOGGER = LoggerFactory.getLogger(JobPlatformSearchPageMainTest_1.class);

    private static final String TEST_RESULT_PAGE = "https://search.51job.com/list/020000,000000,0000,00,9,99,java,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";

    @Test
    public void testHttpClientGetResultPage() throws IOException {
        LOGGER.begin().info("run test method {{testHttpClientGetResultPage}} start");

        Long start = System.currentTimeMillis();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(TEST_RESULT_PAGE);
        httpGet.setHeader("Content-Type", "text/html; charset=UTF-8");

        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity responseEntity = response.getEntity();
        String responseText = EntityUtils.toString(responseEntity, "GBK");

        Long end = System.currentTimeMillis();
        System.out.println(String.format("time cost: %s sec", (end - start) / 1000));

        System.out.println(responseText);

        LOGGER.begin().info("run test method {{testHttpClientGetResultPage}} complete");
    }
}
