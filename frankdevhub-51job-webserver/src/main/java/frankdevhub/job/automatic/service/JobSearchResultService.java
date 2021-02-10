package frankdevhub.job.automatic.service;

import frankdevhub.job.automatic.dto.JobSearchResultQuery;
import frankdevhub.job.automatic.entities.business.JobSearchResult;

import java.util.List;

/**
 * @Title: JobSearchResultService
 * @Description: 平台职位搜索返回结果集
 * @date: 2021/1/23 23:07
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */

@SuppressWarnings("all")
public interface JobSearchResultService {

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
     * @param result 实体对象
     * @return 更新行数
     */
    int insert(JobSearchResult result);

    /**
     * 新增实体对象
     *
     * @param result 实体对象
     * @return 更新行数
     */
    int insertSelective(JobSearchResult result);

    /**
     * 更新实体对象
     *
     * @param result 实体对象
     * @return 更新行数
     */
    int updateByPrimaryKeySelective(JobSearchResult result);

    /**
     * 更新实体对象
     *
     * @param result 实体对象
     * @return 更新行数
     */
    int updateByPrimaryKey(JobSearchResult result);

    /**
     * 条件查询
     *
     * @param query 查询实体
     * @return 满足条件的实体数量
     */
    int findPageWithCount(JobSearchResultQuery query);

    /**
     * 依据唯一识别号查询实体对象的数量
     *
     * @param unionId 唯一识别号
     * @return 满足条件的实体数量
     */
    int selectCountByUnionId(Integer unionId);

    /**
     * 依据主键id查询实体对象
     *
     * @param id 主键id
     * @return 实体对象
     */
    JobSearchResult selectById(String id);

    /**
     * 条件查询
     *
     * @param query    查询实体
     * @param pageNum  分页页数
     * @param pageSize 分页每页大小
     * @return 满足条件的实体集合
     */
    List<JobSearchResult> findPageWithResult(JobSearchResultQuery query, int pageNum, int pageSize);

    /**
     * 依据唯一识别号查询实体对象的数量
     *
     * @param markId 唯一识别号
     * @return 实体对象
     */
    JobSearchResult selectByUnionId(Integer markId);

}
