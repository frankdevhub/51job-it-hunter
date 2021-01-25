package selenium.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title:@ClassName selenium.utils.SalaryRangeStringTest.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/1/26 23:49
 * @Version: 1.0
 */

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class SalaryRangeStringTest {

    private final String rangeRegex =
            "(?<min>(([1-9]\\d*\\.?\\d+)|(0\\.\\d*[1-9])|(\\d+))?)" +
                    "(?<hyphen>((—|-)+)?)" +
                    "(?<max>(([1-9]\\d*\\.?\\d+)|(0\\.\\d*[1-9])|(\\d+))?)" +
                    "(?<numeric>[\\u4e00-\\u9fa5]?)(/?)(?<date>[\\u4e00-\\u9fa5]?)";

    private void testExample(String example) {
        Matcher matcher = Pattern.compile(rangeRegex).matcher(example);
        log.info("using example: " + example + "");
        if (matcher.find()) {

            String match_0 = matcher.group(0);
            String match_1 = matcher.group("min");
            String match_2 = matcher.group("hyphen");
            String match_3 = matcher.group("max");
            String match_4 = matcher.group("numeric");
            String match_5 = matcher.group("date");

            log.info("match_0 = " + match_0);
            log.info("match_1 = " + match_1);
            log.info("match_2 = " + match_2);
            log.info("match_3 = " + match_3);
            log.info("match_4 = " + match_4);
            log.info("match_5 = " + match_5);

        } else
            log.info("no matched element found !!!");
        log.info("\n");
    }

    @Test
    public void testExamplesUsingRegex_1() {
        log.info("runt test method {{testExamplesUsingRegex_1}} start");
        String[] examples = new String[]{"150元/天", "2.0元/天", "250 元/天", "56.78 元/天"};
        try {
            for (String example : examples)
                testExample(example);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("runt test method {{testExamplesUsingRegex_1}} start");
    }


    @Test
    public void testExamplesUsingRegex() {
        log.info("runt test method {{testExamplesUsingRegex}} start");
        String[] examples = new String[]{"2-3万/月", "2——7万/年", "23.9-3万/月", "12000-15000/月", "23.0-334.98"};
        try {
            for (String example : examples)
                testExample(example);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("runt test method {{testExamplesUsingRegex}} complete");
    }
}
