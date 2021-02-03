package frankdevhub.job.automatic.google.drive.ftp.adapter.model;

/**
 * <p>Title:GChange.java</p>  
 * <p>Description: </p>  
 * <p>Copyright: Copyright (c) 2019</p>  
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>  
 * @author frankdevhub   
 * @date:2019-04-23 16:32
 */

@SuppressWarnings("all")
public class GChange {
	private final String fileId;
	private final boolean removed;
	private final GFile file;
	private final String revision;

	GChange(String revision, String fileId, boolean removed, GFile file) {
		this.revision = revision;
		this.fileId = fileId;
		this.removed = removed;
		this.file = file;
	}

	public String getFileId() {
		return fileId;
	}

	public GFile getFile() {
		return file;
	}

	public boolean isRemoved() {
		return removed;
	}

	public String getRevision() {
		return revision;
	}
}
