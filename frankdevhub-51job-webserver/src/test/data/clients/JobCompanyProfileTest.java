package data.clients;

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


    @Test
    public void testGetPageHtmlText() throws IOException {
        PlatformHttpClient.getPageHtmlText(TEST_PAGE);
    }
}
