package frankdevhub.job.automatic.controller;

import com.github.pagehelper.PageInfo;
import frankdevhub.job.automatic.core.constants.BusinessConstants;
import frankdevhub.job.automatic.core.data.rest.results.Response;
import frankdevhub.job.automatic.entities.JobSearchResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title:@ClassName JobSearchResultService.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/11 7:24
 * @Version: 1.0
 */

@Slf4j
@RestController(value = "/jd")
@SuppressWarnings("all")
public class JobSearchResultService {

    @Autowired
    private JobSearchResultService searchResultService;

    /**
     * 查询今日扫描获取的职位信息
     *
     * @param pageNum  分页参数第几页
     * @param pageSize 分页参数每页行数
     * @return 分页结果集列表对象
     */
    @RequestMapping(value = "/data/search/publish/today", method = RequestMethod.GET)
    public Response<PageInfo<JobSearchResult>> listSerachResultToday(@RequestParam(name = "pageNum", required = true) Integer pageNum,
                                                                     @RequestParam(name = "pageSize", required = true) Integer pageSize) {
        try {
            log.info("getJobSearchResultPublishedToday start");
            return new Response<PageInfo<JobSearchResult>>().setData(null).setMsg(BusinessConstants.SUCCESS).success();
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<PageInfo<JobSearchResult>>().setData(null).setMsg(e.getMessage()).failed(e);
        }
    }
}
