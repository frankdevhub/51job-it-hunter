package frankdevhub.job.automatic.repository;

import frankdevhub.job.automatic.core.utils.SpringUtils;
import frankdevhub.job.automatic.entities.JobSearchResult;
import frankdevhub.job.automatic.mapper.JobSearchResultMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>Title:@ClassName JobSearchResultRepository.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/1/26 23:19
 * @Version: 1.0
 */

@Repository
public class JobSearchResultRepository {

    private final String ID = "id";

    private JobSearchResultMapper getMapper() {
        return SpringUtils.getBean(JobSearchResultMapper.class);
    }

    public Integer insert(JobSearchResult record) {
        record.doCreateEntity();
        return getMapper().insert(record);
    }

    public Integer insertSelective(JobSearchResult record) {
        record.doCreateEntity();
        return getMapper().insertSelective(record);
    }
}