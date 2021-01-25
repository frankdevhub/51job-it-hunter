package frankdevhub.job.automatic.configuration;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;


/**
 * @Title: MyBatisMapperScannerConfig
 * @Description:
 * @date: 2021/1/25 0:23
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */

@Configuration
@AutoConfigureAfter(MybatisConfig.class)
public class MyBatisMapperScannerConfig {

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		mapperScannerConfigurer.setBasePackage("frankdevhub.job.automatic.mapper");
		return mapperScannerConfigurer;
	}
}
