package frankdevhub.job.automatic.service.impl;

import frankdevhub.job.automatic.core.repository.MyBatisRepository;
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
public class JobSearchResultServiceImpl extends MyBatisRepository implements JobSearchResultService {

    @Autowired
    private JobSearchResultMapper searchResultMapper;

    /**
     * 根据主键删除对象
     *
     * @param id 主键id
     * @return 更新行数
     */
    @Override
    public int deleteByPrimaryKey(String id) {
        return searchResultMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增实体对象
     *
     * @param result 实体对象
     * @return 更新行数
     */
    @Override
    public int insert(JobSearchResult result) {
        return searchResultMapper.insert(result);
    }

    /**
     * 新增实体对象
     *
     * @param result 实体对象
     * @return 更新行数
     */
    @Override
    public int insertSelective(JobSearchResult result) {
        return searchResultMapper.insertSelective(result);
    }

    /**
     * 更新实体对象
     *
     * @param result 实体对象
     * @return 更新行数
     */
    @Override
    public int updateByPrimaryKeySelective(JobSearchResult result) {
        return searchResultMapper.updateByPrimaryKeySelective(result);
    }

    /**
     * 更新实体对象
     *
     * @param result 实体对象
     * @return 更新行数
     */
    @Override
    public int updateByPrimaryKey(JobSearchResult result) {
        return searchResultMapper.updateByPrimaryKey(result);
    }

    /**
     * 依据主键id查询实体对象
     *
     * @param id 主键id
     * @return 实体对象
     */
    @Override
    public JobSearchResult selectById(String id) {
        return searchResultMapper.selectById(id);
    }

    /**
     * 条件查询
     *
     * @param query 查询实体
     * @return 满足条件的实体集合
     */
    @Override
    public List<JobSearchResult> findPageWithResult(JobSearchResultQuery query) {
        return searchResultMapper.findPageWithResult(query);
    }

    /**
     * 条件查询
     *
     * @param query 查询实体
     * @return 满足条件的实体数量
     */
    @Override
    public int findPageWithCount(JobSearchResultQuery query) {
        return searchResultMapper.findPageWithCount(query);
    }

    /**
     * 依据唯一识别号查询实体对象的数量
     *
     * @param markId 唯一识别号
     * @return 满足条件的实体数量
     */
    @Override
    public int selectCountByUnionId(Integer unionId) {
        return searchResultMapper.selectCountByUnionId(unionId);
    }

    /**
     * 依据唯一识别号查询实体对象的数量
     *
     * @param markId 唯一识别号
     * @return 实体对象
     */
    @Override
    public JobSearchResult selectByUnionId(Integer unionId) {
        return searchResultMapper.selectByUnionId(unionId);
    }
}
