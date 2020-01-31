import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>Title:@ClassName ChromeConfigurationTest.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/1/31 1:11
 * @Version: 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class ChromeConfigurationTest {

    private final Logger LOGGER = LoggerFactory.getLogger(ChromeConfigurationTest.class);



    @Before
    public void init() {
        LOGGER.begin().info("junit test for ChromeConfigurationTest init");

    }

    @After
    public void after() {

    }
}
