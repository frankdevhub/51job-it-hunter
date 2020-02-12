package utils;

import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import frankdevhub.job.automatic.core.enums.NumericUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>Title:@ClassName NumericUnitTest.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/8 18:53
 * @Version: 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class NumericUnitTest {

    private final Logger LOGGER = LoggerFactory.getLogger(NumericUnitTest.class);

    @Test
    public void testGetNumericUnit() {
        LOGGER.begin().info("run test method {{testGetNumericUnit}} start");
        System.out.println("init enum numeric unit objects");
        NumericUnit example = NumericUnit.Digitis_CN;

        System.out.println("\n\n");

        System.out.println("print test example properties");
        example.toString();

        example = NumericUnit.Digitis_TW;
        example.toString();

        example = NumericUnit.Ten_Digitis_CN_TW;
        example.toString();

        example = NumericUnit.Ten_Digitis_CN_TW;
        example.toString();

        example = NumericUnit.Hundred_CN;
        example.toString();

        example = NumericUnit.Hundred_TW;
        example.toString();

        example = NumericUnit.Thousand_CN_TW;
        example.toString();

        example = NumericUnit.Thousand_CN_TW;
        example.toString();

        example = NumericUnit.Thousand_EN;
        example.toString();

        example = NumericUnit.Ten_Thousand_CN;
        example.toString();

        example = NumericUnit.Ten_Thousand_TW;
        example.toString();

        example = NumericUnit.Ten_Thousand_EN;
        example.toString();

        LOGGER.begin().info("run test method {{testGetNumericUnit}} complete");
    }
}
