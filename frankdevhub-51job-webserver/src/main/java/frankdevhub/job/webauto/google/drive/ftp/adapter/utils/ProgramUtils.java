package frankdevhub.job.webauto.google.drive.ftp.adapter.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>Title:ProgramUtils.java</p>  
 * <p>Description: </p>  
 * <p>Copyright: Copyright (c) 2019</p>  
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>  
 * @author frankdevhub   
 * @date:2019-04-23 17:23
 */

public final class ProgramUtils {
	private static final Log LOGGER = LogFactory.getLog(ProgramUtils.class);
	private final File file;

	public ProgramUtils(String executionStatusFilename) {
		super();
		file = new File(executionStatusFilename);
	}

	private static String getLastStatus(File file) {
		try {
			Scanner scanner = new Scanner(file);
			String time = scanner.next();
			scanner.close();
			return time;
		} catch (FileNotFoundException ex) {
			throw new RuntimeException(ex);
		}
	}

	public String lastStatus() {
		if (file.exists()) {
			String ret = getLastStatus(file);
			return ret;
		}
		return null;
	}

	public void deleteStatus() {
		if (file.exists()) {
			file.delete();
		}
	}

	public void setStatus(String text) {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(text.getBytes());

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		}
	}

	public static final class RequestsPerSecondController {

		private final int maxRequestPerSecond;
		private final long period;
		private int requestTimes = 0;
		private long time = 0;

		public RequestsPerSecondController(int maxRequestPerSecond, long period) {
			this.maxRequestPerSecond = maxRequestPerSecond;
			this.period = period;
		}

		public void start() {
			reset();
			LOGGER.info("Started at " + time);
		}

		public void reset() {
			time = System.currentTimeMillis();
			requestTimes = 0;
		}

		public synchronized void newRequest() {
			final long newTime = System.currentTimeMillis();
			final long expiratonTime = time + period;
			requestTimes++;
			if (newTime < expiratonTime) {
				if (requestTimes >= maxRequestPerSecond) {
					LOGGER.info("Reached limit! sleeping " + (expiratonTime - newTime) + " millis,,,");
					try {
						Thread.sleep(expiratonTime - newTime);
					} catch (InterruptedException ex) {
						throw new RuntimeException(ex);
					}
					reset();
				}
			} else { /* if (newTime >= expiratonTime) { */
				requestTimes = 0;
				time = newTime;
			}
		}
	}
}
