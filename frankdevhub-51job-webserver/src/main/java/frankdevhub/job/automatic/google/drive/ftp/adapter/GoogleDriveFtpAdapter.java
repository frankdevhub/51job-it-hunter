package frankdevhub.job.automatic.google.drive.ftp.adapter;

import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import frankdevhub.job.automatic.google.drive.ftp.adapter.controller.Controller;
import frankdevhub.job.automatic.google.drive.ftp.adapter.model.Cache;
import frankdevhub.job.automatic.google.drive.ftp.adapter.model.GoogleDrive;
import frankdevhub.job.automatic.google.drive.ftp.adapter.model.GoogleDriveFactory;
import frankdevhub.job.automatic.google.drive.ftp.adapter.model.SQLiteCache;
import frankdevhub.job.automatic.google.drive.ftp.adapter.service.FtpGdriveSynchService;
import frankdevhub.job.automatic.google.drive.ftp.adapter.view.ftp.GFtpServerFactory;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;

import java.io.IOException;
import java.net.Socket;
import java.util.Properties;


/**
 * <p>Title:GoogleDriveFtpAdapter.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @author frankdevhub
 * @date:2019-04-23 02:05
 */

public class GoogleDriveFtpAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleDriveFtpAdapter.class);

    private final org.apache.ftpserver.FtpServer server;
    private final FtpGdriveSynchService cacheUpdater;
    private final Cache cache;
    private final Controller controller;
    private final GoogleDriveFactory googleDriveFactory;

    private static boolean init = false;


    GoogleDriveFtpAdapter(Properties configuration) {

        int port = Integer.parseInt(configuration.getProperty("port", String.valueOf(1821)));
        if (!available(port)) {
            throw new IllegalArgumentException("Invalid argument. Port '" + port + "' already in used");
        }

        cache = new SQLiteCache(configuration);
        googleDriveFactory = new GoogleDriveFactory(configuration);
        googleDriveFactory.init();

        GoogleDrive googleDrive = new GoogleDrive(googleDriveFactory.getDrive());
        cacheUpdater = new FtpGdriveSynchService(cache, googleDrive);
        controller = new Controller(cache, googleDrive, cacheUpdater);

        FtpServerFactory serverFactory = new GFtpServerFactory(controller, cache, configuration, cacheUpdater);
        server = serverFactory.createServer();

        init = true;

    }

    private static boolean available(int port) {
        try (Socket ignored = new Socket("localhost", port)) {
            return false;
        } catch (IOException ignored) {
            return true;
        }
    }

    void start() {
        try {
            cacheUpdater.start();
            server.start();
            LOGGER.begin().info("Application started!");
        } catch (FtpException e) {
            throw new RuntimeException(e);
        }
    }

    void stop() {
        cacheUpdater.stop();
        server.stop();
        LOGGER.begin().info("Application stopped.");
    }

    public org.apache.ftpserver.FtpServer getServer() {
        return server;
    }

    public FtpGdriveSynchService getCacheUpdater() {
        return cacheUpdater;
    }

    public Cache getCache() {
        return cache;
    }

    public Controller getController() {
        return controller;
    }

    public GoogleDriveFactory getGoogleDriveFactory() {
        return googleDriveFactory;
    }

    public static boolean isInit() {
        return init;
    }
}
