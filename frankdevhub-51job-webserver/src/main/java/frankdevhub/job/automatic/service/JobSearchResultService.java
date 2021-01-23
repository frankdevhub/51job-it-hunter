package frankdevhub.job.automatic.service;

import frankdevhub.job.automatic.dto.JobSearchResultQuery;
import frankdevhub.job.automatic.entities.JobSearchResult;

import java.util.List;

/**
 * @Title: JobSearchResultService
 * @Description: //TODO
 * @date: 2021/1/23 23:07
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */


public interface JobSearchResultService {
    int deleteByPrimaryKey(String id);

    int insert(JobSearchResult result);

    int insertSelective(JobSearchResult result);

    int updateByPrimaryKeySelective(JobSearchResult result);

    int updateByPrimaryKey(JobSearchResult result);

    int findPageWithCount(JobSearchResultQuery query);

    int selectCountByMarkId(Integer markId);

    JobSearchResult selectById(String id);

    List<JobSearchResult> findPageWithResult(JobSearchResultQuery query);

    JobSearchResult selectByMarkId(Integer markId);

}
