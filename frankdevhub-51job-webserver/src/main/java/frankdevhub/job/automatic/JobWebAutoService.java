package frankdevhub.job.automatic;

import frankdevhub.job.automatic.web.pages.JobPlatformSearchPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@EnableTransactionManagement
@SpringBootApplication
@ComponentScan(basePackages = {"frankdevhub.job.automatic"})
public class JobWebAutoService {
    public static void main(String[] args) {
        try {
            SpringApplication.run(JobWebAutoService.class, args);
            log.info("start running service");
            //可视化Selenium驱动模式
            new JobPlatformSearchPage(false, "java").startSearchResultPatrol();
            //Jsoup爬虫模式
            //new JobPlatformService().defaultDataPatrolService(BusinessConstants.JOB_PLATFORM_HOMEPAGE_SH);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
