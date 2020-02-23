package clients;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import frankdevhub.job.automatic.web.clients.JobPlatformClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title:@ClassName JobPlatformClientTest.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/23 11:23
 * @Version: 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class JobPlatformClientTest {

    private final Logger LOGGER = LoggerFactory.getLogger(JobPlatformClientTest.class);

    private final String TEST_RESULT_PAGE = "https://search.51job.com/list/020000,000000,0000,00,9,99,java,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
    private final String TEST_SEARCH_KEY = "java";
    private final String TEST_CONTENT = "<img http://sss src=\"http://www.foo.com/a.png\">http://sss/";
    private final String SEARCH_RESULT_REGEX = "([0-9]+)(.html?)";

    @Test
    public void testGetJobSearchResult() throws IOException, XpathSyntaxErrorException {
        LOGGER.begin().info("run test method {{testGetJobSearchResult}} start");
        JobPlatformClient client = new JobPlatformClient();
        client.getJobSearchResult(TEST_RESULT_PAGE, TEST_SEARCH_KEY);

        LOGGER.begin().info("run test method {{testGetJobSearchResult}} complete");
    }

    @Test
    public void testRegexGroupReplace() {
        String content = TEST_CONTENT;

        System.out.println(content);

        String pattern = "<img\\s*([^>]*)\\s*src=\\\"(http://.*?/)(.*?)\\\"\\s*([^>]*)>(http://.*?/)";
        StringBuffer operatorStr = new StringBuffer(content);
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(content);
        while (m.find()) {
            operatorStr.replace(m.start(2), m.end(2), "/");
            m = p.matcher(operatorStr);
        }

        System.out.println(operatorStr);
    }

    @Test
    public void testGetPreviousPageUrl() {
        LOGGER.begin().info("run test method {{testGetPreviousPageUrl}} start");
        String url = TEST_RESULT_PAGE;
        Matcher matcher = Pattern.compile(SEARCH_RESULT_REGEX).matcher(url);
        if (matcher.find()) {
            String matcher_0 = matcher.group(0);
            String matcher_1 = matcher.group(1);
            String matcher_2 = matcher.group(2);

            System.out.println("matcher_0 = " + matcher_0);
            System.out.println("matcher_1 = " + matcher_1);
            System.out.println("matcher_2 = " + matcher_2);
        } else
            throw new RuntimeException("search result page url can not match regex example");

        LOGGER.begin().info("run test method {{testGetPreviousPageUrl}} complete");
    }
}
