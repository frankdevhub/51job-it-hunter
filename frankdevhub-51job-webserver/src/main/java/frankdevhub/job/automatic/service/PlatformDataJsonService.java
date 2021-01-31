package frankdevhub.job.automatic.service;

import frankdevhub.job.automatic.dto.PlatformDataJsonQuery;
import frankdevhub.job.automatic.entities.PlatformDataJson;

import java.util.List;

/**
 * @Title: PlatformDataJsonService
 * @Description: //TODO
 * @date: 2021/1/31 0:07
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */


@SuppressWarnings("all")
public interface PlatformDataJsonService {

    /**
     * 根据主键删除对象
     *
     * @param id 主键id
     * @return 更新行数
     */
    int deleteByPrimaryKey(String id);

    int insert(PlatformDataJson dataJson);

    int insertSelective(PlatformDataJson dataJson);

    int updateByPrimaryKeySelective(PlatformDataJson dataJson);

    int updateByPrimaryKey(PlatformDataJson dataJson);

    PlatformDataJson selectById(String id);

    List<PlatformDataJson> findPageWithResult(PlatformDataJsonQuery query);

    Integer findPageWithCount(PlatformDataJsonQuery query);
}
