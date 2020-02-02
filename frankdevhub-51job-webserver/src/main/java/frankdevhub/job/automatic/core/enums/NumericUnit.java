package frankdevhub.job.automatic.core.enums;

import frankdevhub.job.automatic.core.utils.CommonBusinessUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * <p>Title:@ClassName NumericUnit.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/1/28 22:56
 * @Version: 1.0
 */
public enum NumericUnit {

    Digitis_CN('个'),
    Digitis_TW('個'),
    Ten_Digitis_CN('十'),
    Ten_Digitis_TW('十'),
    Hundred_CN('百'),
    Hundred_TW('百'),
    Thousand_CN('千'),
    Thousand_TW('千'),
    Thousand_EN('K'),
    Ten_Thousand_CN('万'),
    Ten_Thousand_TW('萬'),
    Ten_Thousand_EN('W');

    private Character unit;
    private Boolean isCN_Character;
    private Boolean isTW_Character;
    private Boolean isEN_Character;
    private Boolean isCapital;
    private Map<String, Boolean> attributes;


    NumericUnit(Character unit) {
        this.unit = unit;
        this.attributes = CommonBusinessUtils.getCharacterAttributes(unit);
        this.setAttributes();
    }

    private void setAttributes() {
        if (null != this.attributes) {
            Class<?> clazz = this.getClass();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method m : methods) {
                m.setAccessible(true);
                String name = m.getName();
                if (name.contains("Character".trim())) {
                    Boolean value = this.attributes.get(name);
                    if (null != value) {
                        try {
                            m.invoke(name, value);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public Character getUnit() {
        return unit;
    }

    private NumericUnit setUnit(Character unit) {
        this.unit = unit;
        return this;
    }

    public Boolean isSimpleChinese() {
        return isCN_Character;
    }

    private NumericUnit isSimpleChinese(Boolean CN_Character) {
        isCN_Character = CN_Character;
        return this;
    }

    public Boolean isTaiwaneseCharacter() {
        return isTW_Character;
    }

    private NumericUnit isTaiwaneseCharacter(Boolean TW_Character) {
        this.isTW_Character = TW_Character;
        return this;
    }

    public Boolean isEnglishCharacter() {
        return isEN_Character;
    }

    private NumericUnit isEnglishCharacter(Boolean EN_Character) {
        this.isEN_Character = EN_Character;
        return this;
    }

    public Boolean isENCapitalCharacter() {
        return isCapital;
    }

    private NumericUnit isENCapitalCharacter(Boolean capital) {
        isCapital = capital;
        return this;
    }

}
