package frankdevhub.job.automatic.core.utils;

import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import frankdevhub.job.automatic.entities.BusinessCharacter;

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

    private Boolean isChinessCharacter(Character character) {
        return null;
    }

    private Boolean isTaiwaneseCharacter(Character character) {
        return null;
    }

    private Boolean isEnglishCharacter(Character character) {
        return null;
    }

    private Boolean isCapitalCharacter(Character character) {
        return null;
    }

    private Boolean isNumeicCharacter(Character character) {
        return null;
    }

}
