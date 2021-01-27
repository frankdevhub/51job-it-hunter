package frankdevhub.job.automatic.core.enums;

import tk.mybatis.mapper.util.Assert;

/**
 * @Title: EducationDegree
 * @Description: 学历程度枚举类
 * @date: 2021/1/27 21:11
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */

@SuppressWarnings("all")
public enum EducationDegree {

    DOCTOR("博士", "DOCTOR"),
    MASTER("硕士研究生", "MASTER"),
    BACHELOR("本科", "BACHELOR"),
    JUNIOR_COLLEGE("大专", "JUNIOR_COLLEGE"),
    TACHNICAL_COLLEGE("中专", "TACHNICAL_COLLEGE");

    /**
     * 获取学历枚举实例
     *
     * @param name 学历中文名称
     * @return 学历枚举实例
     */
    public EducationDegree getDegreeType(String name) {
        Assert.notNull(name, "cannnot find name");
        for (EducationDegree d : EducationDegree.values()) {
            //如果描述的中文名称是枚举中中文描述的子集,则视作匹配当前项
            if (d.name.contains(name))
                return d;
        }
        return null;
    }

    EducationDegree(String name, String code) {
        this.name = name;
        this.code = code;
    }

    private String name;  //中文描述名称
    private String code;  //学历描述代码

    public String getName() {
        return name;
    }

    public EducationDegree setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public EducationDegree setCode(String code) {
        this.code = code;
        return this;
    }
}
