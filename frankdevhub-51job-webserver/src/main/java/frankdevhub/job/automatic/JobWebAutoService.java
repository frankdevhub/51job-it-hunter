package frankdevhub.job.automatic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan(basePackages = {"frankdevhub.job.automatic"})
@MapperScan(basePackages = "frankdevhub.job.automatic.mapper")
@EnableTransactionManagement
public class JobWebAutoService {
    public static void main(String[] args) {
        SpringApplication.run(JobWebAutoService.class, args);
    }
}
