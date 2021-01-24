package utils;

import frankdevhub.job.automatic.core.repository.MyBatisRepository;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class MyBatisRepositoryTest {
    private final MyBatisRepository myBatisRepository = new MyBatisRepository();

    @Test
    public void testGetTimeStampRange_1() {
        log.info("run test method {{testGetTimeStampRange_1}} start");
        int year = 2019;
        int month = 12;
        int day = 15;

        Long[] range = myBatisRepository.getTimeStampRange(year, month, day);
        Long zeroT = range[0];
        Long endT = range[1];

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String zeroTString = dateFormat.format(zeroT);
        String endTString = dateFormat.format(endT);

        System.out.println("zeroTString = " + zeroTString + "");
        System.out.println("endTString = " + endTString + "");

        log.info("run test method {{testGetTimeStampRange_1}} complete");
    }


    @Test
    public void testGetTodayTimeStampRange() {
        log.info("run test method {{testGetTodayTimeStampRange}} start");
        Long[] range = myBatisRepository.getTodayTimeStampRange();

        Long zeroT = range[0];
        Long endT = range[1];

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String zeroTString = dateFormat.format(zeroT);
        String endTString = dateFormat.format(endT);

        System.out.println("zeroTString = " + zeroTString + "");
        System.out.println("endTString = " + endTString + "");


        log.info("run test method {{testGetTodayTimeStampRange}} complete");
    }

    @Test
    public void testGetTimeStampRange() {
        log.info("run test method {{testGetTimeStampRange}} start");
        Long current = System.currentTimeMillis();
        Long[] range = myBatisRepository.getTimeStampRange(current);

        Long zeroT = range[0];
        Long endT = range[1];

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String zeroTString = dateFormat.format(zeroT);
        String endTString = dateFormat.format(endT);

        System.out.println("zeroTString = " + zeroTString + "");
        System.out.println("endTString = " + endTString + "");

        log.info("run test method {{testGetTimeStampRange}} complete");
    }
}
