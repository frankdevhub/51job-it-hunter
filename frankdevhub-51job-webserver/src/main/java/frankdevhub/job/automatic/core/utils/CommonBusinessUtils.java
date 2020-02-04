package frankdevhub.job.automatic.core.utils;

import frankdevhub.job.automatic.core.constants.BusinessConstants;
import frankdevhub.job.automatic.core.enums.CharacterEncode;
import frankdevhub.job.automatic.core.exception.IllegalArgumentException;
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
public class CommonBusinessUtils {

    public static String getRuntimeMethodName(Integer trace) {
        StackTraceElement[] stackTrace = new Exception().getStackTrace();
        String methodName = stackTrace[trace].getMethodName();
        System.out.println("current method name: " + methodName);

        return methodName;
    }

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
                System.out.println("invoke->getCharacterAttributes::" + name + " , "
                        + "value = " + value);

            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                System.err.println("!!!!ERROR!!!::invoke->getCharacterAttributes::" + name + "");

                continue;
            }
            attributes.put(name, value);

        }
        return attributes;
    }

    public static Boolean isChineseCharacter(Character character) {
        Assert.notNull(character, BusinessConstants.CHARACTER_NULL_ARGUMENT);
        String regex = "[\\u4E00-\\u9FA5]";
        Matcher matcher = Pattern.compile(regex).matcher(character.toString());
        if (matcher.find())
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public static Boolean isSimpleChineseCharacter(Character character) throws UnsupportedEncodingException, IllegalArgumentException {
        Boolean isCNChar = isChineseCharacter(character);
        String characterEncode = CharacterEncode.GB2312.getCodeName();
        if (isCNChar) {
            if (new String(character.toString().getBytes(characterEncode), characterEncode).equals(character.toString()))
                return Boolean.TRUE;
            return Boolean.FALSE;
        } else
            return Boolean.FALSE;
    }

    public static Boolean isTaiwaneseCharacter(Character character) throws UnsupportedEncodingException, IllegalArgumentException {
        Boolean match = isChineseCharacter(character);
        if (!match)
            return Boolean.FALSE;
        else
            match = !isSimpleChineseCharacter(character);
        return match;
    }

    public static Boolean isEnglishCharacter(Character character) {
        Assert.notNull(character, BusinessConstants.CHARACTER_NULL_ARGUMENT);
        String regex = "[a-zA-Z]";
        Matcher matcher = Pattern.compile(regex).matcher(character.toString());
        Boolean match = matcher.find();
        return match;
    }

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

    public static Boolean isNumericCharacter(Character character) {
        Assert.notNull(character, BusinessConstants.CHARACTER_NULL_ARGUMENT);
        String regex = "[0-9]";
        Matcher matcher = Pattern.compile(regex).matcher(character.toString());
        Boolean match = matcher.find();
        return match;
    }

    public static Boolean isSymbolCharacter(Character character) {
        Assert.notNull(character, BusinessConstants.CHARACTER_NULL_ARGUMENT);
        String regex = "[a-zA-Z0-9\\u4E00-\\u9FA5]";
        Matcher matcher = Pattern.compile(regex).matcher(character.toString());
        Boolean match = !matcher.find();
        return match;
    }
}
