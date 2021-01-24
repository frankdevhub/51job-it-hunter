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
@SuppressWarnings("all")
public enum NumericUnit {
    /**
     * 通用计量单位
     */
    Digitis_CN('个'),
    Digitis_TW('個'),
    Ten_Digitis_CN_TW('十'),
    Hundred_CN('百'),
    Hundred_TW('百'),
    Thousand_CN_TW('千'),
    Thousand_EN('K'),
    Ten_Thousand_CN('万'),
    Ten_Thousand_TW('萬'),
    Ten_Thousand_EN('W');

    private Character unit; //单位名称
    private Boolean isCN_Character; //是否是中文简体字符
    private Boolean isTW_Character; // 是否是中文台湾繁体字符
    private Boolean isEN_Character; // 是否是英语字符
    private Boolean isCapital; // 是否是首字母大写
    private Map<String, Boolean> attributes; // 字符属性集合

    /**
     * 获取枚举是实例对象
     *
     * @param unit 字符名称
     * @return 计量单位对象
     */
    public static NumericUnit getUnitType(Character unit) {
        for (NumericUnit u : NumericUnit.values()) {
            if (u.getUnit().equals(unit))
                return u;
        }
        return null;
    }

    @Override
    public String toString() {

        System.out.println("print attributes:");
        System.out.println("unit = " + unit);
        System.out.println("isCN_Character = " + isCN_Character);
        System.out.println("isTW_Character = " + isTW_Character);
        System.out.println("isEN_Character = " + isEN_Character);
        System.out.println("isCapital = " + isCapital);

        System.out.println("\n\n");

        return "NumericUnit{" +
                "unit=" + unit +
                ", isCN_Character=" + isCN_Character +
                ", isTW_Character=" + isTW_Character +
                ", isEN_Character=" + isEN_Character +
                ", isCapital=" + isCapital +
                ", attributes=" + attributes +
                '}';
    }

    NumericUnit(Character unit) {
        this.unit = unit;
        this.attributes = CommonBusinessUtils.getCharacterAttributes(unit);
        this.setAttributes();
    }

    //TODO
    private void setAttributes() {
        System.out.println("NumericUnit::setAttributes, UnitValue = " + unit + "");
        if (null != this.attributes) {
            Class<?> clazz = this.getClass();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method m : methods) {
                m.setAccessible(true);

                String name = m.getName();
                Integer args = m.getParameterCount();
                if (name.contains("Character".trim()) && args == 1) {
                    Boolean value = this.attributes.get(name);
                    System.out.println("method name: " + name + " value: " + value);

                    if (null != value) {
                        try {
                            m.invoke(this, value);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        System.out.println("\n");
    }

    public Character getUnit() {
        return unit;
    }

    private NumericUnit setUnit(Character unit) {
        this.unit = unit;
        return this;
    }

    public Boolean isSimpleChineseCharacter() {
        return isCN_Character;
    }

    private NumericUnit isSimpleChineseCharacter(Boolean CN_Character) {
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
