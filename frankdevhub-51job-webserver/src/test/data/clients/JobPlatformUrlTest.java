package data.clients;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: JobPlatformUrlTest
 * @Description: 依照平台链接规则获取指定链接
 * @date: 2021/1/31 1:08
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */

@Slf4j
@SuppressWarnings("all")
public class JobPlatformUrlTest {

    /**
     * 获取上一页页面对象的字符串
     *
     * @param url 页面链接地址
     * @return 页面对象的字符串
     * @throws IOException
     */
    public String getPreviousResultPage(String url) {
        String regex = "([0-9]+)(.html?)"; //匹配网页元素对象
        Matcher matcher = Pattern.compile(regex).matcher(url);
        String index; //当前页索引
        String previous; //上一页索引
        if (matcher.find()) {
            index = matcher.group(1);
            previous = new Integer(Integer.parseInt(index) - 1).toString();
        } else {
            throw new RuntimeException("url regex cannot match page url");
        }
        //获取下一页的链接
        StringBuffer buffer = new StringBuffer(url);
        buffer.replace(matcher.start(1), matcher.end(1), previous);
        String previousPage = buffer.toString();
        log.info("previous page url = " + previousPage);
        return previousPage;
    }

    /**
     * 获取下一页页面对象的字符串
     *
     * @param url 页面链接地址
     * @return 页面对象的字符串
     */
    public String getNextResultPage(String url) {
        String regex = "([0-9]+)(.html?)"; //匹配网页元素对象
        Matcher matcher = Pattern.compile(regex).matcher(url);
        String index; //当前页索引
        String next; //下一页索引
        if (matcher.find()) {
            index = matcher.group(1);
            next = new Integer(Integer.parseInt(index) + 1).toString();
        } else {
            throw new RuntimeException("url regex cannot match page url");
        }
        //获取下一页的链接
        StringBuffer buffer = new StringBuffer(url);
        buffer.replace(matcher.start(1), matcher.end(1), next);
        String nextPage = buffer.toString();
        log.info("next page url = " + nextPage);
        return nextPage;
    }


    /**
     * 获取下一页页面对象的字符串
     */
    @Test
    public void testGetNextResultPage() {
        String example = "https://search.51job.com/list/020000,000000,0000,00,9,99,java,2,1.html?lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare=";
        String result = getNextResultPage(example); //下一页链接
        System.out.println(result);
    }

}
