package frankdevhub.job.automatic.google.drive.ftp.adapter;

import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import frankdevhub.job.automatic.JobWebAutoService;
import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import frankdevhub.job.automatic.google.drive.ftp.adapter.model.GoogleDriveFactory;
import frankdevhub.job.automatic.google.drive.ftp.adapter.utils.JarUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>Title:GoogleDriveFtpAdapterFactory.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @author frankdevhub
 * @date:2019-04-27 19:25
 */

public class GoogleDriveFtpAdapterFactory {
    public static final Logger LOGGER = LoggerFactory.getLogger(GoogleDriveFtpAdapterFactory.class);

    private static GoogleDriveFtpAdapter googleDriveFtpAdapter;

    private static final String DATA_FOLDER = "data";

    public static GoogleDriveFtpAdapter getInstance() {
        if (googleDriveFtpAdapter == null) {
            synchronized (GoogleDriveFtpAdapter.class) {
                if (googleDriveFtpAdapter == null) {
                    init();
                    return googleDriveFtpAdapter;
                }
            }
        }
        return googleDriveFtpAdapter;
    }


    public static String getAuthorizationUrl() {
        if (getInstance().isInit()) {
            GoogleDriveFactory driveFactory = getInstance().getGoogleDriveFactory();
            AuthorizationCodeRequestUrl url = driveFactory.getAuthorizationApp().getRequestUrl();
            if (url == null)
                throw new NullPointerException("AuthorizationRequestUrl is null");
            return url.toString();
        }
        return null;
    }

    private static void cleanDataFolders() {
        try {
            FileUtils.forceDelete(new File(DATA_FOLDER));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void init() {
        try {
            cleanDataFolders();
            JarUtils.printManifestAttributesToString();
            LOGGER.begin().info("Program info: " + JarUtils.getManifestAttributesAsMap());
            LOGGER.begin().info("Loading configuration...");

            Properties configuration = loadPropertiesFromClasspath();
            configuration.putAll(loadProperties("configuration.properties"));

            LOGGER.begin().info("Creating application with configuration '" + configuration + "'");
            googleDriveFtpAdapter = new GoogleDriveFtpAdapter(configuration);

            registerShutdownHook();

            start();

        } catch (Exception e) {
            LOGGER.begin().error("Error loading app");
        }

    }

    private static void start() {
        googleDriveFtpAdapter.start();
    }

    private static void stop() {
        googleDriveFtpAdapter.stop();
    }

    private static void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.begin().info("Shuting down...");
            stop();
            LOGGER.begin().info("Good bye!");
        }));
    }

    private static Properties loadProperties(String propertiesFilename) {
        Properties properties = new Properties();
        FileInputStream inStream = null;
        try {
            LOGGER.begin().info("Loading properfiles file '" + propertiesFilename + "'...");
            inStream = new FileInputStream(propertiesFilename);
            properties.load(inStream);
        } catch (Exception ex) {
            LOGGER.begin().warn("Exception loading file '" + propertiesFilename + "'.");
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (Exception ex) {
                    LOGGER.begin().error(ex.getMessage());
                }
            }
        }
        LOGGER.begin().info("Properfiles loaded: '" + properties + "'");
        return properties;
    }

    static Properties loadPropertiesFromClasspath() {
        Properties properties = new Properties();

        InputStream configurationStream = JobWebAutoService.class.getResourceAsStream("/configuration.properties");
        if (configurationStream == null) {
            return properties;
        }

        try {
            LOGGER.begin().info("Loading properties from classpath...");
            properties.load(configurationStream);
            LOGGER.begin().info("Properties loaded: '" + properties + "'");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (configurationStream != null) {
                try {
                    configurationStream.close();
                } catch (Exception ex) {
                    LOGGER.begin().error(ex.getMessage());
                }
            }
        }
        return properties;
    }
}
