package frankdevhub.job.automatic.entities;

/**
 * @Title: PlatformDataJson
 * @Description: 平台页面返回的json源码
 * @date: 2021/1/30 22:35
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */

@SuppressWarnings("all")
public class PlatformDataJson extends BaseRecord<PlatformDataJson> {

    private String type; //type 搜素结果的类型 例如: engine_search_result
    private Integer jt; //jt
    private String tags; //tags 职位的标签信息
    private String adTrack; //ad_track
    private String jobId; //jobid  平台职位唯一主键
    private String coId; //coid
    private Integer effect; //effect
    private String isSpecialJob; //is_special_job
    private String jobHref; //job_href  职位链接
    private String jobName; //job_name 职位名称
    private String jobTitle; //job_title 职位标题
    private String companyHref; //company_href 公司信息链接
    private String companyName; //company_name 公司名称
    private String providesalaryText; //providesalary_text 薪资范围字符串
    private String workArea; //workarea 辖区代码
    private String workareaText; //workarea_text 辖区字符串
    private String updateDate; //updatedate 更新日期
    private String isIntern; //isIntern 是否是内部岗位
    private String isCommunicate; //iscommunicate 是否薪资可以协调
    private String companytypeText; //companytype_text 企业类型字符串
    private Integer degreeFrom; //degreefrom 学习要求
    private Integer workYear; //workyear 工作年限
    private String issueDate; //issuedate
    private String isFromXyz; //isFromXyz
    private String jobwelf; //jobwelf 职位福利
    private String jobwelfList; //jobwelf_list 职位福利数组
    private String attributeText; //attribute_text 属性链接扩展
    private String companysizeText; //companysize_text 企业规模信息
    private String companyindText; //companyind_text 企业业务行业类型
    private String adid; //adid
    private String context; //源数据

    public String getType() {
        return type;
    }

    public PlatformDataJson setType(String type) {
        this.type = type;
        return this;
    }

    public Integer getJt() {
        return jt;
    }

    public PlatformDataJson setJt(Integer jt) {
        this.jt = jt;
        return this;
    }

    public String getTags() {
        return tags;
    }

    public PlatformDataJson setTags(String tags) {
        this.tags = tags;
        return this;
    }

    public String getAdTrack() {
        return adTrack;
    }

    public PlatformDataJson setAdTrack(String adTrack) {
        this.adTrack = adTrack;
        return this;
    }

    public String getJobId() {
        return jobId;
    }

    public PlatformDataJson setJobId(String jobId) {
        this.jobId = jobId;
        return this;
    }

    public String getCoId() {
        return coId;
    }

    public PlatformDataJson setCoId(String coId) {
        this.coId = coId;
        return this;
    }

    public Integer getEffect() {
        return effect;
    }

    public PlatformDataJson setEffect(Integer effect) {
        this.effect = effect;
        return this;
    }

    public String getIsSpecialJob() {
        return isSpecialJob;
    }

    public PlatformDataJson setIsSpecialJob(String isSpecialJob) {
        this.isSpecialJob = isSpecialJob;
        return this;
    }

    public String getJobHref() {
        return jobHref;
    }

    public PlatformDataJson setJobHref(String jobHref) {
        this.jobHref = jobHref;
        return this;
    }

    public String getJobName() {
        return jobName;
    }

    public PlatformDataJson setJobName(String jobName) {
        this.jobName = jobName;
        return this;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public PlatformDataJson setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public String getCompanyHref() {
        return companyHref;
    }

    public PlatformDataJson setCompanyHref(String companyHref) {
        this.companyHref = companyHref;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public PlatformDataJson setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getProvidesalaryText() {
        return providesalaryText;
    }

    public PlatformDataJson setProvidesalaryText(String providesalaryText) {
        this.providesalaryText = providesalaryText;
        return this;
    }

    public String getWorkArea() {
        return workArea;
    }

    public PlatformDataJson setWorkArea(String workArea) {
        this.workArea = workArea;
        return this;
    }

    public String getWorkareaText() {
        return workareaText;
    }

    public PlatformDataJson setWorkareaText(String workareaText) {
        this.workareaText = workareaText;
        return this;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public PlatformDataJson setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public String getIsIntern() {
        return isIntern;
    }

    public PlatformDataJson setIsIntern(String isIntern) {
        this.isIntern = isIntern;
        return this;
    }

    public String getIsCommunicate() {
        return isCommunicate;
    }

    public PlatformDataJson setIsCommunicate(String isCommunicate) {
        this.isCommunicate = isCommunicate;
        return this;
    }

    public String getCompanytypeText() {
        return companytypeText;
    }

    public PlatformDataJson setCompanytypeText(String companytypeText) {
        this.companytypeText = companytypeText;
        return this;
    }

    public Integer getDegreeFrom() {
        return degreeFrom;
    }

    public PlatformDataJson setDegreeFrom(Integer degreeFrom) {
        this.degreeFrom = degreeFrom;
        return this;
    }

    public Integer getWorkYear() {
        return workYear;
    }

    public PlatformDataJson setWorkYear(Integer workYear) {
        this.workYear = workYear;
        return this;
    }

    public String getIssueData() {
        return issueDate;
    }

    public PlatformDataJson setIssueData(String issueDate) {
        this.issueDate = issueDate;
        return this;
    }

    public String getIsFromXyz() {
        return isFromXyz;
    }

    public PlatformDataJson setIsFromXyz(String isFromXyz) {
        this.isFromXyz = isFromXyz;
        return this;
    }

    public String getJobwelf() {
        return jobwelf;
    }

    public PlatformDataJson setJobwelf(String jobwelf) {
        this.jobwelf = jobwelf;
        return this;
    }

    public String getJobwelfList() {
        return jobwelfList;
    }

    public PlatformDataJson setJobwelfList(String jobwelfList) {
        this.jobwelfList = jobwelfList;
        return this;
    }

    public String getAttributeText() {
        return attributeText;
    }

    public PlatformDataJson setAttributeText(String attributeText) {
        this.attributeText = attributeText;
        return this;
    }

    public String getCompanysizeText() {
        return companysizeText;
    }

    public PlatformDataJson setCompanysizeText(String companysizeText) {
        this.companysizeText = companysizeText;
        return this;
    }

    public String getCompanyindText() {
        return companyindText;
    }

    public PlatformDataJson setCompanyindText(String companyindText) {
        this.companyindText = companyindText;
        return this;
    }

    public String getAdid() {
        return adid;
    }

    public PlatformDataJson setAdid(String adid) {
        this.adid = adid;
        return this;
    }

    public String getContext() {
        return context;
    }

    public PlatformDataJson setContext(String context) {
        this.context = context;
        return this;
    }
}
