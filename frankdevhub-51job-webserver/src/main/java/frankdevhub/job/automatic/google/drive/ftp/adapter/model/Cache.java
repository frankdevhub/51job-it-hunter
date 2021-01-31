package frankdevhub.job.automatic.google.drive.ftp.adapter.model;

import org.springframework.dao.IncorrectResultSizeDataAccessException;

import java.util.List;
import java.util.Set;

/**
 * <p>Title:Cache.java</p>  
 * <p>Description: </p>  
 * <p>Copyright: Copyright (c) 2019</p>  
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>  
 * @author frankdevhub   
 * @date:2019-04-23 16:31
 */

@SuppressWarnings("all")
public interface Cache {
	GFile getFile(String id);

	List<GFile> getFiles(String folderId);

	GFile getFileByName(String parentId, String filename) throws IncorrectResultSizeDataAccessException;

	int addOrUpdateFile(GFile rootFile);

	int deleteFile(String id);

	String getRevision();

	void updateRevision(String revision);

	List<String> getAllFoldersWithoutRevision();

	void updateChilds(GFile file, List<GFile> newChilds);

	Set<String> getParents(String id);
}
