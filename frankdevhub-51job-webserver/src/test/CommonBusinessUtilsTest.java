import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import frankdevhub.job.automatic.core.utils.CommonBusinessUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Method;

/**
 * <p>Title:@ClassName CommonBusinessUtilsTest.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/1/29 5:10
 * @Version: 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class CommonBusinessUtilsTest {

    private final Logger LOGGER = LoggerFactory.getLogger(CommonBusinessUtilsTest.class);

    private void printTestResult(Boolean res, Character c) {
        System.out.println("Test Character = " + c + " Test Result: " + res.toString() + "");
    }

    @Test
    public void testNumericUnitConstructor() {
        LOGGER.begin().info("run test method {{testNumericUnitConstructor}} start");

        LOGGER.begin().info("run test method {{testNumericUnitConstructor}} start");
    }

    @Test
    public void testGetRuntimeMethodName() {
        LOGGER.begin().info("run test method {{testGetRuntimeMethodName}} start");

        Integer defineTrace = 1;
        String methodName = CommonBusinessUtils.getRuntimeMethodName(defineTrace);
        System.out.println("test result-> current runtime method name: " + methodName);

        LOGGER.begin().info("run test method {{testGetRuntimeMethodName}} complete");
    }


    @Test
    public void testGetBusinessUtilsDeclaredMethods() {
        LOGGER.begin().info("run test method {{getBusinessUtilsDeclaredMethods}} start");
        Class<?> clazz = CommonBusinessUtils.class;
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods)
            System.out.println("method name: " + m.getName());

        LOGGER.begin().info("run test method {{getBusinessUtilsDeclaredMethods}} complete");
    }

    @Test
    public void testIsSimpleChinese() {
        LOGGER.begin().info("run test method {{testIsSimpleChinese}} start");

        Character CN_CHAR = '个';
        Character TW_CHAR = '個';
        Character EN_CHAR = 'h';
        Character NUM_CHAR = '3';

        Boolean value = null;
        try {
            value = CommonBusinessUtils.isSimpleChineseCharacter(CN_CHAR);
        } catch (Exception e) {
            e.printStackTrace();
            value = Boolean.FALSE;
        } finally {
            printTestResult(value, CN_CHAR);
        }
        try {
            value = CommonBusinessUtils.isSimpleChineseCharacter(TW_CHAR);
        } catch (Exception e) {
            e.printStackTrace();
            value = Boolean.FALSE;
        } finally {
            printTestResult(value, TW_CHAR);
        }


        try {
            value = CommonBusinessUtils.isSimpleChineseCharacter(EN_CHAR);
        } catch (Exception e) {
            e.printStackTrace();
            value = Boolean.FALSE;
        } finally {
            printTestResult(value, EN_CHAR);
        }

        try {
            value = CommonBusinessUtils.isSimpleChineseCharacter(NUM_CHAR);
        } catch (Exception e) {
            e.printStackTrace();
            value = Boolean.FALSE;
        } finally {
            printTestResult(value, NUM_CHAR);
        }


        LOGGER.begin().info("run test method {{testIsSimpleChinese}} complete");
    }

    @Test
    public void testIsSymbolCharacter() {
        LOGGER.begin().info("run test method {{testIsSymbolCharacter}} start");
        Character[] characters = new Character[]{'=', '-', '.', '{', '【', '、', '~', '`', '·', '2', 'o', '个'};
        for (Character c : characters) {
            Boolean value = CommonBusinessUtils.isSymbolCharacter(c);
            System.out.println("char = " + c + " value = " + value);
        }

        LOGGER.begin().info("run test method {{testIsSymbolCharacter}} complete");

    }

}
