package frankdevhub.job.webauto.selenium.config;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * <p>Title:ChromeDataConfig.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @author frankdevhub
 * @date:2019-05-12 17:28
 */

public class ChromeDataConfig {
    public static final String WIN_CHROME_APP = "Application";
    public static final String WIN_CHROME_DATA = "User Data";

    public static final String WIN_SOURCE = "C:/Users/Administrator/AppData/Local/Google/Chrome/User Data";
    //public static final String WIN_TARGET = "C:/Users/Administrator/AppData/Local/Google/Automation";
    public static final String WIN_TARGET = "C:/Automation/";

    public synchronized static String createDataName(String thread) {
        StringBuilder builder = new StringBuilder();
        long time = System.currentTimeMillis();
        String timeStr = Long.toString(time);

        String dataName = builder.append(timeStr).append("-").append(thread).toString();
        return dataName;
    }

    public synchronized static String getLocal() {
        return WIN_SOURCE;
    }

    public synchronized static String config(String root, String dataName) throws IOException, InterruptedException {
        String destDir = WIN_TARGET + dataName;

        System.out.println("dest-name:" + destDir);
        System.out.println("chrome cache source:" + root);

        File rootFile = new File(root);
        File destFile = new File(destDir);
        System.out.println("copy chrome config file");
        FileUtils.copyDirectory(rootFile, destFile);
        return destDir;
    }

    public synchronized static void cleanData() throws IOException {
        File cacheFolder = new File(WIN_TARGET);
        File[] files = cacheFolder.listFiles();
        for (File file : files) {
            FileUtils.forceDelete(file);
        }
    }

}
