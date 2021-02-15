package frankdevhub.job.automatic.core.parser;

import frankdevhub.job.automatic.core.utils.NumericStringUtils;
import frankdevhub.job.automatic.entities.business.JobSearchResult;
import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.util.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private static final String REGEX_EXPRESSION = ".*(?<prefix>[招聘|招纳|招|需要|急需|需]+)" +
            "(?<numeric>[\u4e00-\u9fa5\u767e\u5343\u96f6]+|[0-9]+|[若干])(?<surfix>人)$";
    //特殊情况 若干
    private static final String EXAMPLE_1 = "若干";
    //正则表达式中文字符匹配
    private static final String REGEX_CH = "[\u4e00-\u9fa5]";

    /**
     * 匹配招聘人数
     *
     * @param text 描述信息
     * @return 计划的招聘人数
     */
    public static String parseText(String text) {
        Assert.notNull(text, "cannot find text");
        log.info("text = {}", text);
        String match = null;

        //过滤去除多余的空格换行符
        text = text.replaceAll("\\s|\\t|\\n", "");
        Pattern p = Pattern.compile(REGEX_EXPRESSION);
        Matcher m = p.matcher(text);
        if (m.find())
            match = m.group("numeric");
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
        Assert.notNull(text, "cannot find text");
        log.info("text = {}", text);
        //匹配关键字
        String match = parseText(text);
        if (null == match)
            return;

        //如果匹配到关键字,进行判断是中文还是数字
        Pattern p = Pattern.compile(REGEX_CH);
        //过滤去除多余的空格换行符
        match = match.replaceAll("\\s|\\t|\\n", "");
        Matcher m = p.matcher(match);
        int num = 0;
        //中文字符
        if (m.find()) {
            if (match.contains(EXAMPLE_1)) {
                num = 99; //默认若干人视为99}
            } else {
                //非大写中文数字转为阿拉伯数字
                num = NumericStringUtils.numberCN2Arab(match);
            }
        } else {
            //数值类型字符
            num = Integer.parseInt(match);
        }
        result.setHeadCount(num);
    }
}
