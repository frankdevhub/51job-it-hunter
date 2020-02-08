package frankdevhub.job.automatic.entities;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Objects;

/**
 * <p>Title:@ClassName JobSearchResult.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/1/26 22:24
 * @Version: 1.0
 */
@Table(name = "platform_search_result")
public class JobSearchResult extends BaseRecord<JobSearchResult> {

    @Column(name = "id")
    private Long id;

    @Column(name = "search_key_id")
    private Long keyId;

    @Column(name = "search_keyword_text")
    private String searchKeyword;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "location")
    private String location;

    @Column(name = "salary_range_chars")
    private String salaryRange;

    @Column(name = "salary_range_min")
    private Double salaryMinNumeric;

    @Column(name = "salary_range_max")
    private Double salaryMaxNumeric;

    @Column(name = "salary_time_unit")
    private String salaryTimeUnit;

    @Column(name = "salary_numeric_unit")
    private String salaryNumericUnit;

    @Column(name = "is_define_by_w")
    private Boolean isUnitByTenThousand;

    @Column(name = "is_define_by_k")
    private Boolean isUnitByThousand;

    @Column(name = "is_define_by_day")
    private Boolean isUnitByDay;

    @Column(name = "is_define_by_month")
    private Boolean isUnitByMonth;

    @Column(name = "is_define_by_year")
    private Boolean isUnitByYear;

    @Column(name = "is_internship_pos")
    private Boolean isInternshipPosition;

    @Column(name = "is_campus_only")
    private Boolean isCampusOnly;

    @Column(name = "is_salary_negotiable")
    private Boolean isSalaryNeedNegotiation;

    @Column(name = "publish_date_char")
    private String publishDate;

    @Column(name = "publish_date_month_numeric")
    private Integer publishMonth;

    @Column(name = "publish_date_day_numeric")
    private Integer publishDayOfMonth;

    @Column(name = "mark_id")
    private Integer markId;

    public Long getId() {
        return id;
    }

    public JobSearchResult setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getKeyId() {
        return keyId;
    }

    public JobSearchResult setKeyId(Long keyId) {
        this.keyId = keyId;
        return this;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public JobSearchResult setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
        return this;
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

    public String getSalaryRange() {
        return salaryRange;
    }

    public JobSearchResult setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
        return this;
    }

    public Double getSalaryMinNumeric() {
        return salaryMinNumeric;
    }

    public JobSearchResult setSalaryMinNumeric(Double salaryMinNumeric) {
        this.salaryMinNumeric = salaryMinNumeric;
        return this;
    }

    public Double getSalaryMaxNumeric() {
        return salaryMaxNumeric;
    }

    public JobSearchResult setSalaryMaxNumeric(Double salaryMaxNumeric) {
        this.salaryMaxNumeric = salaryMaxNumeric;
        return this;
    }

    public String getSalaryTimeUnit() {
        return salaryTimeUnit;
    }

    public JobSearchResult setSalaryTimeUnit(String salaryTimeUnit) {
        this.salaryTimeUnit = salaryTimeUnit;
        return this;
    }

    public Boolean getIsUnitByDay() {
        return isUnitByDay;
    }

    public JobSearchResult setIsUnitByDay(Boolean isUnitByDay) {
        this.isUnitByDay = isUnitByDay;
        return this;
    }

    public Boolean getIsUnitByMonth() {
        return isUnitByMonth;
    }

    public JobSearchResult setIsUnitByMonth(Boolean isUnitByMonth) {
        this.isUnitByMonth = isUnitByMonth;
        return this;
    }

    public Boolean getIsUnitByYear() {
        return isUnitByYear;
    }

    public JobSearchResult setIsUnitByYear(Boolean isUnitByYear) {
        this.isUnitByYear = isUnitByYear;
        return this;
    }

    public String getSalaryNumericUnit() {
        return salaryNumericUnit;
    }

    public JobSearchResult setSalaryNumericUnit(String salaryNumericUnit) {
        this.salaryNumericUnit = salaryNumericUnit;
        return this;
    }

    public Boolean getIsUnitByTenThousand() {
        return isUnitByTenThousand;
    }

    public JobSearchResult setIsUnitByTenThousand(Boolean isUnitByTenThousand) {
        this.isUnitByTenThousand = isUnitByTenThousand;
        return this;
    }

    public Boolean getIsUnitByThousand() {
        return isUnitByThousand;
    }

    public JobSearchResult setIsUnitByThousand(Boolean isUnitByThousand) {
        this.isUnitByThousand = isUnitByThousand;
        return this;
    }

    public Boolean getIsInternshipPosition() {
        return isInternshipPosition;
    }

    public JobSearchResult setIsInternshipPosition(Boolean isInternshipPosition) {
        this.isInternshipPosition = isInternshipPosition;
        return this;
    }

    public Boolean getIsCampusOnly() {
        return isCampusOnly;
    }

    public JobSearchResult setIsCampusOnly(Boolean isCampusOnly) {
        this.isCampusOnly = isCampusOnly;
        return this;
    }

    public Boolean getIsSalaryNeedNegotiation() {
        return isSalaryNeedNegotiation;
    }

    public JobSearchResult setSalaryNeedNegotiation(Boolean isSalaryNeedNegotiation) {
        this.isSalaryNeedNegotiation = isSalaryNeedNegotiation;
        return this;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public JobSearchResult setPublishDate(String publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    public Integer getPublishMonth() {
        return publishMonth;
    }

    public JobSearchResult setPublishMonth(Integer publishMonth) {
        this.publishMonth = publishMonth;
        return this;
    }

    public Integer getPublishDayOfMonth() {
        return publishDayOfMonth;
    }

    public JobSearchResult setPublishDayOfMonth(Integer publishDayOfMonth) {
        this.publishDayOfMonth = publishDayOfMonth;
        return this;
    }

    public Integer getMarkId() {
        return markId;
    }

    public JobSearchResult setMarkId(Integer markId) {
        this.markId = markId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobSearchResult)) return false;
        JobSearchResult that = (JobSearchResult) o;
        return
                Objects.equals(getJobTitle(), that.getJobTitle()) &&
                        Objects.equals(getCompanyName(), that.getCompanyName()) &&
                        Objects.equals(getLocation(), that.getLocation()) &&
                        Objects.equals(getSalaryRange(), that.getSalaryRange()) &&
                        Objects.equals(getSalaryMinNumeric(), that.getSalaryMinNumeric()) &&
                        Objects.equals(getSalaryMaxNumeric(), that.getSalaryMaxNumeric()) &&
                        Objects.equals(getSalaryTimeUnit(), that.getSalaryTimeUnit()) &&
                        Objects.equals(getSalaryNumericUnit(), that.getSalaryNumericUnit()) &&
                        Objects.equals(getIsUnitByTenThousand(), that.getIsUnitByTenThousand()) &&
                        Objects.equals(getIsUnitByThousand(), that.getIsUnitByThousand()) &&
                        Objects.equals(getIsUnitByDay(), that.getIsUnitByDay()) &&
                        Objects.equals(getIsUnitByMonth(), that.getIsUnitByMonth()) &&
                        Objects.equals(getIsUnitByYear(), that.getIsUnitByYear()) &&
                        Objects.equals(getIsInternshipPosition(), that.getIsInternshipPosition()) &&
                        Objects.equals(getIsCampusOnly(), that.getIsCampusOnly()) &&
                        Objects.equals(getIsSalaryNeedNegotiation(), that.getIsSalaryNeedNegotiation()) &&
                        Objects.equals(getPublishDate(), that.getPublishDate()) &&
                        Objects.equals(getPublishMonth(), that.getPublishMonth()) &&
                        Objects.equals(getPublishDayOfMonth(), that.getPublishDayOfMonth());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJobTitle(), getCompanyName(), getLocation(), getSalaryRange(), getSalaryMinNumeric(), getSalaryMaxNumeric(), getSalaryTimeUnit(), getSalaryNumericUnit(), getIsUnitByTenThousand(), getIsUnitByThousand(), getIsUnitByDay(), getIsUnitByMonth(), getIsUnitByYear(), getIsInternshipPosition(), getIsCampusOnly(), getIsSalaryNeedNegotiation(), getPublishDate(), getPublishMonth(), getPublishDayOfMonth());
    }

    @Override
    public String toString() {
        return "JobSearchResult{" + '\n' +
                "id=" + id + '\n' +
                ", keyId=" + keyId + '\n' +
                ", searchKeyword='" + searchKeyword + '\'' + '\n' +
                ", companyName='" + companyName + '\'' + '\n' +
                ", jobTitle='" + jobTitle + '\'' + '\n' +
                ", location='" + location + '\'' + '\n' +
                ", salaryRange='" + salaryRange + '\'' + '\n' +
                ", salaryMinNumeric=" + salaryMinNumeric + '\n' +
                ", salaryMaxNumeric=" + salaryMaxNumeric + '\n' +
                ", salaryTimeUnit='" + salaryTimeUnit + '\'' + '\n' +
                ", salaryNumericUnit='" + salaryNumericUnit + '\'' + '\n' +
                ", isUnitByTenThousand=" + isUnitByTenThousand + '\n' +
                ", isUnitByThousand=" + isUnitByThousand + '\n' +
                ", isUnitByDay=" + isUnitByDay + '\n' +
                ", isUnitByMonth=" + isUnitByMonth + '\n' +
                ", isUnitByYear=" + isUnitByYear + '\n' +
                ", isInternshipPosition=" + isInternshipPosition + '\n' +
                ", isCampusOnly=" + isCampusOnly + '\n' +
                ", isSalaryNeedNegotiation=" + isSalaryNeedNegotiation + '\n' +
                ", publishDate='" + publishDate + '\'' + '\n' +
                ", publishMonth=" + publishMonth + '\n' +
                ", publishDayOfMonth=" + publishDayOfMonth + '\n' +
                ", markId=" + markId + '\n' +
                '}';
    }
}
