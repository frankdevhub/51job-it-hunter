package clients;

import frankdevhub.job.automatic.JobWebAutoService;
import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import frankdevhub.job.automatic.web.clients.JobPlatformService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>Title:@ClassName JobPlatformServiceTest.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/25 15:24
 * @Version: 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JobWebAutoService.class)
public class JobPlatformServiceTest {

    private final Logger LOGGER = LoggerFactory.getLogger(JobPlatformServiceTest.class);
    private final String TEST_RESULT_PAGE = "https://search.51job.com/list/020000,000000,0000,00,9,99,java,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";

    @Test
    public void testDefaultDataPatrolService() {
        System.out.println("thread name = " + Thread.currentThread().getName());
        LOGGER.begin().info("run test method {{testGetJobSearchResult}} start");

        JobPlatformService service = new JobPlatformService();
        try {
            service.defaultDataPatrolService(TEST_RESULT_PAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LOGGER.begin().info("run test method {{testGetJobSearchResult}} complete");
    }
}
