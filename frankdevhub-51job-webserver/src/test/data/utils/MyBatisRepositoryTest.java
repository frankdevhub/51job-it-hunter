package data.utils;

import frankdevhub.job.automatic.core.repository.MyBatisRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

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
public class MyBatisRepositoryTest {
    private final MyBatisRepository myBatisRepository = new MyBatisRepository();

    @Test
    public void testGetTimeStampRange_1() {
        int year = 2019;
        int month = 12;
        int day = 15;
        Long[] range = myBatisRepository.getTimeStampRange(year, month, day);
        Long zeroT = range[0];
        Long endT = range[1];

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String zeroTString = dateFormat.format(zeroT);
        String endTString = dateFormat.format(endT);
        log.info("zeroTString = " + zeroTString + "");
        log.info("endTString = " + endTString + "");
    }


    @Test
    public void testGetTodayTimeStampRange() {
        Long[] range = myBatisRepository.getTodayTimeStampRange();
        Long zeroT = range[0];
        Long endT = range[1];

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String zeroTString = dateFormat.format(zeroT);
        String endTString = dateFormat.format(endT);

        log.info("zeroTString = " + zeroTString + "");
        log.info("endTString = " + endTString + "");
    }

    @Test
    public void testGetTimeStampRange() {
        Long current = System.currentTimeMillis();
        Long[] range = myBatisRepository.getTimeStampRange(current);

        Long zeroT = range[0];
        Long endT = range[1];
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String zeroTString = dateFormat.format(zeroT);
        String endTString = dateFormat.format(endT);
        log.info("zeroTString = " + zeroTString + "");
        log.info("endTString = " + endTString + "");
    }
}
