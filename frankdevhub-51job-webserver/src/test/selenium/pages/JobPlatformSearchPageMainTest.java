package selenium.pages;

import frankdevhub.job.automatic.JobWebAutoService;
import frankdevhub.job.automatic.web.pages.JobPlatformSearchPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
@Slf4j
@SpringBootTest(classes = JobWebAutoService.class)
public class JobPlatformSearchPageMainTest {

    private final String TEST_SEARCH_KEY = "java"; //职位搜索关键字

    /**
     * 测试扫描搜索结果集页面
     *
     * @throws InterruptedException
     */
    @Test
    public void testPatrolTestOnePageOnly() throws InterruptedException {
        log.info("run test method {{doPatrolTestOnePageOnly}} start");
        JobPlatformSearchPage patrolService = new JobPlatformSearchPage(Boolean.FALSE, TEST_SEARCH_KEY);
        patrolService.startSearchResultPatrol();
        log.info("run test method {{doPatrolTestOnePageOnly}} complete");
    }
}
