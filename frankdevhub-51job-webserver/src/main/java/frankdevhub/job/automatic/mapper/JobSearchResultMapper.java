package frankdevhub.job.automatic.mapper;

import frankdevhub.job.automatic.dto.JobSearchResultQuery;
import frankdevhub.job.automatic.entities.JobSearchResult;

import java.util.List;

public interface JobSearchResultMapper {
    int deleteByPrimaryKey(String id);

    int insert(JobSearchResult result);

    int insertSelective(JobSearchResult result);

    int updateByPrimaryKeySelective(JobSearchResult result);

    int updateByPrimaryKey(JobSearchResult result);

    int findPageWithCount(JobSearchResultQuery query);

    int selectCountByMarkId(String markId);

    JobSearchResult selectById(String id);

    List<JobSearchResult> findPageWithResult(JobSearchResultQuery query);

    JobSearchResult selectByMarkId(String markId);
}