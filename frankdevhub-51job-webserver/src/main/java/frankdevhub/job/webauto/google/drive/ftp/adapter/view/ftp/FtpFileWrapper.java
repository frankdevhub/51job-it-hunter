package frankdevhub.job.webauto.google.drive.ftp.adapter.view.ftp;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ftpserver.ftplet.FtpFile;

import nyoibo.inkstone.upload.google.drive.ftp.adapter.controller.Controller;
import nyoibo.inkstone.upload.google.drive.ftp.adapter.model.GFile;

/**
 * <p>Title:FtpFileWrapper.java</p>  
 * <p>Description: </p>  
 * <p>Copyright: Copyright (c) 2019</p>  
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>  
 * @author frankdevhub   
 * @date:2019-04-23 17:07
 */

class FtpFileWrapper implements FtpFile {

	private static final Log LOG = LogFactory.getLog(FtpFileWrapper.class);

	private final Controller controller;

	private final FtpFileSystemView view;

	private final FtpFileWrapper parent;

	private final GFile gfile;

	private String virtualName;

	FtpFileWrapper(FtpFileSystemView view, Controller controller, FtpFileWrapper parent, GFile ftpGFile,
			String virtualName) {
		this.view = view;
		this.controller = controller;
		this.parent = parent;
		this.gfile = ftpGFile;
		this.virtualName = virtualName;
	}

	public String getId() {
		return gfile.getId();
	}

	@Override
	public String getAbsolutePath() {
		return isRoot() ? virtualName
				: parent.isRoot() ? FtpFileSystemView.FILE_SEPARATOR + virtualName
						: parent.getAbsolutePath() + FtpFileSystemView.FILE_SEPARATOR + virtualName;
	}

	@Override
	public boolean isHidden() {
		return false;
	}

	@Override
	public boolean isFile() {
		return !isDirectory();
	}

	@Override
	public boolean doesExist() {
		return gfile.isExists();
	}

	@Override
	public boolean isReadable() {
		return true;
	}

	@Override
	public boolean isWritable() {
		return true;
	}

	@Override
	public boolean isRemovable() {
		return gfile.isRemovable();
	}

	@Override
	public String getOwnerName() {
		return gfile.getOwnerName();
	}

	@Override
	public String getGroupName() {
		return "no_group";
	}

	@Override
	public int getLinkCount() {
		return gfile.getParents() != null ? gfile.getParents().size() : 0;
	}

	@Override
	public long getSize() {
		return gfile.getSize();
	}

	@Override
	public Object getPhysicalFile() {
		return null;
	}

	@Override
	public boolean delete() {
		if (!doesExist()) {
			LOG.info("File '" + getName() + "' doesn't exists");
			return false;
		}
		return controller.trashFile(this.unwrap());
	}

	@Override
	public long getLastModified() {
		return gfile.getLastModified();
	}

	@Override
	public String getName() {
		return virtualName;
	}

	@Override
	public boolean isDirectory() {
		return gfile.isDirectory();
	}

	private GFile unwrap() {
		return gfile;
	}

	@Override
	public boolean move(FtpFile destination) {
		return controller.renameFile(this.unwrap(), destination.getName());
	}

	@Override
	public OutputStream createOutputStream(long offset) {
		return controller.createOutputStream(this.unwrap());
	}

	@Override
	public InputStream createInputStream(long offset) {
		return controller.createInputStream(this.unwrap());
	}

	@Override
	public boolean mkdir() {
		if (isRoot()) {
			throw new IllegalArgumentException("Cannot create root folder");
		}
		return controller.mkdir(parent.getId(), this.unwrap());
	}

	@Override
	public boolean setLastModified(long arg0) {
		return controller.updateLastModified(this.unwrap(), arg0);
	}

	@Override
	public List<FtpFile> listFiles() {
		return view.listFiles(this);
	}

	@Override
	public String toString() {
		return "FtpFileWrapper [absolutePath=" + getAbsolutePath() + "]";
	}

	boolean isRoot() {
		return parent == null;
	}

	FtpFileWrapper getParentFile() {
		return parent;
	}

	void setVirtualName(String virtualName) {
		this.virtualName = virtualName;
	}
}
