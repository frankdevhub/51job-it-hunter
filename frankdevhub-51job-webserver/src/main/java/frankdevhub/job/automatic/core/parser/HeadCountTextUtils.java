package frankdevhub.job.automatic.core.parser;

import frankdevhub.job.automatic.entities.JobSearchResult;
import lombok.extern.slf4j.Slf4j;

/**
 * @Title: HeadCountTextUtils
 * @Description: 招聘人数解析
 * @date: 2021/1/27 22:32
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */

@Slf4j
@SuppressWarnings("all")
public class HeadCountTextUtils {

    //上海 | 无需经验 | 本科 | 招若干人
    //匹配招聘人数的正则表达式
    private static final String REGEX_EXAMPLE = ".*(?<prefix>[招聘|招纳|招|需要|急需|需]+)" +
            "(?<numeric>[\u4e00-\u9fa5\u767e\u5343\u96f6]+|[0-9]+|[若干])(?<surfix>人)$";

    /**
     * 匹配招聘人数
     *
     * @param text 描述信息
     * @return 计划的招聘人数
     */
    public static String parseText(String text) {
        String match = null;
        return match;
    }

    /**
     * 匹配招聘人数
     *
     * @param text   描述信息
     * @param result 职位搜索返回的结果集
     * @return 计划的招聘人数
     */
    public static void parseText(String text, JobSearchResult result) {
        String match = null;

    }
}
