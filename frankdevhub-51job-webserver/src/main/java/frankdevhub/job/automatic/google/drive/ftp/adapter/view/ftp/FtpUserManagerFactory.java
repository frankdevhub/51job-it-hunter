package frankdevhub.job.automatic.google.drive.ftp.adapter.view.ftp;

import java.util.Properties;

import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.usermanager.ClearTextPasswordEncryptor;
import org.apache.ftpserver.usermanager.UserManagerFactory;

/**
 * <p>Title:FtpUserManagerFactory.java</p>  
 * <p>Description: </p>  
 * <p>Copyright: Copyright (c) 2019</p>  
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>  
 * @author frankdevhub   
 * @date:2019-04-23 17:13
 */

class FtpUserManagerFactory implements UserManagerFactory {

    private final Properties configuration;

    FtpUserManagerFactory(Properties configuration) {
        this.configuration = configuration;
    }

    @Override
    public UserManager createUserManager() {
        return new FtpUserManager(configuration, "admin", new ClearTextPasswordEncryptor());
    }
}

