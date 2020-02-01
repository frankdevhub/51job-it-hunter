package frankdevhub.job.automatic.configuration;


import frankdevhub.job.automatic.core.utils.ViewMapper;
import frankdevhub.job.automatic.entities.ConfigProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * <p>Title:@ClassName MyBatisMapperScannerConfig.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/1 21:58
 * @Version: 1.0
 */
@Configuration
@AutoConfigureAfter(MybatisConfig.class)
public class MyBatisMapperScannerConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("frankdevhub.job.automatic.mapper");

        ConfigProperties props = new ConfigProperties();
        props.setProperty("mappers", ViewMapper.class.getName())
                .setProperty("notEmpty", "false")
                .setProperty("IDENTITY", "MYSQL");

        mapperScannerConfigurer.setProperties(props);
        return mapperScannerConfigurer;
    }
}
