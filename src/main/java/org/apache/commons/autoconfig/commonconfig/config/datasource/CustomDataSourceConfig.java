package org.apache.commons.autoconfig.commonconfig.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = CustomDataSourceConfig.CUSTOM_PACKAGE,sqlSessionFactoryRef = "customSqlSessionFactory")
public class CustomDataSourceConfig {

    private Logger logger = LoggerFactory.getLogger(CustomDataSourceConfig.class);


    static final String CUSTOM_PACKAGE = "com.jcl.pbcms.mapper1";
    private static final String CUSTOM_MAPPER_LOCAL = "classpath:mybatis/mapper1/*.xml";

    @Bean(name = "customDataSource")
    @ConfigurationProperties(prefix = "custom.datasource")
    public DataSource customDruidDataSource(){
        return new DruidDataSource();
    }

    @Bean(name = "customSqlSessionFactory")
    public SqlSessionFactory customSqlSessionFactory(){
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(customDruidDataSource());

        try {
            sessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
            sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(CUSTOM_MAPPER_LOCAL));
            return sessionFactoryBean.getObject();
        } catch (Exception e) {
            logger.error("配置从库的SqlSessionFactory失败，error:{}",e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Bean(name = "customTransactionManager")
    public DataSourceTransactionManager customTransactionManager(){
        return new DataSourceTransactionManager(customDruidDataSource());
    }

}