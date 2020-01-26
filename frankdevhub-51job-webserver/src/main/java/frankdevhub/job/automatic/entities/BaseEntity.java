package frankdevhub.job.automatic.entities;

import javax.persistence.Column;
import java.util.Date;

/**
 * <p>Title:@ClassName BaseEntity.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/1/26 22:43
 * @Version: 1.0
 */
public class BaseEntity<T> {

    @Column(name = "create_time")
    private Long createTime;

    @Column(name = "update_time")
    private Long updateTime;

    public Long getCreateTime() {
        return createTime;
    }

    private BaseEntity setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    private BaseEntity setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    protected T doCreateEntity() {
        Long timeStamp = new Date().getTime();
        this.setCreateTime(timeStamp).setUpdateTime(timeStamp);
        return (T) this;
    }

    protected T doUpdateEntity() {
        this.setUpdateTime(new Date().getTime());
        return (T) this;
    }
}
