package data.utils;

import frankdevhub.job.automatic.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

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

@Slf4j
public class ResourceDownloadTest {

    private static final String MIRROR_INDEX = "http://npm.taobao.org/mirrors/chromedriver/"; //驱动镜像资源地址
    private static final String RESOURCE_INDEX = "http://npm.taobao.org/mirrors/chromedriver/2.0/chromedriver_win32.zip"; //驱动镜像资源地址
    private static final String RESOURCE_INDEX_1 = "http://www.frankdevhub.site/robots.txt"; //外链资源文件地址

    /**
     * 测试获取文件名
     */
    @Test
    public void testGetFileName() {
        String url = RESOURCE_INDEX;
        log.info("url = " + RESOURCE_INDEX);
        String fileName = url.substring(url.lastIndexOf("/") + 1); //截取地址获取文件名称
        log.info("fileName = " + fileName);
    }

    /**
     * 测试下载外链数据文件
     *
     * @param path 链接地址
     * @throws IOException,BusinessException
     */
    private void downloadByDataInputStream(String path) throws IOException, BusinessException {
        String fileName = path.substring(path.lastIndexOf("/") + 1);
        String savePath = "D:\\download_test\\" + fileName; //默认下载存储的目录

        File downloadFile = new File(savePath);
        if (!downloadFile.exists())
            downloadFile.createNewFile();
        URL url = new URL(path);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        int code = urlConnection.getResponseCode();
        log.info("code = " + code);
        if (code != HttpURLConnection.HTTP_OK)
            throw new BusinessException("cannot read remote file");

        log.info("start to download");
        Long start = System.currentTimeMillis();
        DataInputStream in = new DataInputStream(urlConnection.getInputStream());
        DataOutputStream out = new DataOutputStream(new FileOutputStream(savePath));
        byte[] buffer = new byte[2048];
        int count;
        while ((count = in.read(buffer)) > 0)
            out.write(buffer, 0, count);
        try {
            if (out != null)
                out.close();
            if (in != null)
                in.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Long end = System.currentTimeMillis();
            log.info("download complete, time cost = " + (end - start) / 1000 + "sec");
        }
    }

    /**
     * 测试下载外链数据文件
     */
    @Test
    public void testDownloadByDataInputStream() {
        String[] examples = new String[]{RESOURCE_INDEX, RESOURCE_INDEX_1};
        for (String ex : examples) {
            try {
                downloadByDataInputStream(ex);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
