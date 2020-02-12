package utils;

import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>Title:@ClassName WebDriverInstallerTest.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/13 5:17
 * @Version: 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class WebDriverInstallerTest {

    private final Logger LOGGER = LoggerFactory.getLogger(WebDriverInstallerTest.class);

    @Test
    public void testGetOperatingSystem() {
        LOGGER.begin().info("runt test method {{testGetOperatingSystem}} start");

        LOGGER.begin().info("runt test method {{testGetOperatingSystem}} complete");
    }

}
