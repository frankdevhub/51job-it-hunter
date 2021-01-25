package frankdevhub.job.automatic;

import frankdevhub.job.automatic.core.utils.SpringUtils;
import frankdevhub.job.automatic.web.pages.JobPlatformSearchPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@Slf4j
@MapperScan(basePackages = "frankdevhub.job.automatic.mapper")
@EnableTransactionManagement
@SpringBootApplication
public class JobWebAutoService {

    public static void main(String[] args) {
        try {
            SpringApplication.run(JobWebAutoService.class, args);
            log.info("start running service");
            ((JobPlatformSearchPage) SpringUtils.getBean(JobPlatformSearchPage.class)).startSearchResultPatrol();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
