package frankdevhub.job.automatic.google.drive.ftp.adapter.model;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;

import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * <p>Title:GoogleDriveFactory.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @author frankdevhub
 * @date:2019-04-23 16:32
 */

public class GoogleDriveFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleDriveFactory.class);

    private static final String APPLICATION_NAME = "nyoibo-inkstone-google-drive";

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private final FileDataStoreFactory dataStoreFactory;

    private final HttpTransport httpTransport;

    private AuthorizationCodeInstalledAppExtend authorizationApp;

    private Drive drive;

    public GoogleDriveFactory(Properties configuration) {

        java.io.File DATA_STORE_DIR = new java.io.File(
                "data/google/" + configuration.getProperty("account", "default"));

        try {

            dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);

            httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        } catch (Exception e) {
            throw new RuntimeException("Error intializing google drive API", e);
        }
    }

    public static Drive build(Credential credential) throws Exception {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new Drive.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
    }

    public void init() {
        try {

            Credential credential = authorize();

            drive = new Drive.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME)
                    .build();

            LOGGER.begin().info("Google drive webservice client initialized.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Credential authorize() throws Exception {

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                new InputStreamReader(GFile.class.getResourceAsStream("/client_secrets.json")));
        if (clientSecrets.getDetails().getClientId().startsWith("Enter")
                || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
            System.out.println("Overwrite the src/main/resources/client_secrets.json file with the client secrets file "
                    + "you downloaded from the Quickstart tool or manually enter your Client ID and Secret "
                    + "from https://code.google.com/apis/console/?api=drive#project:275751503302 "
                    + "into src/main/resources/client_secrets.json");
            System.exit(1);
        }

        Set<String> scopes = new HashSet<>();
        scopes.add(DriveScopes.DRIVE);
        scopes.add(DriveScopes.DRIVE_METADATA);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets,
                scopes).setDataStoreFactory(dataStoreFactory).build();
        this.authorizationApp = new AuthorizationCodeInstalledAppExtend(flow, new LocalServerReceiver());

        return this.authorizationApp.authorize("user");
    }

    public Drive getDrive() {
        return drive;
    }

    public AuthorizationCodeInstalledAppExtend getAuthorizationApp() {
        return authorizationApp;
    }
}
