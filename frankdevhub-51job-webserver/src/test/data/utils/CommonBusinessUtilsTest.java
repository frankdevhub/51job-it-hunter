package data.utils;

import frankdevhub.job.automatic.core.utils.CommonBusinessUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * <p>Title:@ClassName selenium.utils.CommonBusinessUtilsTest.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/1/29 5:10
 * @Version: 1.0
 */

@Slf4j
@SuppressWarnings("all")
public class CommonBusinessUtilsTest {

    private void printTestResult(Boolean res, Character c) {
        log.info("Test Character = " + c + " Test Result: " + res.toString() + "");
    }

    /**
     * 测试由堆栈索引获取对象内的方法名称
     */
    @Test
    public void testGetRuntimeMethodName() {
        Integer defineTrace = 1; //定义堆栈深度
        String methodName = CommonBusinessUtils.getRuntimeMethodName(defineTrace);
        log.info("test result-> current runtime method name: " + methodName);
    }

    /**
     * 测试获取对象内声明的方法
     */
    @Test
    public void testGetBusinessUtilsDeclaredMethods() {
        Class<?> clazz = CommonBusinessUtils.class;
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            log.info("method name: " + m.getName());
        }
    }

    /**
     * 测试判断是否是中文简体字符
     */
    @Test
    public void testIsSimpleChinese() {
        Character CN_CHAR = '个';
        Character TW_CHAR = '個';
        Character EN_CHAR = 'h';
        Character NUM_CHAR = '3';
        Boolean value = null;
        //判断是否是简体中文字符
        try {
            value = CommonBusinessUtils.isSimpleChineseCharacter(CN_CHAR);
        } catch (Exception e) {
            e.printStackTrace();
            value = Boolean.FALSE;
        } finally {
            printTestResult(value, CN_CHAR);
        }
        //判断是否是繁体中文字符
        try {
            value = CommonBusinessUtils.isSimpleChineseCharacter(TW_CHAR);
        } catch (Exception e) {
            e.printStackTrace();
            value = Boolean.FALSE;
        } finally {
            printTestResult(value, TW_CHAR);
        }
        //判断是否是英文字符
        try {
            value = CommonBusinessUtils.isSimpleChineseCharacter(EN_CHAR);
        } catch (Exception e) {
            e.printStackTrace();
            value = Boolean.FALSE;
        } finally {
            printTestResult(value, EN_CHAR);
        }
        //判断是否数值类型的字符
        try {
            value = CommonBusinessUtils.isSimpleChineseCharacter(NUM_CHAR);
        } catch (Exception e) {
            e.printStackTrace();
            value = Boolean.FALSE;
        } finally {
            printTestResult(value, NUM_CHAR);
        }
    }

    /**
     * 测试是否是非文学数字类制度
     */
    @Test
    public void testIsSymbolCharacter() {
        Character[] characters = new Character[]{'=', '-', '.', '{', '【', '、', '~', '`', '·', '2', 'o', '个' };
        for (Character c : characters) {
            Boolean value = CommonBusinessUtils.isSymbolCharacter(c);
            log.info("char = " + c + " value = " + value);
        }
    }

}
