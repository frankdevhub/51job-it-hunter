package frankdevhub.job.automatic.core.constants;

@SuppressWarnings("all")
public class SeleniumConstants {
    // selenium constants
    // web element attribute name
    // 页面对象常用属性参数
    public static final String ATTRIBUTE_NAME = "name";
    public static final String ATTRIBUTE_TITLE = "title";
    public static final String ATTRIBUTE_TARGET = "target";
    public static final String ATTRIBUTE_HREF = "href";
    public static final String ATTRIBUTE_CLASS = "class";
    public static final String ATTRIBUTE_VALUE = "value";
    // 搜索模糊查询文本框
    public static final String INPUT_SEARCH_CLASS = "ipt";
    public static final String INPUT_SEARCH_XPATH = "//p[@class='ipt']/input[@id='kwdselectid']";
    // 搜索提交按钮
    public static final String SUBMIT_SEARCH_XPATH = "//div[@class='ush top_wrap']/button";
    // 查询结果集列表页面
    public static final String SEARCH_RESULT_LIST_XPATH = "//div[@class='j_joblist']/div[@class='e']"; //查询返回的职位列表集合
    // 查询结果集元素
    public static final String RESULT_JD_NAME_CLASS = "t1";
    public static final String RESULT_COMPANY_NAME_CLASS = "t2";
    public static final String RESULT_JD_LOCATION_CLASS = "t3";
    public static final String RESULT_SALARY_RANGE_CLASS = "t4";
    public static final String RESULT_JD_PUBLISH_DATE_CLASS = "t5";
    // 元素定位Xpath表达式
    //搜索返回职位(职位简介,薪资,发布日期)
    public static final String RESULT_JD_NAME_XPATH = "a[@class='el']/p[@class='t']/span[@class='jname at']"; //职位名称
    public static final String RESULT_JD_LOCATION_XPATH = "a[@class='el']/p[@class='info']/span[@class='d at']"; //职位地点信息  上海-浦东新区  |  2年经验  |  本科  |  招2人
    public static final String RESULT_SALARY_RANGE_XPATH = "a[@class='el']/p[@class='info']/span[@class='sal']"; //职位薪资范围描述
    public static final String RESULT_JD_PUBLISH_DATE_XPATH = "a[@class='el']/p[@class='t']/span[@class='time']"; //职位发布日期  01-26发布
    //搜索返回职位(企业简介,行业,标签)
    public static final String COMPANY_INFO_TEXT_XPATH = "//div[@class='tCompany_center clearfix']"; //jsoup解析企业信息页面中的企业信息段落(含html)
    public static final String RESULT_COMPANY_NAME_XPATH = "div[@class='er']/a"; //职位企业名称  盛趣信息技术（上海）有限公司
    public static final String RESULT_COMPANY_TYPE_NAME_XPATH = "div[@class='er']/p[@class='dc at']"; //职位企业类型(民营公司) 民营公司 | 1000-5000人
    public static final String RESULT_COMPANY_INDUSTRY_TYPE_NAME_XPATH = "div[@class='er']/p[@class='int at']"; //职位企业类型(民营公司) 计算机软件
    //TODO:尚未更新定位的元素表达式
    public static final String RESULT_JD_CAMPUS_ONLY_XPATH = "p[@class='t1 ']/img[@alt='校招']"; //职位是否是校招的
    public static final String RESULT_JD_INTERNSHIP_ONLY_XPATH = "p[@class='t1 ']/img[@alt='实习']"; //职位是否是实习生或兼职的岗位
    // 分页导航控件
    public static final String RESULT_PAGE_NAVIGATOR_XPATH = "//div[@class='p_in']//ul/li";
    // 首页用户登录按钮
    public static final String HOMEPAGE_USER_LOGIN_BTN_XPATH = "//p[@class='op']/a";
}
