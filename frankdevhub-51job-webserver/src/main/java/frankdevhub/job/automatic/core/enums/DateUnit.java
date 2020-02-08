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
public enum DateUnit {
    DAY_1('天'), DAY_2('日'), MONTH('月'), YEAR('年');

    private Character unit;

    DateUnit(Character unit) {
        this.unit = unit;
    }

    public static DateUnit getUnitType(Character unit) {
        for (DateUnit u : DateUnit.values()) {
            if (u.getUnit().equals(unit))
                return u;
        }
        return null;
    }

    public Character getUnit() {
        return unit;
    }

    private DateUnit setUnit(Character unit) {
        this.unit = unit;
        return this;
    }
}
