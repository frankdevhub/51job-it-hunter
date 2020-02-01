package frankdevhub.job.automatic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@ComponentScan(basePackages = {"frankdevhub.job.automatic"})
@MapperScan(basePackages = {"frankdevhub.job.automatic.mapper"})
@EnableTransactionManagement
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class JobWebAutoService {
    public static void main(String[] args) {
        SpringApplication.run(JobWebAutoService.class, args);
    }
}
