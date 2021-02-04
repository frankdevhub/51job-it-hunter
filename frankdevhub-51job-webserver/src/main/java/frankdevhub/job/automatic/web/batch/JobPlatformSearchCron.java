package frankdevhub.job.automatic.web.batch;

import frankdevhub.job.automatic.core.parser.PlatformPageParser;
import frankdevhub.job.automatic.core.utils.SpringUtils;
import frankdevhub.job.automatic.dto.PlatformDataJsonQuery;
import frankdevhub.job.automatic.entities.JobCompany;
import frankdevhub.job.automatic.entities.PlatformDataJson;
import frankdevhub.job.automatic.service.JobCompanyService;
import frankdevhub.job.automatic.service.PlatformDataJsonService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private final ThreadPoolExecutor threadPool; //扫描解析的线程池对象

    public JobPlatformSearchCron() {
        this.CPU_CAPBILITY = Runtime.getRuntime().availableProcessors();
        this.threadPool = new ThreadPoolExecutor(2 * CPU_CAPBILITY + 1, Integer.MAX_VALUE, 100, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), new CustomizableThreadFactory("cron-batch-service"),
                new ThreadPoolExecutor.CallerRunsPolicy()); //解析每一个列表页面的线程对象所在的线程池
    }

    private JobCompanyService getJobCompanyService() {
        return SpringUtils.getBean(JobCompanyService.class);
    }

    private PlatformDataJsonService getPlatformDataJsonService() {
        return SpringUtils.getBean(PlatformDataJsonService.class);
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

        @Override
        public void run() {

        }
    }

    /**
     * 批量刷新企业信息介绍页面
     *
     * @param query    条件查询
     * @param pageNum  分页页数
     * @param pageSize 分页大小
     */
    public void refreshCompanyData(PlatformDataJsonQuery query, Integer pageNum, Integer pageSize) {
        //配置默认分页参数大小
        if (null == pageNum)
            pageNum = DEFAULT_PAGE_NUM;
        if (null == pageSize)
            pageSize = DEFAULT_PAGE_SIZE;

        //循环分页获取全量
        while (true) {
            List<PlatformDataJson> datas = getPlatformDataJsonService().findPageWithResult(query, pageNum, pageSize);
            List<JobCompany> companys = new ArrayList<>();
            for (PlatformDataJson data : datas) {
                try {
                    String link = data.getCompanyHref(); //企业平台介绍链接
                    if (null == link) {
                        continue;
                    }
                    //解析获取基础实例对象
                    //TODO: 同时解析其他企业信息,资质信息,运营信息,职位列表
                    Map<String, Object> map = PlatformPageParser.parseCompanyPlatformPage(link, false, false);
                    JobCompany comp = (JobCompany) map.get("company");
                    companys.add(comp);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //线程池批量持久化
            Thread t = new JobCompanyRestoreThread(companys);
            threadPool.execute(t);
        }
    }

}
