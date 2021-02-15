package frankdevhub.job.automatic.mapper;

import frankdevhub.job.automatic.dto.JobSearchResultQuery;
import frankdevhub.job.automatic.entities.business.JobSearchResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@SuppressWarnings("all")
public interface JobSearchResultMapper {

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
     * 依据主键id查询实体对象
     *
     * @param id 主键id
     * @return 实体对象
     */
    JobSearchResult selectById(String id);

    /**
     * 条件查询
     *
     * @param query 查询实体
     * @return 满足条件的实体集合
     */
    List<JobSearchResult> findPageWithResult(JobSearchResultQuery query);

    /**
     * 依据唯一识别号查询实体对象的数量
     *
     * @param unionId 唯一识别号
     * @return 实体对象
     */
    JobSearchResult selectByUnionId(Integer unionId);


    /**
     * 依据唯一识别号查询实体对象的数量
     *
     * @param unionId 唯一识别号
     * @return 满足条件的实体数量
     */
    int selectCountByUnionId(Integer unionId);

}