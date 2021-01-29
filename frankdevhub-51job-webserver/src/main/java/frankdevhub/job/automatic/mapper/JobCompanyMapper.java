package frankdevhub.job.automatic.mapper;

import frankdevhub.job.automatic.dto.JobCompanyQuery;
import frankdevhub.job.automatic.entities.JobCompany;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JobCompanyMapper {
    int deleteByPrimaryKey(String id);

    int insert(JobCompany company);

    int insertSelective(JobCompany company);

    int updateByPrimaryKeySelective(JobCompany company);

    int updateByPrimaryKey(JobCompany company);

    JobCompany selectById(String id);

    List<JobCompany> findPageWithResult(JobCompanyQuery query);

    Integer findPageWithCount(JobCompanyQuery query);
}