package entities;

import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import frankdevhub.job.automatic.entities.JobSearchResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>Title:@ClassName JobSearchResultTest.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/11 5:20
 * @Version: 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class JobSearchResultTest {

    public final Logger LOGGER = LoggerFactory.getLogger(JobSearchResultTest.class);

    private JobSearchResult result_1;
    private JobSearchResult result_2;

    private final String DEFAULT_TEST_SEARCH_KEYWORD = "java";

    @Before
    public void init() {
        LOGGER.begin().info("invoke {{JobSearchResultTest::init()}}");
        this.result_1 = new JobSearchResult();
        this.result_2 = new JobSearchResult();

        //test data, only inject manually
        result_1.setSearchKeyword(DEFAULT_TEST_SEARCH_KEYWORD)
                .setResourceUrl("https://jobs.51job.com/shanghai-hpq/119640356.html?s=01&t=0")
                .setIsUnitByTenThousand(true)
                .setIsUnitByThousand(false)
                .setIsUnitByYear(true)
                .setIsUnitByMonth(false)
                .setIsUnitByDay(true)
                .setIsUnitByDay(true)
                .setSalaryRange("2.5-3.5万/月")
                .setJobTitle("Java架构师")
                .setPublishMonth(2)
                .setPublishDayOfMonth(10)
                .setIsInternshipPosition(false)
                .setSalaryMaxNumeric(2.5)
                .setSalaryMaxNumeric(3.5);


        LOGGER.begin().info("invoke {{JobSearchResultTest::init()}}");
    }

    @Test
    public void testHashCode() {
        LOGGER.begin().info("run test method {{testHashCode}} start");
        for (int i = 0; i < 3; i++) {
            System.out.println("loop index: " + i);
            int hashCode = result_1.hashCode();
            System.out.println("result_1 hashcode: " + result_1.hashCode());
            System.out.println("result_1 markId: " + result_1.getMarkId());

            result_1.setMarkId(hashCode);
            System.out.println("result_1 hashcode: " + result_1.getMarkId());
        }


        LOGGER.begin().info("run test method {{testHashCode}} complete");
    }
}
