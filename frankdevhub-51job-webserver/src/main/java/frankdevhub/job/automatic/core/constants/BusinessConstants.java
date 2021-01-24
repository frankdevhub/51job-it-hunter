package frankdevhub.job.automatic.core.constants;

@SuppressWarnings("all")
public class BusinessConstants {
    // 接口规范常用字段
    public static final String SUCCESS = "success";
    // selenium configuration
    public static final String SELENIUM_CACHE_ROOT_NULL = "selenium cache root directory path should not be null";
    public static final String SELENIUM_CACHE_ROOT_NOT_EXISTS = "selenium cache root directory not exist";
    public static final String SELENIUM_CACHE_FILE_NAME_NULL = "selenium cache file name should not be null";
    public static final String SELENIUM_WEB_DRIVER_PATH_NULL = "selenium web driver path should not be null";
    public static final String SELENIUM_WEB_DRIVER_NOT_EXIST = "selenium web driver not exist";
    // 字符信息常量
    public static final String CHARACTER_NULL_ARGUMENT = "character should not be null";
    public static final String INVALID_CHINESE_CHARACTER = "not a chinese character";
    public static final String INVALID_ENGLISH_CHARACTER = "not an english character";
    // https://www.51job.com 首页地址
    public static final String JOB_PLATFORM_HOMEPAGE = "http://www.51job.com";
    // 枚举类变量
    public static final String CHARACTER_BOOLEAN_VAREIABLE_CONFLICT = "time unit variable type conflict";
    // 默认职位地点
    public static final String DEFAULT_RESOURCE_LOCATION = "上海";
    // 网页搜索关键字项
    public static final String JOB_PLATFORM_HOMEPAGE_TITLE_KEY = "招聘网_人才网";
    public static final String JOB_PLATFORM_HOMEPAGE_TITLE_KEY_1 = "招聘";
    public static final String JOB_SEARCH_KEYWORD_NULL = "job search key word search should not be null";
    // 匹配描述薪资范围的正则表达式
    public static final String SALARY_RANGE_REGEX_MATCH_ERROR = "salary range regex match failure";
    // 下一页无数据
    public static final String NEXT_PAGE_NOT_AVAILABLE = "next page not available, this may be the last page";
    // 数据库异常
    public static final String DUPLICATE_DB_RECORDS_WITH_MARKID = "duplicate records found with markId";
    // 默认客户端浏览器会话缓存文件名
    public static final String DEFAULT_TEMP_COOKIE_FILE = "temp_cookie.dat";
}
