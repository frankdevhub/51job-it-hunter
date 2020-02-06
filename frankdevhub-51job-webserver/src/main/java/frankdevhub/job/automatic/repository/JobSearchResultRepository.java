package frankdevhub.job.automatic.repository;

import frankdevhub.job.automatic.core.repository.MyBatisRepository;
import frankdevhub.job.automatic.core.utils.SpringUtils;
import frankdevhub.job.automatic.entities.JobSearchResult;
import frankdevhub.job.automatic.mapper.JobSearchResultMapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

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
public class JobSearchResultRepository extends MyBatisRepository {

    private final String ID = "id";
    private final String MARK_ID = "markId";
    private final String CREATE_TIME = "createTime";

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

    public Integer selectCountByMarkId(JobSearchResult record) {
        Example example = new Example(JobSearchResult.class);
        example.createCriteria().andEqualTo(MARK_ID, record.getMarkId());
        return getMapper().selectCountByExample(example);
    }
}