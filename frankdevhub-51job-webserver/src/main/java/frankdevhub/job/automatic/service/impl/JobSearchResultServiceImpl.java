package frankdevhub.job.automatic.service.impl;

import frankdevhub.job.automatic.dto.JobSearchResultQuery;
import frankdevhub.job.automatic.entities.JobSearchResult;
import frankdevhub.job.automatic.mapper.JobSearchResultMapper;
import frankdevhub.job.automatic.service.JobSearchResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: JobSearchResultServiceImpl
 * @Description: //TODO
 * @date: 2021/1/23 23:07
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */

@Slf4j
@Service
@SuppressWarnings("all")
public class JobSearchResultServiceImpl implements JobSearchResultService {

    @Autowired
    private JobSearchResultMapper searchResultMapper;


    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(JobSearchResult result) {
        return 0;
    }

    @Override
    public int insertSelective(JobSearchResult result) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(JobSearchResult result) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(JobSearchResult result) {
        return 0;
    }

    @Override
    public JobSearchResult selectById(String id) {
        return null;
    }

    @Override
    public List<JobSearchResult> findPageWithResult(JobSearchResultQuery query) {
        return null;
    }

    @Override
    public int findPageWithCount(JobSearchResultQuery query) {
        return 0;
    }

    @Override
    public int selectCountByMarkId(Integer markId) {
        return 0;
    }

    @Override
    public JobSearchResult selectByMarkId(Integer markId) {
        return null;
    }
}
