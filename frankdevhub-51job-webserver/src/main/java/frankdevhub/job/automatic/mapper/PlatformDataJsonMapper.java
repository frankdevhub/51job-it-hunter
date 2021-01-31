package frankdevhub.job.automatic.mapper;

import frankdevhub.job.automatic.dto.PlatformDataJsonQuery;
import frankdevhub.job.automatic.entities.PlatformDataJson;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@SuppressWarnings("all")
public interface PlatformDataJsonMapper {

    /**
     * 根据主键删除对象
     *
     * @param id 主键id
     * @return 更新行数
     */
    int deleteByPrimaryKey(String id);

    int insert(PlatformDataJson dataJson);

    int insertSelective(PlatformDataJson dataJson);

    int updateByPrimaryKeySelective(PlatformDataJson dataJson);

    int updateByPrimaryKey(PlatformDataJson dataJson);

    PlatformDataJson selectById(String id);

    List<PlatformDataJson> findPageWithResult(PlatformDataJsonQuery query);

    Integer findPageWithCount(PlatformDataJsonQuery query);
}
