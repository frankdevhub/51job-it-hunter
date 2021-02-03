package data.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title:@ClassName selenium.utils.SalaryRangeStringTest.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Description: 测试薪资范围描述的解析
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/1/26 23:49
 * @Version: 1.0
 */

@Slf4j
@SuppressWarnings("all")
public class SalaryRangeStringTest {

    //描述薪资范围的正则表达式
    private final String rangeRegex =
            "(?<min>(([1-9]\\d*\\.?\\d+)|(0\\.\\d*[1-9])|(\\d+))?)" +
                    "(?<hyphen>((—|-)+)?)" +
                    "(?<max>(([1-9]\\d*\\.?\\d+)|(0\\.\\d*[1-9])|(\\d+))?)" +
                    "(?<numeric>[\\u4e00-\\u9fa5]?)(/?)(?<date>[\\u4e00-\\u9fa5]?)";

    /**
     * 测试解析样式数据
     *
     * @param example 测试用例字符串
     */
    private void testExample(String example) {
        Matcher matcher = Pattern.compile(rangeRegex).matcher(example);
        log.info("using example: " + example + "");
        //如果匹配成功则输出所有组合内匹配的值
        if (matcher.find()) {
            String match_0 = matcher.group(0);
            String match_1 = matcher.group("min"); //薪资最小值
            String match_2 = matcher.group("hyphen"); //破折号"-"
            String match_3 = matcher.group("max"); //薪资最大值
            String match_4 = matcher.group("numeric"); //计量的数值单位
            String match_5 = matcher.group("date"); //计量的时间单位
            //匹配到各个组内的值
            log.info("match_0 = " + match_0); //match_0
            log.info("match_1 = " + match_1); //match_1
            log.info("match_2 = " + match_2); //match_2
            log.info("match_3 = " + match_3); //match_3
            log.info("match_4 = " + match_4); //match_4
            log.info("match_5 = " + match_5); //match_5
        } else
            log.info("no matched element found !!!");
        log.info("\n");
    }

    /**
     * 测试正则表达式匹配薪资范围中的数据
     */
    @Test
    public void testExamplesUsingRegex_1() {
        try {
            //测试匹配单个数值
            String[] examples = new String[]{"150元/天", //整数
                    "2.0元/天",  //小数
                    "250 元/天", //含有空格的整数
                    "56.78 元/天"}; //含有空格的小数
            for (String example : examples)
                testExample(example);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 测试正则表达式匹配薪资范围中的数据
     */
    @Test
    public void testExamplesUsingRegex_2() {
        //测试匹配区间范围
        String[] examples = new String[]{"2-3万/月", //整数
                "2——7万/年",  //多个破折号作为间隔
                "23.9-3万/月", //小数与整数
                "12000-15000/月", //默认各位不显示单位的区间范围
                "23.0-334.98"}; //默认没有任何单位显示的区间范围
        try {
            for (String example : examples)
                testExample(example);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
