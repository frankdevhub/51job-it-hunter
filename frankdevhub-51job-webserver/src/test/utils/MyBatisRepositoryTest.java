package utils;

import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import frankdevhub.job.automatic.core.repository.MyBatisRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;

/**
 * <p>Title:@ClassName MyBatisRepositoryTest.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/8 6:13
 * @Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class MyBatisRepositoryTest {

    private final Logger LOGGER = LoggerFactory.getLogger(MyBatisRepositoryTest.class);

    private final MyBatisRepository myBatisRepository = new MyBatisRepository();

    @Test
    public void testGetTimeStampRange() {
        LOGGER.begin().info("run test method {{testGetTimeStampRange}} start");
        Long current = System.currentTimeMillis();
        Long[] range = myBatisRepository.getTimeStampRange(current);

        Long zeroT = range[0];
        Long endT = range[1];

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH::mm:ss");
        String zeroTString = dateFormat.format(zeroT);
        String endTString = dateFormat.format(endT);

        System.out.println("zeroTString = " + zeroTString + "");
        System.out.println("endTString = " + endTString + "");

        LOGGER.begin().info("run test method {{testGetTimeStampRange}} complete");
    }
}
