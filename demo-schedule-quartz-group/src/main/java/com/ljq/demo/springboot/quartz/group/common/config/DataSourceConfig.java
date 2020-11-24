package com.ljq.demo.springboot.quartz.group.common.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * @Description: 数据源配置
 * @Author: junqiang.lu
 * @Date: 2020/11/17
 */
@Configuration
public class DataSourceConfig {

    /**
     * 用户数据源配置(主数据源)
     *
     * @return
     */
    @Primary
    @Bean("userDatasourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.user")
    public DataSourceProperties userDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * 用户数据源(主数据源)
     *
     * @return
     */
    @Primary
    @Bean("userDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.user.hikari")
    public DataSource userDataSource() {
        DataSourceProperties properties = this.userDataSourceProperties();
        return createHikariDataSource(properties);
    }

    /**
     * Quartz 数据源配置
     *
     * @return
     */
    @Bean("quartzDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.quartz")
    public DataSourceProperties quartzDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * Quartz 数据源
     *
     * @return
     */
    @Bean("quartzDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.quartz.hikari")
    @QuartzDataSource
    public DataSource quartzDataSource() {
        DataSourceProperties properties = this.quartzDataSourceProperties();
        return createHikariDataSource(properties);
    }

    /**
     * 创建 Hikari 数据库连接池
     *
     * @param properties
     * @return
     */
    private HikariDataSource createHikariDataSource(DataSourceProperties properties) {
        HikariDataSource dataSource = properties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
        if (StringUtils.hasText(properties.getName())) {
            dataSource.setPoolName(properties.getName());
        }
        return dataSource;
    }

}
