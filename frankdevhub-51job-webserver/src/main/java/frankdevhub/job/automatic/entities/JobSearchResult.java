package frankdevhub.job.automatic.entities;

import javax.persistence.Column;
import javax.persistence.Table;

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
@Table(name = "")
public class JobSearchResult extends BaseRecord<JobSearchResult> {

    @Column(name = "id")
    private Long id;

    @Column(name = "search_key_id")
    private Long keyId;

    @Column(name = "job_title")
    private String jobTitle;

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

    public String getJobTitle() {
        return jobTitle;
    }

    public JobSearchResult setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
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

    public JobSearchResult setUnitByTenThousand(Boolean unitByTenThousand) {
        isUnitByTenThousand = unitByTenThousand;
        return this;
    }

    public Boolean getIsUnitByThousand() {
        return isUnitByThousand;
    }

    public JobSearchResult setUnitByThousand(Boolean unitByThousand) {
        isUnitByThousand = unitByThousand;
        return this;
    }

    public Boolean getIsUnitByDay() {
        return isUnitByDay;
    }

    public JobSearchResult setUnitByDay(Boolean unitByDay) {
        isUnitByDay = unitByDay;
        return this;
    }

    public Boolean getIsInternshipPosition() {
        return isInternshipPosition;
    }

    public JobSearchResult setInternshipPosition(Boolean internshipPosition) {
        isInternshipPosition = internshipPosition;
        return this;
    }

    public Boolean getIsCampusOnly() {
        return isCampusOnly;
    }

    public JobSearchResult setCampusOnly(Boolean campusOnly) {
        isCampusOnly = campusOnly;
        return this;
    }

    public Boolean getIsSalaryNeedNegotiation() {
        return isSalaryNeedNegotiation;
    }

    public JobSearchResult setSalaryNeedNegotiation(Boolean salaryNeedNegotiation) {
        isSalaryNeedNegotiation = salaryNeedNegotiation;
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
}
