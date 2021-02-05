package frankdevhub.job.automatic.entities.dto;

/**
 * @Title: SearchQuery
 * @Description: 平台搜索查询条件
 * @date: 2021/2/5 21:59
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */

@SuppressWarnings("all")
public class SearchQuery {

    private String keyword;  //搜索关键字
    private String lang; //语言类型
    private String postChannel;  //投送渠道
    private String workyear;  //工作年限
    private String cotype;
    private String degreeFrom; //学历要求
    private String jobTerm;
    private String companySize; //企业规模
    private String ordField;
    private String dibiaoId;
    private String line;
    private String welfare; //职工福利

    public String getKeyword() {
        return keyword;
    }

    public SearchQuery setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public String getLang() {
        return lang;
    }

    public SearchQuery setLang(String lang) {
        this.lang = lang;
        return this;
    }

    public String getPostChannel() {
        return postChannel;
    }

    public SearchQuery setPostChannel(String postChannel) {
        this.postChannel = postChannel;
        return this;
    }

    public String getWorkyear() {
        return workyear;
    }

    public SearchQuery setWorkyear(String workyear) {
        this.workyear = workyear;
        return this;
    }

    public String getCotype() {
        return cotype;
    }

    public SearchQuery setCotype(String cotype) {
        this.cotype = cotype;
        return this;
    }

    public String getDegreeFrom() {
        return degreeFrom;
    }

    public SearchQuery setDegreeFrom(String degreeFrom) {
        this.degreeFrom = degreeFrom;
        return this;
    }

    public String getJobTerm() {
        return jobTerm;
    }

    public SearchQuery setJobTerm(String jobTerm) {
        this.jobTerm = jobTerm;
        return this;
    }

    public String getCompanySize() {
        return companySize;
    }

    public SearchQuery setCompanySize(String companySize) {
        this.companySize = companySize;
        return this;
    }

    public String getOrdField() {
        return ordField;
    }

    public SearchQuery setOrdField(String ordField) {
        this.ordField = ordField;
        return this;
    }

    public String getDibiaoId() {
        return dibiaoId;
    }

    public SearchQuery setDibiaoId(String dibiaoId) {
        this.dibiaoId = dibiaoId;
        return this;
    }

    public String getLine() {
        return line;
    }

    public SearchQuery setLine(String line) {
        this.line = line;
        return this;
    }

    public String getWelfare() {
        return welfare;
    }

    public SearchQuery setWelfare(String welfare) {
        this.welfare = welfare;
        return this;
    }
}
