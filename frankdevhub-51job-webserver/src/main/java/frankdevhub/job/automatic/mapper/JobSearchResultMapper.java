package frankdevhub.job.automatic.mapper;

import frankdevhub.job.automatic.core.utils.ViewMapper;
import frankdevhub.job.automatic.entities.JobSearchResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JobSearchResultMapper extends ViewMapper<JobSearchResult> {
}
