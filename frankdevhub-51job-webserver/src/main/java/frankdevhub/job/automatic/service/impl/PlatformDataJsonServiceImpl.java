package frankdevhub.job.automatic.service.impl;

import frankdevhub.job.automatic.core.repository.MyBatisRepository;
import frankdevhub.job.automatic.dto.PlatformDataJsonQuery;
import frankdevhub.job.automatic.entities.PlatformDataJson;
import frankdevhub.job.automatic.mapper.PlatformDataJsonMapper;
import frankdevhub.job.automatic.service.PlatformDataJsonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.Assert;

import java.util.List;
import java.util.UUID;

/**
 * @Title: PlatformDataJsonServiceImpl
 * @Description: //TODO
 * @date: 2021/1/31 0:08
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */

@Slf4j
@Service
@SuppressWarnings("all")
public class PlatformDataJsonServiceImpl extends MyBatisRepository implements PlatformDataJsonService {

    @Autowired
    private PlatformDataJsonMapper dataJsonMapper;

    /**
     * 根据主键删除对象
     *
     * @param id 主键id
     * @return 更新行数
     */
    @Override
    public int deleteByPrimaryKey(String id) {
        return dataJsonMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增实体对象
     *
     * @param dataJson 实体对象
     * @return 更新行数
     */
    @Override
    public int insert(PlatformDataJson dataJson) {
        Assert.notNull(dataJson, "cannot find dataJson");
        return dataJsonMapper.insert(dataJson);
    }


    /**
     * 新增实体对象
     *
     * @param dataJson 实体对象
     * @return 更新行数
     */
    @Override
    public int insertSelective(PlatformDataJson dataJson) {

        Assert.notNull(dataJson, "cannot find dataJson");
        if (null == dataJson.getId())
            dataJson.setId(UUID.randomUUID().toString());

        String unionId = dataJson.getJobId();
        //校验jobid唯一识别是否非空
        Assert.notNull(unionId, "cannot find property jobid");

        return dataJsonMapper.insertSelective(dataJson);
    }

    /**
     * 更新实体对象
     *
     * @param dataJson 实体对象
     * @return 更新行数
     */
    @Override
    public int updateByPrimaryKeySelective(PlatformDataJson dataJson) {

        Assert.notNull(dataJson, "cannot find dataJson");
        Assert.notNull(dataJson.getId(), "cannot find id in object dataJson");
        String unionId = dataJson.getJobId();

        //校验jobid唯一识别是否非空
        Assert.notNull(unionId, "cannot find property jobid");
        return dataJsonMapper.updateByPrimaryKeySelective(dataJson);
    }

    /**
     * 更新实体对象
     *
     * @param dataJson 实体对象
     * @return 更新行数
     */
    @Override
    public int updateByPrimaryKey(PlatformDataJson dataJson) {

        Assert.notNull(dataJson, "cannot find dataJson");
        Assert.notNull(dataJson.getId(), "cannot find id in object dataJson");
        String unionId = dataJson.getJobId();

        //校验jobid唯一识别是否非空
        Assert.notNull(dataJson, "cannot find dataJson");
        return dataJsonMapper.updateByPrimaryKey(dataJson);
    }

    /**
     * 依据主键id查询实体对象
     *
     * @param id 主键id
     * @return 实体对象
     */
    @Override
    public PlatformDataJson selectById(String id) {
        return dataJsonMapper.selectById(id);
    }

    /**
     * 条件查询
     *
     * @param query 查询实体
     * @return 满足条件的实体集合
     */
    @Override
    public List<PlatformDataJson> findPageWithResult(PlatformDataJsonQuery query) {
        return dataJsonMapper.findPageWithResult(query);
    }

    /**
     * 条件查询
     *
     * @param query 查询实体
     * @return 满足条件的实体数量
     */
    @Override
    public int findPageWithCount(PlatformDataJsonQuery query) {
        return dataJsonMapper.findPageWithCount(query);
    }
}
