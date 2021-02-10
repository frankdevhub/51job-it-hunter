package frankdevhub.job.automatic.web.batch;

import frankdevhub.job.automatic.core.parser.PlatformPageParser;
import frankdevhub.job.automatic.core.utils.SpringUtils;
import frankdevhub.job.automatic.dto.PlatformDataJsonQuery;
import frankdevhub.job.automatic.entities.business.JobCompany;
import frankdevhub.job.automatic.entities.business.PlatformDataJson;
import frankdevhub.job.automatic.service.JobCompanyService;
import frankdevhub.job.automatic.service.PlatformDataJsonService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import tk.mybatis.mapper.util.Assert;

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
     * 批量刷新企业信息介绍页面
     *
     * @param query       条件查询
     * @param pageNum     分页页数
     * @param pageSize    分页大小
     * @param companyInfo 是否抓取企业信息相关的内容
     * @param jobList     是否抓取该企业所有正在招聘的职位信息
     */
    public void refreshCompanyData(PlatformDataJsonQuery query, Integer pageNum, Integer pageSize,
                                   boolean companyInfo, boolean jobList) throws InterruptedException {
        //配置默认分页参数大小
        if (null == pageNum) {
            pageNum = DEFAULT_PAGE_NUM;
        }
        if (null == pageSize) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        //循环分页获取全量
        List<PlatformDataJson> datas = new ArrayList<>();
        do {
            datas = getPlatformDataJsonService().findPageWithResult(query, pageNum, pageSize);
            log.info("datas.size() = {}", datas.size());
            if (null == datas || datas.isEmpty()) {
                break;
            }

            List<JobCompany> companys = new ArrayList<>();
            for (PlatformDataJson data : datas) {
                try {
                    String link = data.getCompanyHref(); //企业平台介绍链接
                    if (null == link) {
                        continue;
                    }
                    //解析获取基础实例对象
                    Map<String, Object> map = PlatformPageParser.parseCompanyPlatformPage(link, companyInfo, jobList);
                    JobCompany comp = (JobCompany) map.get("company");
                    Assert.notNull(comp, "company data cannot be null");
                    companys.add(comp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Runnable task = () -> {
                try {
                    //线程池批量持久化
                    Thread t = new JobCompanyRestoreThread(companys);
                    t.start();
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            Thread thread = new Thread(task);
            thread.setDaemon(true); //设置为守护进程
            threadPool.execute(thread);  //提交任务至线程池
            //TODO:依据策略进行解析操作
            Thread.sleep(5000L);
            pageNum++;
        } while (null != datas && datas.size() > 0);
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
            for (JobCompany company : companyList) {
                try {
                    //unionId查询判断是否库中已经存在
                    JobCompany c = getJobCompanyService().selectByUnionId(company.getUnionId());
                    if (null != c) {
                        company.doUpdateEntity();
                        company.setId(c.getId());
                        getJobCompanyService().updateByPrimaryKeySelective(company);
                    } else {
                        company.doCreateEntity();
                        getJobCompanyService().insertSelective(company);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
