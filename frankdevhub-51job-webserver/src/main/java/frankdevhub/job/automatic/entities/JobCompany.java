package frankdevhub.job.automatic.entities;

/**
 * @Title: JobCompany
 * @Description: 企业信息
 * @date: 2020/1/25 0:18
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */

@SuppressWarnings("all")
public class JobCompany extends BaseRecord<JobCompany> {
    /**
     * 唯一标识
     */
    private Integer unionId;

    /**
     * 企业商标
     */
    private String platCompanyLogo;

    /**
     * 企业类型(民营企业)
     */
    private String platCompanyType;

    /**
     * 企业行业类型(计算机软件,汽车)
     */
    private String platCompanyIndustry;

    /**
     * 企业介绍信息
     */
    private String platComppanyInfo;

    /**
     * 企业标签(环境,人文氛围,福利,个人社保公积金)
     */
    private String tagList;

    /**
     * 更新时间时间戳
     */
    private Long updateTime;

    public Integer getUnionId() {
        return unionId;
    }

    public JobCompany setUnionId(Integer unionId) {
        this.unionId = unionId;
        return this;
    }

    public String getPlatCompanyLogo() {
        return platCompanyLogo;
    }

    public JobCompany setPlatCompanyLogo(String platCompanyLogo) {
        this.platCompanyLogo = platCompanyLogo;
        return this;
    }

    public String getPlatCompanyType() {
        return platCompanyType;
    }

    public JobCompany setPlatCompanyType(String platCompanyType) {
        this.platCompanyType = platCompanyType;
        return this;
    }

    public String getPlatCompanyIndustry() {
        return platCompanyIndustry;
    }

    public JobCompany setPlatCompanyIndustry(String platCompanyIndustry) {
        this.platCompanyIndustry = platCompanyIndustry;
        return this;
    }

    public String getPlatComppanyInfo() {
        return platComppanyInfo;
    }

    public JobCompany setPlatComppanyInfo(String platComppanyInfo) {
        this.platComppanyInfo = platComppanyInfo;
        return this;
    }

    public String getTagList() {
        return tagList;
    }

    public JobCompany setTagList(String tagList) {
        this.tagList = tagList;
        return this;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public JobCompany setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
