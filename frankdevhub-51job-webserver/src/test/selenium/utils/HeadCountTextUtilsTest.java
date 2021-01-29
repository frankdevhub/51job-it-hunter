package selenium.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: HeadCountTextUtilsTest
 * @Description: 测试解析招聘人数的正则表达式
 * @date: 2021/1/28 22:54
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */

@Slf4j
@SuppressWarnings("all")
public class HeadCountTextUtilsTest {

    //上海 | 无需经验 | 本科 | 招若干人
    //匹配招聘人数的正则表达式
    private final String REGEX_EXAMPLE = ".*(?<prefix>[招聘|招纳|招|需要|急需|需]+)" +
            "(?<numeric>[\u4e00-\u9fa5\u767e\u5343\u96f6]+|[0-9]+|[若干])(?<surfix>人)$";

    private final String[] examples = new String[]{"招 23 人",  //十位数及以上的型数值
            "招若干人", //若干人
            "招 1 人",  //个位数值
            "招一人",   //文字类型数值
            "上海 | 无需经验 | 本科 | 招若干人", //若干人
            "上海 | 无需经验 招若干人"};  // 若干人

    /**
     * 测试匹配中文前缀
     */
    @Test
    public void testMatchPrefix() {
        String regex = ".*(?<prefix>[招聘|招纳|招|需要|急需|需]+)";
        for (String example : examples) {
            //替换多余的空格换行字符
            String parse = example.replaceAll("(\\s|\\t|\\n)", "");
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(parse);
            log.info("parse = {}", parse);
            if (m.find()) {
                log.info("match = {}", m.group("prefix"));
            } else {
                log.info("not match");
            }
        }
    }

    /**
     * 测试解析招聘人数的正则表达式
     */
    @Test
    public void testRegexExpression() {
        for (String example : examples) {
            //替换多余的空格换行字符
            String parse = example.replaceAll("(\\s|\\t|\\n)", "");
            Pattern p = Pattern.compile(REGEX_EXAMPLE);
            Matcher m = p.matcher(parse);
            log.info("parse = {}", parse);
            if (m.find()) {
                log.info("match = {}", m.group("numeric"));
            } else {
                log.info("not match");
            }
        }
    }


}
