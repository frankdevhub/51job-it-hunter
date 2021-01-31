package frankdevhub.job.automatic.entities;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * <p>Title:@ClassName BaseRecord.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/1/26 22:43
 * @Version: 1.0
 */

@Data
@SuppressWarnings("all")
public class BaseRecord<T> {

    /**
     * 主键id编号
     */
    private String id;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;


    /**
     * @param createTime 创建时间(时间戳毫秒)
     */
    private BaseRecord<T> setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    /**
     * @param updateTime 更新时间(时间戳毫秒)
     */
    private BaseRecord<T> setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @SuppressWarnings("unchecked")
    public T doCreateEntity() {
        Long timeStamp = new Date().getTime();
        this.setId(UUID.randomUUID().toString());
        this.setCreateTime(timeStamp).setUpdateTime(timeStamp);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T doUpdateEntity() {
        this.setUpdateTime(new Date().getTime());
        return (T) this;
    }
}
