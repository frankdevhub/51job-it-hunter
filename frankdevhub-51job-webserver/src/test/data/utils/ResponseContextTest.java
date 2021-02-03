package data.utils;

import org.junit.Test;
import tk.mybatis.mapper.util.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: ResponseContextTest
 * @Description: //TODO
 * @date: 2021/1/29 23:38
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */

@SuppressWarnings("all")
public class ResponseContextTest {

    //源码window.__SEARCH_RESULT__=句柄处开始
    private final String REGEX_EXPRESSION = "window.__SEARCH_RESULT__\\s?=\\s?(?<context>\\{.*\\})</script>";

    @Test
    public void testGetResponseJson() {
        String str = "";
        //jsop返回的报文格式输出为字符串进行解析
        File file = new File("src/main/resources/example/search-result-page.txt");
        try {
            FileInputStream in = new FileInputStream(file);
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            str = new String(buffer, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.notNull(str, "str cannot be found");
        //过滤去除空格换行符
        str = str.replaceAll("\\n", "");
        Pattern p = Pattern.compile(REGEX_EXPRESSION);
        Matcher m = p.matcher(str);
        if (m.find()) {
            System.out.println(m.group("context"));
        }

    }

}
