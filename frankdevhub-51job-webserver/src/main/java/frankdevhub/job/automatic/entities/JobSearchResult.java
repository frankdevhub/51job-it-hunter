package frankdevhub.job.automatic.entities;

import frankdevhub.job.automatic.core.constants.BusinessConstants;
import frankdevhub.job.automatic.core.exception.BusinessException;
import tk.mybatis.mapper.util.Assert;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title:@ClassName JobSearchResult.java</p>
 * <p>Description: 职位列表信息对象</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/1/26 22:24
 * @Version: 1.0
 */

@SuppressWarnings("all")
public class JobSearchResult extends PlatformDataJson {
    /**
     * 职位名称
     */
    private String jobTitle;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 职位地点
     */
    private String location;

    /**
     * 薪资描述原文字符串
     */
    private String salaryRangeChars;

    /**
     * 薪资最小值
     */
    private Double salaryRangeMin;

    /**
     * 薪资最大值
     */
    private Double salaryRangeMax;

    /**
     * 薪资时间计量单位
     */
    private String salaryTimeUnit;

    /**
     * 薪资计量单位
     */
    private String salaryNumericUnit;

    /**
     * 是否以万计量
     */
    private Boolean isDefineByW;

    /**
     * 是否以千计量
     */
    private Boolean isDefineByK;

    /**
     * 是否按日计量
     */
    private Boolean isDefineByDay;

    /**
     * 是否月薪计量
     */
    private Boolean isDefineByMonth;

    /**
     * 是否年薪计量
     */
    private Boolean isDefineByYear;

    /**
     * 是否内推职位
     */
    private Boolean isInternshipPos;

    /**
     * 是否校招职位
     */
    private Boolean isCampusOnly;

    /**
     * 薪资是否可商议
     */
    private Boolean isSalaryNegotiable;

    /**
     * 发布日期
     */
    private String publishDateChar;

    /**
     * 发布月份（月）
     */
    private Integer publishDateMonthNumeric;

    /**
     * 发布日期（天）
     */
    private Integer publishDateDayNumeric;

    /**
     * 企业岗位计划招聘人数(0默认为未知)
     */
    private Integer headCount;

    /**
     * 职位链接地址
     */
    private String linkUrl;

    /**
     * 唯一标识
     */
    private Integer markId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobSearchResult)) return false;
        if (!super.equals(o)) return false;
        JobSearchResult that = (JobSearchResult) o;
        return getJobTitle().equals(that.getJobTitle())
                && getCompanyName().equals(that.getCompanyName())
                && getLocation().equals(that.getLocation())
                && getSalaryRangeChars().equals(that.getSalaryRangeChars())
                && getSalaryRangeMin().equals(that.getSalaryRangeMin())
                && getSalaryRangeMax().equals(that.getSalaryRangeMax())
                && getSalaryTimeUnit().equals(that.getSalaryTimeUnit())
                && getSalaryNumericUnit().equals(that.getSalaryNumericUnit())
                && getIsDefineByW().equals(that.getIsDefineByW())
                && getIsDefineByK().equals(that.getIsDefineByK())
                && getIsDefineByDay().equals(that.getIsDefineByDay())
                && getIsDefineByMonth().equals(that.getIsDefineByMonth())
                && getIsDefineByYear().equals(that.getIsDefineByYear())
                && getIsInternshipPos().equals(that.getIsInternshipPos())
                && getIsCampusOnly().equals(that.getIsCampusOnly())
                && getIsSalaryNegotiable().equals(that.getIsSalaryNegotiable())
                && getPublishDateChar().equals(that.getPublishDateChar())
                && getPublishDateMonthNumeric().equals(that.getPublishDateMonthNumeric())
                && getPublishDateDayNumeric().equals(that.getPublishDateDayNumeric())
                && getLinkUrl().equals(that.getLinkUrl());
    }

    @Override
    public int hashCode() {
        int hashCode = Objects.hash(super.hashCode(), getJobTitle(), getCompanyName(), getLocation(),
                getSalaryRangeChars(), getSalaryRangeMin(), getSalaryRangeMax(), getSalaryTimeUnit(),
                getSalaryNumericUnit(), getIsDefineByW(), getIsDefineByK(), getIsDefineByDay(),
                getIsDefineByMonth(), getIsDefineByYear(), getIsInternshipPos(), getIsCampusOnly(),
                getIsSalaryNegotiable(), getPublishDateChar(), getPublishDateMonthNumeric(),
                getPublishDateDayNumeric(), getLinkUrl());
        if (hashCode < 0)
            hashCode = hashCode * (-1);
        return hashCode;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public JobSearchResult setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public JobSearchResult setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public JobSearchResult setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getSalaryRangeChars() {
        return salaryRangeChars;
    }

    public JobSearchResult setSalaryRangeChars(String salaryRangeChars) {
        this.salaryRangeChars = salaryRangeChars;
        return this;
    }

    public Double getSalaryRangeMin() {
        return salaryRangeMin;
    }

    public JobSearchResult setSalaryRangeMin(Double salaryRangeMin) {
        this.salaryRangeMin = salaryRangeMin;
        return this;
    }

    public Double getSalaryRangeMax() {
        return salaryRangeMax;
    }

    public JobSearchResult setSalaryRangeMax(Double salaryRangeMax) {
        this.salaryRangeMax = salaryRangeMax;
        return this;
    }

    public String getSalaryTimeUnit() {
        return salaryTimeUnit;
    }

    public JobSearchResult setSalaryTimeUnit(String salaryTimeUnit) {
        this.salaryTimeUnit = salaryTimeUnit;
        return this;
    }

    public String getSalaryNumericUnit() {
        return salaryNumericUnit;
    }

    public JobSearchResult setSalaryNumericUnit(String salaryNumericUnit) {
        this.salaryNumericUnit = salaryNumericUnit;
        return this;
    }

    public Boolean getIsDefineByW() {
        return isDefineByW;
    }

    public JobSearchResult setIsDefineByW(Boolean isDefineByW) {
        this.isDefineByW = isDefineByW;
        return this;
    }

    public Boolean getIsDefineByK() {
        return isDefineByK;
    }

    public JobSearchResult setIsDefineByK(Boolean isDefineByK) {
        this.isDefineByK = isDefineByK;
        return this;
    }

    public Boolean getIsDefineByDay() {
        return isDefineByDay;
    }

    public JobSearchResult setIsDefineByDay(Boolean isDefineByDay) {
        this.isDefineByDay = isDefineByDay;
        return this;
    }

    public Boolean getIsDefineByMonth() {
        return isDefineByMonth;
    }

    public JobSearchResult setIsDefineByMonth(Boolean isDefineByMonth) {
        this.isDefineByMonth = isDefineByMonth;
        return this;
    }

    public Boolean getIsDefineByYear() {
        return isDefineByYear;
    }

    public JobSearchResult setIsDefineByYear(Boolean isDefineByYear) {
        this.isDefineByYear = isDefineByYear;
        return this;
    }

    public Boolean getIsInternshipPos() {
        return isInternshipPos;
    }

    public JobSearchResult setIsInternshipPos(Boolean isInternshipPos) {
        this.isInternshipPos = isInternshipPos;
        return this;
    }

    public Boolean getIsCampusOnly() {
        return isCampusOnly;
    }

    public JobSearchResult setIsCampusOnly(Boolean isCampusOnly) {
        this.isCampusOnly = isCampusOnly;
        return this;
    }

    public Boolean getIsSalaryNegotiable() {
        return isSalaryNegotiable;
    }

    public JobSearchResult setIsSalaryNegotiable(Boolean isSalaryNegotiable) {
        this.isSalaryNegotiable = isSalaryNegotiable;
        return this;
    }

    public String getPublishDateChar() {
        return publishDateChar;
    }

    public JobSearchResult setPublishDateChar(String publishDateChar) {
        this.publishDateChar = publishDateChar;
        return this;
    }

    public Integer getPublishDateMonthNumeric() {
        return publishDateMonthNumeric;
    }

    public JobSearchResult setPublishDateMonthNumeric(Integer publishDateMonthNumeric) {
        this.publishDateMonthNumeric = publishDateMonthNumeric;
        return this;
    }

    public Integer getPublishDateDayNumeric() {
        return publishDateDayNumeric;
    }

    public JobSearchResult setPublishDateDayNumeric(Integer publishDateDayNumeric) {
        this.publishDateDayNumeric = publishDateDayNumeric;
        return this;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public JobSearchResult setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        return this;
    }

    public Integer getHeadCount() {
        return headCount;
    }

    public JobSearchResult setHeadCount(Integer headCount) {
        this.headCount = headCount;
        return this;
    }

    public Integer getMarkId() {
        return markId;
    }

    /**
     * 生成平台唯一的标识符
     * 生成策略：依据链接进行生成,提取链接中的唯一特征值
     * 例: https://jobs.51job.com/shanghai/126397572.html?s=01&t=0
     * 提取:126397572
     */
    public void generateMarkId() throws BusinessException {
        //校验是否获取到了职位链接
        Assert.notNull(this.linkUrl, "cannot find job detail link url");
        String exp = BusinessConstants.DEFAULT_HTTP_LINK_MARK_REGEX;
        Pattern p = Pattern.compile(exp);
        Matcher m = p.matcher(exp);
        if (m.find()) {
            String s = m.group("key");
            Assert.notNull(s, "markid cannot be generated as null");
            this.markId = Integer.parseInt(s); //获取唯一识别号
        } else {
            throw new BusinessException(BusinessConstants.MARKID_GENERATE_ERROR);
        }
    }


}
