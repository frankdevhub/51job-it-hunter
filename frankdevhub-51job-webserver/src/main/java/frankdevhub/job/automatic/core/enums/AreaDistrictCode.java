package frankdevhub.job.automatic.core.enums;

import org.springframework.util.Assert;

/**
 * @author:frankdevhub@gmail.com
 * @createDate:2020年6月25日 下午11:19:03
 * @version: 1.0                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              br>
 * @type:AreaDistrictCode.java
 * @github:https://github.com/frankdevhub
 * @blog:www.frankdevhub.site
 */

@SuppressWarnings("all")
public enum AreaDistrictCode {

    /**
     * 默认上海市各个辖区
     */
    HP("黄浦", 310101), SJ("松江", 310102),
    LW("卢湾", 310103), QP("青浦", 310118),
    XH("徐汇", 310104), NH("南汇", 310119),
    CN("长宁", 310105), FX("奉贤", 310120),
    JA("静安", 310106), CS("川沙", 310152),
    PT("普陀", 310107), CM("崇明", 310230),
    ZB("闸北", 310108), HK("虹口", 310109),
    YP("杨浦", 310110), MH("闵行", 310112),
    BS("宝山", 310113), JD("嘉定", 310114),
    PD("浦东", 310115), JS("金山", 310116);

    private String name; // 上海市辖区中文名称
    private Integer code; // 上海市辖区代码编号

    /**
     * 获取对象实例(匹配区域中文名称)
     *
     * @param name 辖区中文名称
     * @reutrn 辖区对象
     */
    public static AreaDistrictCode getInstance(String name) {
        Assert.notNull(name, "cannot find name");
        for (AreaDistrictCode c : AreaDistrictCode.values()) {
            if (name.trim().contains(c.getDistrictName())) {
                return c;
            }
        }
        return null;
    }

    /**
     * 获取对象实例(匹配区域代码)
     *
     * @param code 辖区代码编号
     * @reutrn 辖区对象
     */
    public static AreaDistrictCode getInstance(Integer code) {
        Assert.notNull(code, "cannot find name");
        for (AreaDistrictCode c : AreaDistrictCode.values()) {
            if (code.equals(c.getDistrictCode())) {
                return c;
            }
        }
        return null;
    }

    private AreaDistrictCode(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public String getDistrictName() {
        return name;
    }

    public void setDistrictName(String name) {
        this.name = name;
    }

    public Integer getDistrictCode() {
        return code;
    }

    public void setDistrictCode(Integer code) {
        this.code = code;
    }

}
