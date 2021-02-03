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

    int insert(JobCompany company);

    int insertSelective(JobCompany company);

    int updateByPrimaryKeySelective(JobCompany company);

    int updateByPrimaryKey(JobCompany company);

    JobCompany selectById(String id);

    List<JobCompany> findPageWithResult(JobCompanyQuery query);

    Integer findPageWithCount(JobCompanyQuery query);
}