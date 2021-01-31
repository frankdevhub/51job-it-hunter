package frankdevhub.job.automatic.mapper;

import frankdevhub.job.automatic.dto.JobSearchResultQuery;
import frankdevhub.job.automatic.entities.JobSearchResult;
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