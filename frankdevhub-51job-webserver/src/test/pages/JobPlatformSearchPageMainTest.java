package pages;

import frankdevhub.job.automatic.JobWebAutoService;
import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import frankdevhub.job.automatic.web.pages.JobPlatformSearchPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>Title:@ClassName JobPlatformSearchPageMainTest.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/11 3:53
 * @Version: 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JobWebAutoService.class)
public class JobPlatformSearchPageMainTest {

    private final Logger LOGGER = LoggerFactory.getLogger(JobPlatformSearchPageMainTest.class);

    private final String TEST_SEARCH_KEY = "java";

    @Test
    public void doPatrolTestOnePageOnly() throws InterruptedException {
        LOGGER.begin().info("run test method {{doPatrolTestOnePageOnly}} start");

        JobPlatformSearchPage patrolService = new JobPlatformSearchPage(Boolean.FALSE, TEST_SEARCH_KEY);
        patrolService.startSearchResultPatrol();

        LOGGER.begin().info("run test method {{doPatrolTestOnePageOnly}} complete");
    }
}
