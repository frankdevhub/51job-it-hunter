package selenium.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: MarkIdGeneratorTest
 * @Description: 测试生成唯一标识
 * @date: 2021/1/27 23:05
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class MarkIdGeneratorTest {

    private final String example = "https://jobs.51job.com/shanghai/126397572.html?s=01&t=0";

    /**
     * 生成平台唯一的标识符
     * 生成策略：依据链接进行生成,提取链接中的唯一特征值
     * 例: https://jobs.51job.com/shanghai/126397572.html?s=01&t=0
     * 提取:126397572
     */
    @Test
    public void testGenerateJobMarkId() {
        String exp = "((?<key>[1-9]\\d*\\.?\\d*)(.html))";
        Pattern p = Pattern.compile(exp);
        Matcher m = p.matcher(example);
        if (m.find()) {
            log.info("matched");
            System.out.println(m.group("key"));
        } else {
            log.info("not matched");
        }
    }
}
