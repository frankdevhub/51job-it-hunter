package selenium.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: HeadCountTextUtilsTest
 * @Description: //TODO
 * @date: 2021/1/28 22:54
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SuppressWarnings("all")
public class HeadCountTextUtilsTest {

    //上海 | 无需经验 | 本科 | 招若干人
    private final String REGEX_EXAMPLE = "^(招聘|招纳|招|需要|需){0,1}" +
            "(?<numeric>([\\u4e00-\\u9fa5\\u767e\\u5343\\u96f6]|[0-9]|若干)*)(人{0,1})$"; //匹配招聘人数的正则表达式

    private final String[] examples = new String[]{"招 23 人", "招 1 人", "招一人",
            "上海 | 无需经验 | 本科 | 招若干人",
            "上海 | 无需经验 招若干人"};

    /**
     * 测试解析招聘人数的正则表达式
     */
    @Test
    public void testRegexExpression() {
        for (String example : examples) {
            String parse = example.trim().replaceAll("(\\s|\\t|\\n)", ""); //去除多余空格
            Pattern p = Pattern.compile(REGEX_EXAMPLE);
            Matcher m = p.matcher(parse);
            log.info("parse = {}", parse);

            if (m.find()) {
                log.info("match = {}", m.group("numeric"));
            } else {
                log.info("cannot match");
            }
        }
    }

}
