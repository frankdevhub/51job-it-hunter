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

    /**
     * 新增实体对象
     *
     * @param dataJson 实体对象
     * @return 更新行数
     */
    int insert(PlatformDataJson dataJson);

    /**
     * 新增实体对象
     *
     * @param dataJson 实体对象
     * @return 更新行数
     */
    int insertSelective(PlatformDataJson dataJson);

    /**
     * 更新实体对象
     *
     * @param dataJson 实体对象
     * @return 更新行数
     */
    int updateByPrimaryKeySelective(PlatformDataJson dataJson);

    /**
     * 更新实体对象
     *
     * @param dataJson 实体对象
     * @return 更新行数
     */
    int updateByPrimaryKey(PlatformDataJson dataJson);

    /**
     * 依据主键id查询实体对象
     *
     * @param id 主键id
     * @return 实体对象
     */
    PlatformDataJson selectById(String id);

    /**
     * 条件查询
     *
     * @param query 查询实体
     * @return 满足条件的实体集合
     */
    List<PlatformDataJson> findPageWithResult(PlatformDataJsonQuery query);

    /**
     * 条件查询
     *
     * @param query 查询实体
     * @return 满足条件的实体数量
     */
    int findPageWithCount(PlatformDataJsonQuery query);

    /**
     * 依据主键jobid查询实体对象
     *
     * @param jobId 唯一标识jobid
     * @return 实体对象
     */
    PlatformDataJson selectByJobId(String jobId);
}
