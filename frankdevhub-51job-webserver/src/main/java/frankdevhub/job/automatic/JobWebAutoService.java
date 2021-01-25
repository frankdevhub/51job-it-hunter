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
            new JobPlatformSearchPage(false, "java").startSearchResultPatrol();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
