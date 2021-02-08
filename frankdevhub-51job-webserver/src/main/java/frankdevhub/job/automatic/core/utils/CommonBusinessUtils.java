package frankdevhub.job.automatic.core.utils;

import frankdevhub.job.automatic.core.constants.BusinessConstants;
import frankdevhub.job.automatic.core.enums.CharacterEncode;
import tk.mybatis.mapper.util.Assert;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title:@ClassName CommonBusinessUtils.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/1/28 23:36
 * @Version: 1.0
 */

@SuppressWarnings("all")
public class CommonBusinessUtils {

    /**
     * 获取运行时方法名
     *
     * @param trace 运行时堆栈深度
     */
    public static String getRuntimeMethodName(Integer trace) {
        StackTraceElement[] stackTrace = new Exception().getStackTrace();
        String methodName = stackTrace[trace].getMethodName();
        return methodName;
    }

    /**
     * 获取字符属性集合
     *
     * @param character 字符对象
     */
    public static final Map<String, Boolean> getCharacterAttributes(Character character) {
        CommonBusinessUtils utils = new CommonBusinessUtils();
        Class<?> clazz = CommonBusinessUtils.class;

        String currentMethodName = CommonBusinessUtils.getRuntimeMethodName(1);
        Map<String, Boolean> attributes = new HashMap<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            if (m.getName().equals(currentMethodName) || !m.getName().contains("Character".trim()))
                continue;

            m.setAccessible(true);
            String name = m.getName();
            Boolean value;
            try {
                value = (Boolean) m.invoke(utils, character);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                continue;
            }
            attributes.put(name, value);
        }

        return attributes;
    }

    /**
     * 是否是中文字符
     *
     * @param character 字符对象
     * @return 布尔值
     */
    public static Boolean isChineseCharacter(Character character) {
        Assert.notNull(character, BusinessConstants.CHARACTER_NULL_ARGUMENT);
        String regex = "[\\u4E00-\\u9FA5]";
        Matcher matcher = Pattern.compile(regex).matcher(character.toString());
        if (matcher.find())
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    /**
     * 是否是简体中文字符
     *
     * @param character 字符对象
     * @return 布尔值
     * @throws UnsupportedEncodingException
     */
    public static Boolean isSimpleChineseCharacter(Character character) throws UnsupportedEncodingException {
        Boolean isCNChar = isChineseCharacter(character);
        String characterEncode = CharacterEncode.GB2312.getCodeName();
        if (isCNChar) {
            if (new String(character.toString().getBytes(characterEncode), characterEncode).equals(character.toString()))
                return Boolean.TRUE;
            return Boolean.FALSE;
        } else
            return Boolean.FALSE;
    }

    /**
     * 是否是繁体中文字符
     *
     * @param character 字符对象
     * @return 布尔值
     * @throws UnsupportedEncodingException
     */
    public static Boolean isTaiwaneseCharacter(Character character) throws UnsupportedEncodingException {
        Boolean isCNChar = isChineseCharacter(character);
        String characterEncode = CharacterEncode.Big5.getCodeName();
        if (isCNChar) {
            if (new String(character.toString().getBytes(characterEncode), characterEncode).equals(character.toString()))
                return Boolean.TRUE;
            return Boolean.FALSE;
        } else
            return Boolean.FALSE;
    }

    /**
     * 是否是英文字符
     *
     * @param character 字符对象
     * @return 布尔值
     * @throws UnsupportedEncodingException
     */
    public static Boolean isEnglishCharacter(Character character) {
        Assert.notNull(character, BusinessConstants.CHARACTER_NULL_ARGUMENT);
        String regex = "[a-zA-Z]";
        Matcher matcher = Pattern.compile(regex).matcher(character.toString());
        Boolean match = matcher.find();
        return match;
    }

    /**
     * 是否是英文大写字符
     *
     * @param character 字符对象
     * @return 布尔值
     * @throws UnsupportedEncodingException
     */
    public static Boolean isENCapitalCharacter(Character character) {
        Boolean isENChar = isEnglishCharacter(character);
        if (isENChar) {
            String regex = "[a-z]";
            Matcher matcher = Pattern.compile(regex).matcher(character.toString());
            if (matcher.find())
                return Boolean.FALSE;
            return Boolean.TRUE;
        } else
            return Boolean.FALSE;
    }

    /**
     * 是否是数值类字符
     *
     * @param character 字符对象
     * @return 布尔值
     */
    public static Boolean isNumericCharacter(Character character) {
        Assert.notNull(character, BusinessConstants.CHARACTER_NULL_ARGUMENT);
        String regex = "[0-9]";
        Matcher matcher = Pattern.compile(regex).matcher(character.toString());
        Boolean match = matcher.find();
        return match;
    }

    /**
     * 是否是符号类字符
     *
     * @param character 字符对象
     * @return 布尔值
     */
    public static Boolean isSymbolCharacter(Character character) {
        Assert.notNull(character, BusinessConstants.CHARACTER_NULL_ARGUMENT);
        String regex = "[a-zA-Z0-9\\u4E00-\\u9FA5]";
        Matcher matcher = Pattern.compile(regex).matcher(character.toString());
        Boolean match = !matcher.find();
        return match;
    }

}
