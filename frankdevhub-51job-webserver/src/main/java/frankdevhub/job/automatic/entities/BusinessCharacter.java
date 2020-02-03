package frankdevhub.job.automatic.entities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * <p>Title:@ClassName BusinessCharacter.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/1/28 23:49
 * @Version: 1.0
 */
public class BusinessCharacter {
    private Character value;
    private Boolean isCN_Character;
    private Boolean isTW_Character;
    private Boolean isEN_Character;
    private Boolean isENCapital;
    private Boolean isNumericCharacter;
    private Boolean isSymbolCharacter;
    private Map<String, Boolean> attributes;

    @Override
    public String toString() {

        System.out.println("print attributes:");
        System.out.println("value = " + value);
        System.out.println("isCN_Character = " + isCN_Character);
        System.out.println("isTW_Character = " + isTW_Character);
        System.out.println("isEN_Character = " + isEN_Character);
        System.out.println("isENCapital = " + isENCapital);
        System.out.println("isNumericCharacter = " + isNumericCharacter);
        System.out.println("isSymbolCharacter = " + isSymbolCharacter);

        return "BusinessCharacter{" +
                "value=" + value +
                ", isCN_Character=" + isCN_Character +
                ", isTW_Character=" + isTW_Character +
                ", isEN_Character=" + isEN_Character +
                ", isENCapital=" + isENCapital +
                ", isNumericCharacter=" + isNumericCharacter +
                ", isSymbolCharacter=" + isSymbolCharacter +
                ", attributes=" + attributes +
                '}';
    }

    public Map<String, Boolean> getAttributes() {
        return attributes;
    }

    public BusinessCharacter setAttributes(Map<String, Boolean> attributes) throws InvocationTargetException, IllegalAccessException {
        this.attributes = attributes;
        setAttributes();
        return this;
    }

    private void setAttributes() throws InvocationTargetException, IllegalAccessException {
        if (null != this.attributes) {
            Class<?> clazz = this.getClass();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method m : methods) {
                m.setAccessible(true);
                String name = m.getName();
                if (name.contains("Character".trim())) {
                    Boolean value = this.attributes.get(name);
                    if (null != value)
                        m.invoke(name, value);
                }
            }
        }
    }

    public Character getValue() {
        return value;
    }

    public BusinessCharacter setValue(Character value) {
        this.value = value;
        return this;
    }

    public Boolean isSimpleChineseCharacter() {
        return isCN_Character;
    }

    public BusinessCharacter isSimpleChineseCharacter(Boolean isCN_Character) {
        this.isCN_Character = isCN_Character;
        return this;
    }

    public Boolean isTaiwaneseCharacter() {
        return isTW_Character;
    }

    public BusinessCharacter isTaiwaneseCharacter(Boolean isTW_Character) {
        this.isTW_Character = isTW_Character;
        return this;
    }

    public Boolean isEnglishCharacter() {
        return isEN_Character;
    }

    public BusinessCharacter isEnglishCharacter(Boolean isEN_Character) {
        this.isEN_Character = isEN_Character;
        return this;
    }

    public Boolean isENCapitalCharacter() {
        return isENCapital;
    }

    public BusinessCharacter isENCapitalCharacter(Boolean isENCapital) {
        this.isENCapital = isENCapital;
        return this;
    }

    public Boolean isNumericCharacter() {
        return isNumericCharacter;
    }

    public BusinessCharacter isNumericCharacter(Boolean isNumericCharacter) {
        this.isNumericCharacter = isNumericCharacter;
        return this;
    }

    public Boolean isSymbolCharacter() {
        return isSymbolCharacter;
    }

    public BusinessCharacter isSymbolCharacter(Boolean isSymbolCharacter) {
        this.isSymbolCharacter = isSymbolCharacter;
        return this;
    }
}
