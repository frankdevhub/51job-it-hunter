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
    public static final String SEARCH_RESULT_LIST_XPATH = "//div[@class='dw_table']/div[@class='el']";
    // 查询结果集元素
    public static final String RESULT_JD_NAME_CLASS = "t1";
    public static final String RESULT_COMPANY_NAME_CLASS = "t2";
    public static final String RESULT_JD_LOCATION_CLASS = "t3";
    public static final String RESULT_SALARY_RANGE_CLASS = "t4";
    public static final String RESULT_JD_PUBLISH_DATE_CLASS = "t5";
    public static final String RESULT_JD_NAME_XPATH = "p/span/a";
    public static final String RESULT_COMPANY_NAME_XPATH = "span[@class='t2']/a";
    public static final String RESULT_JD_LOCATION_XPATH = "span[@class='t3']";
    public static final String RESULT_SALARY_RANGE_XPATH = "span[@class='t4']";
    public static final String RESULT_JD_PUBLISH_DATE_XPATH = "span[@class='t5']";
    public static final String RESULT_JD_CAMPUS_ONLY_XPATH = "p[@class='t1 ']/img[@alt='校招']";
    public static final String RESULT_JD_INTERNSHIP_ONLY_XPATH = "p[@class='t1 ']/img[@alt='实习']";
    // 分页导航控件
    public static final String RESULT_PAGE_NAVIGATOR_XPATH = "//li[@class='bk']/a";
    // 首页用户登录按钮
    public static final String HOMEPAGE_USER_LOGIN_BTN_XPATH = "//p[@class='op']/a";
}
