package frankdevhub.job.automatic.core.enums;

/**
 * <p>Title:@ClassName DateUnit.java</p>
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

    public Character getUnit() {
        return unit;
    }

    private NumericUnit setUnit(Character unit) {
        this.unit = unit;
        return this;
    }

    public Boolean getIsCN_Character() {
        return isCN_Character;
    }

    private NumericUnit setCN_Character(Boolean CN_Character) {
        isCN_Character = CN_Character;
        return this;
    }

    public Boolean getIsTW_Character() {
        return isTW_Character;
    }

    private NumericUnit setTW_Character(Boolean TW_Character) {
        isTW_Character = TW_Character;
        return this;
    }

    public Boolean getIsEN_Character() {
        return isEN_Character;
    }

    private NumericUnit setEN_Character(Boolean EN_Character) {
        isEN_Character = EN_Character;
        return this;
    }

    public Boolean getIsCapital() {
        return isCapital;
    }

    private NumericUnit setCapital(Boolean capital) {
        isCapital = capital;
        return this;
    }

    NumericUnit(Character unit) {
        this.unit = unit;
    }

}
