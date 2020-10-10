package frankdevhub.job.automatic.configuration;

import frankdevhub.job.automatic.core.utils.ViewMapper;
import frankdevhub.job.automatic.entities.ConfigProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

@Configuration
@AutoConfigureAfter(MybatisConfig.class)
public class MyBatisMapperScannerConfig {

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		mapperScannerConfigurer.setBasePackage("frankdevhub.job.automatic.mapper");

		ConfigProperties props = new ConfigProperties();
		props.setProperty("mappers", ViewMapper.class.getName()).setProperty("notEmpty", "false")
				.setProperty("IDENTITY", "MYSQL");

		mapperScannerConfigurer.setProperties(props);
		return mapperScannerConfigurer;
	}
}
