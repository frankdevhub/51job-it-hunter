package frankdevhub.job.automatic.core.utils;

import frankdevhub.job.automatic.core.constants.BusinessConstants;
import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import frankdevhub.job.automatic.core.enums.CharacterEncode;
import frankdevhub.job.automatic.core.exception.IllegalArgumentException;
import frankdevhub.job.automatic.entities.BusinessCharacter;
import tk.mybatis.mapper.util.Assert;

import java.io.UnsupportedEncodingException;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonBusinessUtils.class);

    public static final BusinessCharacter getBusinessCharacter(Character value) {
        LOGGER.begin().info(String.format("invoke {{getBusinessCharacter}} ,character:[%s]", value));
        BusinessCharacter character = new BusinessCharacter();

        return character;
    }

    public static Boolean isChineseCharacter(Character character) {
        Assert.notNull(character, BusinessConstants.CHARACTER_NULL_ARGUMENT);
        String regex = "[\\u4E00-\\u9FA5]";
        Matcher matcher = Pattern.compile(regex).matcher(character.toString());
        if (matcher.find())
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public static Boolean isSimpleChinese(Character character) throws UnsupportedEncodingException, IllegalArgumentException {
        Boolean isCNChar = isChineseCharacter(character);
        String characterEncode = CharacterEncode.GB2312.getCodeName();
        if (isCNChar) {
            if (new String(character.toString().getBytes(characterEncode), characterEncode).equals(character.toString()))
                return Boolean.TRUE;
            return Boolean.FALSE;
        } else
            throw new IllegalArgumentException(BusinessConstants.INVALID_CHINESE_CHARACTER);
    }

    public static Boolean isTaiwaneseCharacter(Character character) throws UnsupportedEncodingException, IllegalArgumentException {
        Boolean match = !isSimpleChinese(character);
        return match;
    }

    public static Boolean isEnglishCharacter(Character character) {
        Assert.notNull(character, BusinessConstants.CHARACTER_NULL_ARGUMENT);
        String regex = "[a-zA-Z]";
        Matcher matcher = Pattern.compile(regex).matcher(character.toString());
        Boolean match = matcher.find();
        return match;
    }

    public static Boolean isENCapitalCharacter(Character character) throws IllegalArgumentException {
        Boolean isENChar = isEnglishCharacter(character);
        if (isENChar) {
            String regex = "[a-z]";
            Matcher matcher = Pattern.compile(regex).matcher(character.toString());
            if (matcher.find())
                return Boolean.FALSE;
            return Boolean.TRUE;
        } else
            throw new IllegalArgumentException(BusinessConstants.INVALID_ENGLISH_CHARACTER);
    }

    public static Boolean isNumeicCharacter(Character character) {
        Assert.notNull(character, BusinessConstants.CHARACTER_NULL_ARGUMENT);
        String regex = "[0-9]";
        Matcher matcher = Pattern.compile(regex).matcher(character.toString());
        Boolean match = matcher.find();
        return match;
    }

}
