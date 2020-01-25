package frankdevhub.job.automatic.google.drive.ftp.adapter.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Function;

/**
 * <p>Title:CallbackOutputStream.java</p>  
 * <p>Description: </p>  
 * <p>Copyright: Copyright (c) 2019</p>  
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>  
 * @author frankdevhub   
 * @date:2019-04-23 17:18
 */

public class CallbackOutputStream extends OutputStream {

	private final OutputStream os;
	private final Function<Void, Exception> closeCallback;

	public CallbackOutputStream(OutputStream os, Function closeCallback) {
		this.os = os;
		this.closeCallback = closeCallback;
	}

	@Override
	public void write(int b) throws IOException {
		os.write(b);
	}

	@Override
	public void write(byte[] b) throws IOException {
		os.write(b);
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		os.write(b, off, len);
	}

	@Override
	public void flush() throws IOException {
		os.flush();
	}

	@Override
	public void close() throws IOException {
		os.close();
		Exception apply = closeCallback.apply(null);
		if (apply != null) {
			throw new IOException(apply);
		}
	}
}
