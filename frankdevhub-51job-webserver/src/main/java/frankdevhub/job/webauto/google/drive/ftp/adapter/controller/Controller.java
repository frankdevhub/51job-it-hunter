package frankdevhub.job.webauto.google.drive.ftp.adapter.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import frankdevhub.job.webauto.google.drive.ftp.adapter.model.GoogleDrive;
import frankdevhub.job.webauto.google.drive.ftp.adapter.service.FtpGdriveSynchService;


/**
 * <p>Title:Controller.java</p>  
 * <p>Description: </p>  
 * <p>Copyright: Copyright (c) 2019</p>  
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>  
 * @author frankdevhub   
 * @date:2019-04-23 16:28
 */

public class Controller {
	  private static final Log LOGGER = LogFactory.getLog(Controller.class);
	    private final GoogleDrive googleDrive;
	    private final FtpGdriveSynchService updaterService;
	    private final Cache cache;
	   
	    private final Map<String, ControllerRequest> lastQueries = new LRUCache<>(10);

	    public Controller(Cache cache, GoogleDrive googleDrive, FtpGdriveSynchService updaterService) {
	        this.googleDrive = googleDrive;
	        this.updaterService = updaterService;
	        this.cache = cache;
	    }

	    public List<GFile> getFiles(String folderId) {

	        forceFolderUpdate(folderId);

	        return cache.getFiles(folderId);
	    }


	    private void forceFolderUpdate(String folderId) {
	        // patch
	        ControllerRequest lastAction = lastQueries.get("getFiles-" + folderId);
	        if (lastAction == null) {
	            lastAction = new ControllerRequest(new Date(), 0);
	            lastQueries.put("getFiles-" + folderId, lastAction);
	        }
	        lastAction.times++;

	        if (lastAction.times > 2) {
	            if (System.currentTimeMillis() < (lastAction.date.getTime() + 10000)) {
	            	LOGGER.info("Forcing update for folder '" + folderId + "'");
	                updaterService.updateFolderNow(folderId);
	            }
	            lastAction.times = 0;
	            lastAction.date = new Date();
	        }
	       
	    }

	    public boolean renameFile(GFile gFile, String newName) {
	    	LOGGER.info("Renaming file " + gFile.getId());
	        return touch(gFile, new GFile(newName));
	    }

	    public boolean updateLastModified(GFile gFile, long time) {
	    	LOGGER.info("Updating last modified date for " + gFile.getId() + " to " + new Date(time));
	        GFile patch = new GFile(null);
	        patch.setLastModified(time);
	        return touch(gFile, patch);
	    }

	    private boolean touch(GFile ftpFile, GFile patch) {
	    	LOGGER.info("Patching file... " + ftpFile.getId());
	        if (patch.getName() == null && patch.getLastModified() <= 0) {
	            throw new IllegalArgumentException("Patching doesn't contain valid name nor modified date");
	        }
	        GFile googleFileUpdated = googleDrive.patchFile(ftpFile.getId(), patch.getName(), patch.getLastModified());
	        if (googleFileUpdated != null) {
	            googleFileUpdated.setRevision(cache.getRevision());
	            return cache.addOrUpdateFile(googleFileUpdated) > 0;
	        }
	        return false;
	    }

	    public boolean trashFile(GFile file) {
	        String fileId = file.getId();
	        LOGGER.info("Trashing file " + file.getId() + "...");
	        if (googleDrive.trashFile(fileId).getTrashed()) {
	            cache.deleteFile(fileId);
	            return true;
	        }
	        return false;
	    }

	    public boolean mkdir(String parentFileId, GFile gFile) {
	    	LOGGER.info("Creating directory " + gFile.getId() + "...");
	        GFile newDir = googleDrive.mkdir(parentFileId, gFile.getName());
	        cache.addOrUpdateFile(newDir);
	        return true;
	    }
	    
	    public InputStream createInputStream(GFile gFile) {
	    	LOGGER.info("Downloading file " + gFile.getId() + "...");
	    	
	        return new CallbackInputStream(googleDrive.downloadFile(gFile), (na) -> {
	        	LOGGER.info("Input stream closed");
	            return null;
	        });
	    }

	    public OutputStream createOutputStream(final GFile gFile) {
	        if (gFile.isDirectory()) {
	            throw new IllegalArgumentException("Error. Can't upload files of type directory");
	        }

	        
	        if (gFile.getParents() == null) {
	            gFile.setParents(cache.getParents(gFile.getId()));
	        }

	        
	        final GoogleDrive.OutputStreamRequest outputStreamRequest = googleDrive.getOutputStream(gFile);

	        
	        final Future<GFile> fileUploadFuture = outputStreamRequest.getFutureGFile()
	                .thenApply(uploadedFile -> {
	                	LOGGER.info("File uploaded. Updating local cache...");
	                    uploadedFile.setRevision(cache.getRevision());
	                    if (cache.addOrUpdateFile(uploadedFile) <= 0) {
	                        throw new RuntimeException("Error synchronizing file to cache");
	                    }
	                    return uploadedFile;
	                });


	    
	        return new CallbackOutputStream(outputStreamRequest.getOutputStream(), (na) -> {
	            try {
	                fileUploadFuture.get(10, TimeUnit.SECONDS);
	                return null;
	            } catch (Exception e) {
	            	LOGGER.error("Error waiting for upload to complete", e);
	                return e;
	            }
	        });
	    }

	    private static class LRUCache<K, V> extends LinkedHashMap<K, V> {

	        private static final long serialVersionUID = 5705764796697720184L;

	        private int size;

	        private LRUCache(int size) {
	            super(size, 0.75f, true);
	            this.size = size;
	        }

	        @Override
	        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
	            return size() > size;
	        }
	    }

	    private static class ControllerRequest {
	        private Date date;
	        private int times;

	        private ControllerRequest(Date date, int times) {
	            this.date = date;
	            this.times = times;
	        }
	    }
}
