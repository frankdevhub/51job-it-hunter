package frankdevhub.job.automatic.entities;

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
    private Boolean isCapital;
    private Boolean isNumericCharacter;
    private Boolean isSymbolCharacter;

    public Character getValue() {
        return value;
    }

    public BusinessCharacter setValue(Character value) {
        this.value = value;
        return this;
    }

    public Boolean getIsCN_Character() {
        return isCN_Character;
    }

    public BusinessCharacter setIsCN_Character(Boolean isCN_Character) {
        this.isCN_Character = isCN_Character;
        return this;
    }

    public Boolean getIsTW_Character() {
        return isTW_Character;
    }

    public BusinessCharacter setIsTW_Character(Boolean isTW_Character) {
        this.isTW_Character = isTW_Character;
        return this;
    }

    public Boolean getIsEN_Character() {
        return isEN_Character;
    }

    public BusinessCharacter setIsEN_Character(Boolean isEN_Character) {
        this.isEN_Character = isEN_Character;
        return this;
    }

    public Boolean getIsCapital() {
        return isCapital;
    }

    public BusinessCharacter setIsCapital(Boolean isCapital) {
        this.isCapital = isCapital;
        return this;
    }

    public Boolean getIsNumericCharacter() {
        return isNumericCharacter;
    }

    public BusinessCharacter setIsNumericCharacter(Boolean isNumericCharacter) {
        this.isNumericCharacter = isNumericCharacter;
        return this;
    }

    public Boolean getIsSymbolCharacter() {
        return isSymbolCharacter;
    }

    public BusinessCharacter setSymbolCharacter(Boolean isSymbolCharacter) {
        this.isSymbolCharacter = isSymbolCharacter;
        return this;
    }
}
