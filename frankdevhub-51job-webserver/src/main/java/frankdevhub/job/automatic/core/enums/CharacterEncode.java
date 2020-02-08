package frankdevhub.job.automatic.core.enums;

/**
 * <p>Title:@ClassName CharacterEncode.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/1/29 4:27
 * @Version: 1.0
 */
public enum CharacterEncode {
    GB2312("GB2312"),
    ASCII("ASCII"),
    MBCS("MBCS"),
    GBK("GBK"),
    Big5("BIG5"),
    Unicode("Unicode"),
    UTF8("UTF-8"),
    Base64("Base64");

    private String codeName;

    public String getCodeName() {
        return codeName;
    }

    public CharacterEncode setCodeName(String codeName) {
        this.codeName = codeName;
        return this;
    }

    CharacterEncode(String codeName) {
        this.codeName = codeName;
    }
}
