package frankdevhub.job.automatic.core.constants;

/**
 * <p>Title:@ClassName SeleniumConstants.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/6 20:39
 * @Version: 1.0
 */
public class SeleniumConstants {
    //selenium constants
    //web element attribute name
    public static final String ATTRIBUTE_NAME = "name";
    public static final String ATTRIBUTE_TITLE = "title";
    public static final String ATTRIBUTE_TARGET = "target";
    public static final String ATTRIBUTE_HREF = "href";
    public static final String ATTRIBUTE_CLASS = "class";
    public static final String ATTRIBUTE_VALUE = "value";

    //job search text box
    public static final String INPUT_SEARCH_CLASS = "ipt";
    public static final String INPUT_SEARCH_XPATH = "//p[@class='ipt']/input";

    //job search submit button
    public static final String SUBMIT_SEARCH_XPATH = "//div[@class='ush top_wrap']/button";

    //job search result list
    public static final String SEARCH_RESULT_LIST_XPATH = "//div[@class='dw_table']/div[@class='el']";

    //job search result class names
    public static final String RESULT_JD_NAME_CLASS = "t1";
    public static final String RESULT_COMPANY_NAME_CLASS = "t2";
    public static final String RESULT_JD_LOCATION_CLASS = "t3";
    public static final String RESULT_SALARY_RANGE_CLASS = "t4";
    public static final String RESULT_JD_PUBLISH_DATE_CLASS = "t5";

    //job search result xpath (based on the row header element on each row)
    public static final String RESULT_JD_NAME_XPATH = "p/span/a";
    public static final String RESULT_COMPANY_NAME_XPATH = "span[@class='t2']/a";
    public static final String RESULT_JD_LOCATION_XPATH = "span[@class='t3']";
    public static final String RESULT_SALARY_RANGE_XPATH = "span[@class='t4']";
    public static final String RESULT_JD_PUBLISH_DATE_XPATH = "span[@class='t5']";
    public static final String RESULT_JD_CAMPUS_ONLY_XPATH = "p[@class='t1 ']/img[@alt='校招']";
    public static final String RESULT_JD_INTERNSHIP_ONLY_XPATH = "p[@class='t1 ']/img[@alt='实习']";

}
