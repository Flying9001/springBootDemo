package com.ljq.demo.springboot.schedule.common.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @Description: 数据源配置-receiveAddress
 * @Author: junqiang.lu
 * @Date: 2019/6/16
 */
@Slf4j
@Configuration
@MapperScan(basePackages = {"com.ljq.demo.springboot.dao.receiveaddress"},sqlSessionTemplateRef = "sqlSessionTemplateReceiveAddress")
public class DataSourceReceiveAddressConfig {

    @Autowired
    private ConfigurableEnvironment env;

    /**
     * 数据源配置
     *
     * @return
     */
    @Bean(name = "dataSourceReceiveAddress")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        log.info("{}"," 【receiveAddress】初始化数据源配置");
        log.debug("\n driverClassName: {},\n url: {},\n username: {},\n password: {}",
                env.getProperty("spring.datasource.druid.driver-class-name"),
                env.getProperty("spring.datasource.receiveAddress.url"),
                env.getProperty("spring.datasource.receiveAddress.username"),
                env.getProperty("spring.datasource.receiveAddress.password"));
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.druid.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.receiveAddress.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.receiveAddress.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.receiveAddress.password"));
        return dataSource;
    }

    /**
     * 数据会话工厂
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "sqlSessionFactoryReceiveAddress")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSourceReceiveAddress") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        // 仅当使用 Mapper 文件存放SQL时设置该部分;如果使用注解方式写SQL语句,则不设置
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));
        factoryBean.setTypeAliasesPackage(env.getProperty("mybatis.type-aliases-package"));

        return factoryBean.getObject();
    }

    /**
     * 数据库事务管理
     *
     * @param dataSource
     * @return
     */
    @Bean(name = "transactionManagerReceiveAddress")
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSourceReceiveAddress") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 数据库会话实例
     *
     * @param sqlSessionFactory
     * @return
     */
    @Bean(name = "sqlSessionTemplateReceiveAddress")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactoryReceiveAddress") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }




}
