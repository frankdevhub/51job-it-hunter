package frankdevhub.job.automatic.core.parser;

import frankdevhub.job.automatic.entities.PlatformDataJson;
import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.util.Assert;

/**
 * @Title: PlatformDataConverter
 * @Description: 平台json转换为业务实体类
 * @date: 2021/1/31 0:20
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */

@Slf4j
@SuppressWarnings("all")
public class PlatformDataConverter {

    /**
     * 平台json转换为ORM持久化对象
     *
     * @param json 返回的json字符串
     * @return ORM业务对象实体类
     */
    public static PlatformDataJson convert(String json) {
        Assert.notNull(json, "cannot find json");
        PlatformDataJson data = new PlatformDataJson();
        return data;
    }
}
