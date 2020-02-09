package frankdevhub.job.automatic.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>Title:@ClassName WebDriverResource.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/10 4:08
 * @Version: 1.0
 */

@Table(name = "web_driver_resource_index")
public class WebDriverResource extends BaseRecord<WebDriverResource> {

    private static final String MIRROR_INDEX = "http://npm.taobao.org/mirrors/chromedriver/";
    private static final String TYPE_LINUX_32 = "linux32";
    private static final String TYPE_LINUX_64 = "linux64";
    private static final String TYPE_MAC_32 = "mac32";
    private static final String TYPE_MAC_64 = "mac64";
    private static final String TYPE_WIN_32 = "win32";
    private static final String TYPE_WIN_64 = "win64";

    @Id
    private Long id;

    @Column(name = "resource_id")
    private Long resourceId;

    @Column(name = "version_text")
    private String version;

    @Column(name = "index_release_note")
    private String indexReleaseNote;

    @Column(name = "source_file_name")
    private String sourceFileName;

    @Column(name = "source_release_note")
    private String sourceReleaseNote;

    @Column(name = "os_type")
    private String osType;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "source_file_size_info")
    private String sourceFileSizeInfo;

    @Column(name = "link_url")
    private String linkUrl;

    public Long getId() {
        return id;
    }

    public WebDriverResource setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public WebDriverResource setResourceId(Long resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public WebDriverResource setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getIndexReleaseNote() {
        return indexReleaseNote;
    }

    public WebDriverResource setIndexReleaseNote(String indexReleaseNote) {
        this.indexReleaseNote = indexReleaseNote;
        return this;
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    public WebDriverResource setSourceFileName(String sourceFileName) {
        this.sourceFileName = sourceFileName;
        return this;
    }

    public String getSourceReleaseNote() {
        return sourceReleaseNote;
    }

    public WebDriverResource setSourceReleaseNote(String sourceReleaseNote) {
        this.sourceReleaseNote = sourceReleaseNote;
        return this;
    }

    public String getOsType() {
        return osType;
    }

    public WebDriverResource setOsType(String osType) {
        this.osType = osType;
        return this;
    }

    public String getFileType() {
        return fileType;
    }

    public WebDriverResource setFileType(String fileType) {
        this.fileType = fileType;
        return this;
    }

    public String getSourceFileSizeInfo() {
        return sourceFileSizeInfo;
    }

    public WebDriverResource setSourceFileSizeInfo(String sourceFileSizeInfo) {
        this.sourceFileSizeInfo = sourceFileSizeInfo;
        return this;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public WebDriverResource setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        return this;
    }
}
