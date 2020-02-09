package frankdevhub.job.automatic.repository;

import frankdevhub.job.automatic.core.repository.MyBatisRepository;
import frankdevhub.job.automatic.core.utils.SpringUtils;
import frankdevhub.job.automatic.entities.WebDriverResource;
import frankdevhub.job.automatic.mapper.WebDriverResourceMapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

/**
 * <p>Title:@ClassName WebDriverResourceRepository.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/10 4:30
 * @Version: 1.0
 */

@Repository
public class WebDriverResourceRepository extends MyBatisRepository {

    private static final String ID = "id";
    private static final String RES_ID = "resourceId";

    private WebDriverResourceMapper getMapper() {
        return SpringUtils.getBean(WebDriverResourceMapper.class);
    }

    public Integer clear() {
        Example example = new Example(WebDriverResource.class);
        return getMapper().deleteByExample(example);
    }

    public Integer insert(WebDriverResource record) {
        record.doCreateEntity();
        return getMapper().insert(record);
    }

    public Integer insertSelective(WebDriverResource record) {
        record.doCreateEntity();
        return getMapper().insertSelective(record);
    }
}
