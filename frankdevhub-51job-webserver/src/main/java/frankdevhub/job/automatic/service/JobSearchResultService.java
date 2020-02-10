package frankdevhub.job.automatic.service;

import com.github.pagehelper.PageInfo;
import frankdevhub.job.automatic.core.constants.BusinessConstants;
import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import frankdevhub.job.automatic.core.data.rest.results.Response;
import frankdevhub.job.automatic.core.message.MessageMethod;
import frankdevhub.job.automatic.core.utils.SpringUtils;
import frankdevhub.job.automatic.entities.JobSearchResult;
import frankdevhub.job.automatic.repository.JobSearchResultRepository;
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

@RestController(value = "/jd")
public class JobSearchResultService {

    private Logger LOGGER = LoggerFactory.getLogger(JobSearchResultService.class);

    private JobSearchResultRepository getJobSearchResultRepository() {
        return SpringUtils.getBean(JobSearchResultRepository.class);
    }

    @RequestMapping(value = "/data/search/publish/today", method = RequestMethod.GET)
    public Response<PageInfo<JobSearchResult>> getJobSearchResultPublishedToday(@RequestParam(name = "pageNum") Integer pageNum,
                                                                                @RequestParam(name = "pageSize") Integer pageSize) {
        try {
            LOGGER.begin().headerMethod(MessageMethod.GET).info("invoke {{/data/search/publish/today}}::getJobSearchResultByPublishDate() start ");
            return new Response<PageInfo<JobSearchResult>>().setData(null).setMsg(BusinessConstants.SUCCESS).success();
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<PageInfo<JobSearchResult>>().setData(null).setMsg(e.getMessage()).failed(e);
        }
    }
}
