package frankdevhub.job.automatic.web.batch;

import frankdevhub.job.automatic.core.utils.SpringUtils;
import frankdevhub.job.automatic.entities.JobCompany;
import frankdevhub.job.automatic.service.JobCompanyService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Title: JobPlatformSearchCron
 * @Description: 平台定时搜索收纳新数据的定时任务
 * @date: 2021/2/3 23:42
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */

@Slf4j
@SuppressWarnings("all")
public class JobPlatformSearchCron {

    private final Integer DEFAULT_PAGE_NUM = 1; //默认的分页页数
    private final Integer DEFAULT_PAGE_SIZE = 100; //默认的分页页面单位大小

    private final Integer CPU_CAPBILITY; //CPU性能
    private ThreadPoolExecutor threadPool; //扫描解析的线程池对象

    public JobPlatformSearchCron() {
        this.CPU_CAPBILITY = Runtime.getRuntime().availableProcessors();
        this.threadPool = new ThreadPoolExecutor(2 * CPU_CAPBILITY + 1, Integer.MAX_VALUE, 100, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), new CustomizableThreadFactory("cron-batch-service"),
                new ThreadPoolExecutor.CallerRunsPolicy()); //解析每一个列表页面的线程对象所在的线程池
    }

    private JobCompanyService getJobCompanyService() {
        return SpringUtils.getBean(JobCompanyService.class);
    }

    /**
     * 批量进行企业信息解析,解析contextHtml获取企业资质类型,运营类型
     */
    @Data
    private class JobCompanyRestoreThread extends Thread {
        private List<JobCompany> companyList;

        public JobCompanyRestoreThread(List<JobCompany> list) {
            this.companyList = list;
        }
    }

    /**
     * 批量刷新企业信息介绍页面
     *
     * @param pageNum  分页页数
     * @param pageSize 分页大小
     */
    public void refreshCompanyData(Integer pageNum, Integer pageSize) {
        //配置默认分页参数大小
        if (null == pageNum)
            pageNum = DEFAULT_PAGE_NUM;
        if (null == pageSize)
            pageSize = DEFAULT_PAGE_SIZE;


    }

}
