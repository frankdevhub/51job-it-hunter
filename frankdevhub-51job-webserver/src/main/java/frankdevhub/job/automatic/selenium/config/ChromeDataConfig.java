package frankdevhub.job.automatic.selenium.config;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

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

    public synchronized static String config(String root, String dataName) throws IOException {
        String destDir = WIN_TARGET + dataName;

        System.out.println(String.format("chrome cache directory location:[%s]", root));
        System.out.println(String.format("copy chrome cache directory location:[%s]", destDir));

        File rootFile = new File(root);
        File destFile = new File(destDir);

        System.out.println("start to copy cache directory from source path to dest path");
        FileUtils.copyDirectory(rootFile, destFile);
        System.out.println(String.format("copy complete, directory location:[%s]", destDir));

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
