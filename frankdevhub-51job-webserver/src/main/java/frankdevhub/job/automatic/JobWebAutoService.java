package frankdevhub.job.automatic;

import frankdevhub.job.automatic.web.pages.JobPlatformSearchPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan(basePackages = {"frankdevhub.job.automatic"})
@MapperScan(basePackages = "frankdevhub.job.automatic.mapper")
@EnableTransactionManagement
@SuppressWarnings("all")
public class JobWebAutoService {

    @Autowired
    private static JobPlatformSearchPage jobPlatformSearchPage;

    public static void main(String[] args) {
        SpringApplication.run(JobWebAutoService.class, args);
        try {
            jobPlatformSearchPage.startSearchResultPatrol();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
