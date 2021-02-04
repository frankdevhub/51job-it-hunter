package frankdevhub.job.automatic.service;

import frankdevhub.job.automatic.dto.JobCompanyQuery;
import frankdevhub.job.automatic.entities.JobCompany;

import java.util.List;

/**
 * @Title: JobCompanyService
 * @Description: 企业信息服务
 * @date: 2021/2/3 23:59
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */

@SuppressWarnings("all")
public interface JobCompanyService {

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
     * @param query    查询实体
     * @param pageNum  分页页数
     * @param pageSize 分页每页大小
     * @return 满足条件的实体集合
     */
    List<JobCompany> findPageWithResult(JobCompanyQuery query, int pageNum, int pageSize);

    /**
     * 条件查询
     *
     * @param query 查询实体
     * @return 满足条件的实体数量
     */
    int findPageWithCount(JobCompanyQuery query);

    /**
     * 依据唯一识别号查询实体对象的数量
     *
     * @param unionId 唯一识别号
     * @return 实体对象
     */
    JobCompany selectByUnionId(Integer unionId);


    /**
     * 依据唯一识别号查询实体对象的数量
     *
     * @param markId 唯一识别号
     * @return 满足条件的实体数量
     */
    int selectCountByUnionId(Integer unionId);


}
