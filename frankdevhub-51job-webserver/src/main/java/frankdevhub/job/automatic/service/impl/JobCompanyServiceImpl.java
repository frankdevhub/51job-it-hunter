package frankdevhub.job.automatic.service.impl;

import frankdevhub.job.automatic.core.repository.MyBatisRepository;
import frankdevhub.job.automatic.dto.JobCompanyQuery;
import frankdevhub.job.automatic.entities.business.JobCompany;
import frankdevhub.job.automatic.mapper.JobCompanyMapper;
import frankdevhub.job.automatic.service.JobCompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.Assert;

import java.util.List;

/**
 * @Title: JobCompanyServiceImpl
 * @Description: 平台企业信息服务
 * @date: 2021/2/4 0:01
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */

@Slf4j
@Service
@SuppressWarnings("all")
public class JobCompanyServiceImpl extends MyBatisRepository implements JobCompanyService {

    @Autowired
    private JobCompanyMapper jobCompanyMapper;

    /**
     * 根据主键删除对象
     *
     * @param id 主键id
     * @return 更新行数
     */
    @Override
    public int deleteByPrimaryKey(String id) {
        return jobCompanyMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增实体对象
     *
     * @param company 实体对象
     * @return 更新行数
     */
    @Override
    public int insert(JobCompany company) {
        Assert.notNull(company, "cannot find company");

        return jobCompanyMapper.insert(company);
    }

    /**
     * 新增实体对象
     *
     * @param company 实体对象
     * @return 更新行数
     */
    @Override
    public int insertSelective(JobCompany company) {
        Assert.notNull(company, "cannot find company");
        Integer unionId = company.getUnionId();
        //校验jobid唯一识别是否非空
        Assert.notNull(unionId, "cannot find property unionId");

        return jobCompanyMapper.insertSelective(company);
    }

    /**
     * 更新实体对象
     *
     * @param company 实体对象
     * @return 更新行数
     */
    @Override
    public int updateByPrimaryKeySelective(JobCompany company) {
        Assert.notNull(company, "cannot find company");
        Integer unionId = company.getUnionId();
        //校验jobid唯一识别是否非空
        Assert.notNull(unionId, "cannot find property unionId");

        return jobCompanyMapper.updateByPrimaryKeySelective(company);
    }

    /**
     * 更新实体对象
     *
     * @param company 实体对象
     * @return 更新行数
     */
    @Override
    public int updateByPrimaryKey(JobCompany company) {
        Assert.notNull(company, "cannot find company");
        Integer unionId = company.getUnionId();
        //校验jobid唯一识别是否非空
        Assert.notNull(unionId, "cannot find property unionId");

        return jobCompanyMapper.updateByPrimaryKey(company);
    }

    /**
     * 依据主键id查询实体对象
     *
     * @param id 主键id
     * @return 实体对象
     */
    @Override
    public JobCompany selectById(String id) {
        return jobCompanyMapper.selectById(id);
    }

    /**
     * 条件查询
     *
     * @param query    查询实体
     * @param pageNum  分页页数
     * @param pageSize 分页每页大小
     * @return 满足条件的实体集合
     */
    @Override
    public List<JobCompany> findPageWithResult(JobCompanyQuery query, int pageNum, int pageSize) {
        Assert.notNull(query, "cannot find query");
        setPage(pageNum, pageSize); //分页查询
        return jobCompanyMapper.findPageWithResult(query);
    }

    /**
     * 条件查询
     *
     * @param query 查询实体
     * @return 满足条件的实体数量
     */
    @Override
    public int findPageWithCount(JobCompanyQuery query) {
        Assert.notNull(query, "cannot find query");
        return jobCompanyMapper.findPageWithCount(query);
    }

    /**
     * 依据唯一识别号查询实体对象的数量
     *
     * @param unionId 唯一识别号
     * @return 实体对象
     */
    @Override
    public JobCompany selectByUnionId(Integer unionId) {
        return jobCompanyMapper.selectByUnionId(unionId);
    }

    /**
     * 依据唯一识别号查询实体对象的数量
     *
     * @param unionId
     * @return 满足条件的实体数量
     */
    @Override
    public int selectCountByUnionId(Integer unionId) {
        return jobCompanyMapper.selectCountByUnionId(unionId);
    }
}
