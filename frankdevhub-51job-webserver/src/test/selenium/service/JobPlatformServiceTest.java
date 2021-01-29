package selenium.service;

import frankdevhub.job.automatic.JobWebAutoService;
import frankdevhub.job.automatic.web.clients.JobPlatformService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Title: ExtractServiceTest
 * @Description: //TODO
 * @date: 2021/1/28 11:25
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */


@Slf4j
@SpringBootTest(classes = JobWebAutoService.class)
public class JobPlatformServiceTest {

    private final String DEFAULT_SEARCH_JAVA = "https://search.51job.com/list/020000,000000,0000,00,9,99,java,2,1.html?" +  //搜索关键字java
            "lang=c&postchannel=0000&workyear=99" +
            "&cotype=99&degreefrom=99" +  //cotype
            "&jobterm=99" +   //jobterm
            "&companysize=99" +  //companysize
            "&ord_field=0" +  //ord_field
            "&dibiaoid=0" + //dibiaoid
            "&line=" + //line
            "&welfare=";  //welfare

    /**
     * 测试jsoup爬虫模式抽取简历搜索返回信息并进行批量解析
     *
     * @throws InterruptedException
     */
    @Test
    public void testJobPlatformService() throws InterruptedException {
        log.info("testJobPlatformService start");
        new JobPlatformService().defaultDataPatrolService(DEFAULT_SEARCH_JAVA);
    }
}
