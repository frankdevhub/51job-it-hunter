package frankdevhub.job.automatic.core.constants;

@SuppressWarnings("all")
public class BusinessConstants {
    // 接口规范常用字段
    public static final String SUCCESS = "success"; //返回成功
    // selenium configuration
    public static final String SELENIUM_CACHE_ROOT_NULL = "selenium cache root directory path should not be null"; //驱动缓存路径配置不存在
    public static final String SELENIUM_CACHE_ROOT_NOT_EXISTS = "selenium cache root directory not exist"; //驱动缓存配置文件不存在
    public static final String SELENIUM_CACHE_FILE_NAME_NULL = "selenium cache file name should not be null"; //驱动缓存路径配配置为空
    public static final String SELENIUM_WEB_DRIVER_PATH_NULL = "selenium web driver path should not be null"; //无法加载驱动,路径配置为空
    public static final String SELENIUM_WEB_DRIVER_NOT_EXIST = "selenium web driver not exist"; //无法加载驱动,驱动不存在
    // 字符信息常量
    public static final String CHARACTER_NULL_ARGUMENT = "character should not be null"; //字符不能为空
    public static final String INVALID_CHINESE_CHARACTER = "not a chinese character"; //非中文的字符异常
    public static final String INVALID_ENGLISH_CHARACTER = "not an english character"; //非英文的字符的异常
    // https://www.51job.com 首页地址
    public static final String JOB_PLATFORM_HOMEPAGE = "http://www.51job.com";
    // 上海地区入口
    public static final String JOB_PLATFORM_HOMEPAGE_SH = "https://search.51job.com/list/020000,000000,0000,00,9,99,%2B,2,1.html?lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare=";
    // 枚举类变量
    public static final String CHARACTER_BOOLEAN_VAREIABLE_CONFLICT = "time unit variable type conflict";
    // 默认职位地点
    public static final String DEFAULT_RESOURCE_LOCATION = "上海"; //默认搜索地区名
    // 网页搜索关键字项
    public static final String JOB_PLATFORM_HOMEPAGE_TITLE_KEY = "招聘网_人才网"; //页面标题关键字
    public static final String JOB_PLATFORM_HOMEPAGE_TITLE_KEY_1 = "招聘"; //页面标题关键字
    public static final String JOB_SEARCH_KEYWORD_NULL = "job search key word search should not be null"; //职位搜索关键字不能为空
    // 匹配描述薪资范围的正则表达式
    public static final String SALARY_RANGE_REGEX_MATCH_ERROR = "salary range regex match failure"; //无法匹配薪资范围的描述异常
    // 下一页无数据
    public static final String NEXT_PAGE_NOT_AVAILABLE = "next page not available, this may be the last page"; //当前页为最后一页
    // 数据库异常
    public static final String DUPLICATE_DB_RECORDS_WITH_MARKID = "duplicate records found with markId"; //重复的唯一识别标识异常
    // 默认客户端浏览器会话缓存文件名
    public static final String DEFAULT_TEMP_COOKIE_FILE = "temp_cookie.dat"; //缓存会话文件
}
