package frankdevhub.job.automatic.core.constants;

/**
 * <p>Title:@ClassName BusinessConstants.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/1/28 22:42
 * @Version: 1.0
 */
public class BusinessConstants {
    //restful service
    public static final String SUCCESS = "success";
    //selenium configuration
    public static final String SELENIUM_CACHE_ROOT_NULL = "selenium cache root directory path should not be null";
    public static final String SELENIUM_CACHE_ROOT_NOT_EXISTS = "selenium cache root directory not exist";
    public static final String SELENIUM_CACHE_FILE_NAME_NULL = "selenium cache file name should not be null";
    public static final String SELENIUM_WEB_DRIVER_PATH_NULL = "selenium web driver path should not be null";
    public static final String SELENIUM_WEB_DRIVER_NOT_EXIST = "selenium web driver not exist";
    //character unit argument
    public static final String CHARACTER_NULL_ARGUMENT = "character should not be null";
    public static final String INVALID_CHINESE_CHARACTER = "not a chinese character";
    public static final String INVALID_ENGLISH_CHARACTER = "not an english character";
    //https://www.51job.com homepage url
    public static final String JOB_PLATFORM_HOMEPAGE = "http://www.51job.com";
    //enum constants
    public static final String CHARACTER_BOOLEAN_VAREIABLE_CONFLICT = "time unit variable type conflict";
    //default resource location
    public static final String DEFAULT_RESOURCE_LOCATION = "上海";
    //web page keywords and properties
    public static final String JOB_PLATFORM_HOMEPAGE_TITLE_KEY = "招聘网_人才网";
    public static final String JOB_SEARCH_KEYWORD_NULL = "job search key word search should not be null";
    //salary range regex match failure
    public static final String SALARY_RANGE_REGEX_MATCH_ERROR = "salary range regex match failure";
    //next page not available
    public static final String NEXT_PAGE_NOT_AVAILABLE = "next page not available, this may be the last page";
    //DB error
    public static final String DUPLICATE_DB_RECORDS_WITH_MARKID = "duplicate records found with markId";
    //default temp cache file name
    public static final String DEFAULT_TEMP_COOKIE_FILE = "temp_cookie.dat";
}
