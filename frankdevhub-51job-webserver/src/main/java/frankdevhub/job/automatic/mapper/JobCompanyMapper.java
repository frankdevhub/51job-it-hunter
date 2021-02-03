package frankdevhub.job.automatic.mapper;

import frankdevhub.job.automatic.dto.JobCompanyQuery;
import frankdevhub.job.automatic.entities.JobCompany;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@SuppressWarnings("all")
public interface JobCompanyMapper {

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
     * @param company 实体对象
     * @return 更新行数
     */
    int insert(JobCompany company);

    /**
     * 新增实体对象
     *
     * @param company 实体对象
     * @return 更新行数
     */
    int insertSelective(JobCompany company);

    /**
     * 更新实体对象
     *
     * @param company 实体对象
     * @return 更新行数
     */
    int updateByPrimaryKeySelective(JobCompany company);

    /**
     * 更新实体对象
     *
     * @param company 实体对象
     * @return 更新行数
     */
    int updateByPrimaryKey(JobCompany company);

    /**
     * 依据主键id查询实体对象
     *
     * @param id 主键id
     * @return 实体对象
     */
    JobCompany selectById(String id);

    /**
     * 条件查询
     *
     * @param query 查询实体
     * @return 满足条件的实体集合
     */
    List<JobCompany> findPageWithResult(JobCompanyQuery query);

    /**
     * 条件查询
     *
     * @param query 查询实体
     * @return 满足条件的实体数量
     */
    int findPageWithCount(JobCompanyQuery query);
}