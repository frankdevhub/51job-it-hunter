package data.utils;

import frankdevhub.job.automatic.core.enums.NumericUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * <p>Title:@ClassName NumericUnitTest.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Description: 测试数值类字符串的解析
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/8 18:53
 * @Version: 1.0
 */

@Slf4j
public class NumericUnitTest {

    @Test
    public void testGetNumericUnit() {
        log.info("init enum numeric unit objects");
        NumericUnit example = NumericUnit.Digitis_CN;

        log.info("\n\n");
        log.info("print test example properties");
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
    }
}
