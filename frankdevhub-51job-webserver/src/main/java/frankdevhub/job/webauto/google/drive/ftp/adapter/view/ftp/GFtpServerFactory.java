package frankdevhub.job.webauto.google.drive.ftp.adapter.view.ftp;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ftpserver.ConnectionConfigFactory;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.command.CommandFactoryFactory;
import org.apache.ftpserver.ftplet.Ftplet;
import org.apache.ftpserver.listener.ListenerFactory;

import frankdevhub.job.webauto.google.drive.ftp.adapter.controller.Controller;
import frankdevhub.job.webauto.google.drive.ftp.adapter.model.Cache;
import frankdevhub.job.webauto.google.drive.ftp.adapter.service.FtpGdriveSynchService;


/**
 * <p>Title:GFtpServerFactory.java</p>  
 * <p>Description: </p>  
 * <p>Copyright: Copyright (c) 2019</p>  
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>  
 * @author frankdevhub   
 * @date:2019-04-23 17:14
 */

public class GFtpServerFactory extends FtpServerFactory {

	private static final Log LOGGER = LogFactory.getLog(GFtpServerFactory.class);
	private static final String DEFAULT_ILLEGAL_CHARS_REGEX = "\\/|[\\x00-\\x1F\\x7F]|\\`|\\?|\\*|\\\\|\\<|\\>|\\||\\\"|\\:";
	private final Controller controller;
	private final Cache model;
	private final Properties configuration;
	private final Pattern illegalChars;
	private final FtpGdriveSynchService cacheUpdater;

	public GFtpServerFactory(Controller controller, Cache model, Properties configuration,
			FtpGdriveSynchService cacheUpdater) {
		super();
		this.controller = controller;
		this.model = model;
		this.cacheUpdater = cacheUpdater;
		this.configuration = configuration;
		this.illegalChars = Pattern
				.compile(configuration.getProperty("os.illegalCharacters", DEFAULT_ILLEGAL_CHARS_REGEX));
		LOGGER.info("Configured illegalchars '" + illegalChars + "'");
		init();
	}

	private void init() {
		setFileSystem(new FtpFileSystemView(controller, model, illegalChars, null, cacheUpdater));
		ConnectionConfigFactory connectionConfigFactory = new ConnectionConfigFactory();
		connectionConfigFactory.setMaxThreads(10);
		connectionConfigFactory.setAnonymousLoginEnabled(
				Boolean.valueOf(this.configuration.getProperty("ftp.anonymous.enabled", "false")));
		setConnectionConfig(connectionConfigFactory.createConnectionConfig());
		setUserManager(new FtpUserManagerFactory(configuration).createUserManager());

		CommandFactoryFactory ccf = new CommandFactoryFactory();
		ccf.addCommand("MFMT", new FtpCommands.MFMT());
		setCommandFactory(ccf.createCommandFactory());

		Map<String, Ftplet> ftplets = new HashMap<>();
		ftplets.put("default", new FtpletController());
		setFtplets(ftplets);

		int port = Integer.parseInt(configuration.getProperty("port", String.valueOf(1821)));
		String serverAddress = configuration.getProperty("server", "");
		LOGGER.info("FTP server configured at '" + serverAddress + ":" + port + "'");
		ListenerFactory listenerFactory = new ListenerFactory();
		listenerFactory.setPort(port);
		if (!serverAddress.isEmpty()) {
			listenerFactory.setServerAddress(serverAddress);
		}

		addListener("default", listenerFactory.createListener());
	}

}
