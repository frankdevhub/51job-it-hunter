package frankdevhub.job.automatic;

import frankdevhub.job.automatic.core.constants.BusinessConstants;
import frankdevhub.job.automatic.web.clients.JobPlatformService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@EnableTransactionManagement
@SpringBootApplication
@ComponentScan(basePackages = {"frankdevhub.job.automatic"})
@SuppressWarnings("all")
public class JobWebAutoService {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(JobWebAutoService.class, args);
        // 主方法,爬虫模式扫描搜索返回的结果集列表
        new JobPlatformService().defaultDataPatrolService(BusinessConstants.DEFAULT_SEARCH_JAVA);
    }
}
