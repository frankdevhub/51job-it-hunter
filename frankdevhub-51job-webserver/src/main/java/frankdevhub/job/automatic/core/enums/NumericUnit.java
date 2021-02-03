package frankdevhub.job.automatic.core.enums;

import frankdevhub.job.automatic.core.utils.CommonBusinessUtils;
import lombok.extern.slf4j.Slf4j;

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

@Slf4j
@SuppressWarnings("all")
public enum NumericUnit {
    /**
     * 通用计量单位
     */
    Digitis_CN('个'),    //个位数 简体中文
    Digitis_TW('個'),    //个位数 繁体中文
    Ten_Digitis_CN_TW('十'), //十位数 简体中文
    Hundred_CN('百'),  //百位数 简体中文
    Hundred_TW('百'),  //百位数 繁体中文
    Thousand_CN_TW('千'), //千位数 繁体中文
    Thousand_EN('K'), //千位数 英文字符
    Ten_Thousand_CN('万'), //万位数 中文简体
    Ten_Thousand_TW('萬'), //万位数 繁体中文
    Ten_Thousand_EN('W');  //万位数 英文字符

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
        //如果没有匹配的则返回为空
        return null;
    }

    @Override
    public String toString() {
        return "NumericUnit{" +
                "unit=" + unit + //单位字符
                ", isCN_Character=" + isCN_Character + //是否是中文简体字符
                ", isTW_Character=" + isTW_Character + //是否是中文繁体字符
                ", isEN_Character=" + isEN_Character + //是否是英文字符
                ", isCapital=" + isCapital +  //是否是的大写的字符
                ", attributes=" + attributes + //表示单位的字符的属性集合
                '}';
    }

    NumericUnit(Character unit) {
        this.unit = unit;  //标识单位的字符
        this.attributes = CommonBusinessUtils.getCharacterAttributes(unit); //解析获取单位的字符的属性
        this.setAttributes(); //解析的属性依次反射调用getter setter方法注入属性
    }

    /**
     * 反射获取方法名并调用后将结果注入属性
     */
    private void setAttributes() {
        log.info("NumericUnit::setAttributes, UnitValue = " + unit + "");
        if (null != this.attributes) {
            Class<?> clazz = this.getClass(); //获取枚举对象的类定义
            Method[] methods = clazz.getDeclaredMethods(); //获取实体类定义的方法集合
            for (Method m : methods) {
                m.setAccessible(true);

                String name = m.getName(); //方法名
                Integer args = m.getParameterCount(); //方法的声明的参数个数
                if (name.contains("Character".trim()) && args == 1) {
                    Boolean value = this.attributes.get(name); //如果属性没有值则反射调用getter setter方法注入
                    log.info("method name: " + name + " value: " + value);

                    if (null != value) {
                        try {
                            m.invoke(this, value); //反射方式调用getter setter注入属性值
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        log.info("\n");
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
