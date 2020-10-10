package utils;

import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import frankdevhub.job.automatic.core.exception.BusinessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <p>Title:@ClassName ResourceDownloadTest.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/18 5:33
 * @Version: 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class ResourceDownloadTest {

    private final Logger LOGGER = LoggerFactory.getLogger(ResourceDownloadTest.class);

    private static final String MIRROR_INDEX = "http://npm.taobao.org/mirrors/chromedriver/";
    private static final String RESOURCE_INDEX = "http://npm.taobao.org/mirrors/chromedriver/2.0/chromedriver_win32.zip";
    private static final String RESOURCE_INDEX_1 = "http://www.frankdevhub.site/robots.txt";


    @Test
    public void testGetFileName() {

        LOGGER.begin().info("runt test method {{testGetFileName}} start");
        String url = RESOURCE_INDEX;
        System.out.println("url = " + RESOURCE_INDEX);

        String fileName = url.substring(url.lastIndexOf("/") + 1);
        System.out.println("fileName = " + fileName);

        LOGGER.begin().info("runt test method {{testGetFileName}} complete");
    }

    private void downloadByDataInputStream(String path) throws IOException, BusinessException {
        String fileName = path.substring(path.lastIndexOf("/") + 1);
        String savePath = "D:\\download_test\\" + fileName;

        File downloadFile = new File(savePath);
        if (!downloadFile.exists())
            downloadFile.createNewFile();
        URL url = new URL(path);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        int code = urlConnection.getResponseCode();

        System.out.println("code = " + code);
        if (code != HttpURLConnection.HTTP_OK)
            throw new BusinessException("cannot read remote file");

        System.out.println("start to download");
        Long start = System.currentTimeMillis();

        DataInputStream in = new DataInputStream(urlConnection.getInputStream());
        DataOutputStream out = new DataOutputStream(new FileOutputStream(savePath));
        byte[] buffer = new byte[2048];
        int count;
        while ((count = in.read(buffer)) > 0) {
            out.write(buffer, 0, count);
        }

        try {
            if (out != null)
                out.close();
            if (in != null)
                in.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Long end = System.currentTimeMillis();
            System.out.println("download complete, time cost = " + (end - start) / 1000 + "sec");
        }
    }


    @Test
    public void testDownloadByDataInputStream() {
        LOGGER.begin().info("runt test method {{testDownloadByDataInputStream}} start");

        String[] examples = new String[]{RESOURCE_INDEX, RESOURCE_INDEX_1};
        for (String ex : examples) {
            try {
                downloadByDataInputStream(ex);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        LOGGER.begin().info("runt test method {{testDownloadByDataInputStream}} complete");
    }
}
