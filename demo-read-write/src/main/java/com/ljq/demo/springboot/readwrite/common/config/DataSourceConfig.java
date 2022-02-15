package com.ljq.demo.springboot.readwrite.common.config;

import com.ljq.demo.springboot.readwrite.common.constant.DataSourceConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 数据源配置
 * @Author: junqiang.lu
 * @Date: 2022/2/14
 */
@Slf4j
@Configuration
public class DataSourceConfig {


    /**
     * 主数据源
     *
     * @return
     */
    @Bean(DataSourceConst.DATASOURCE_MASTER)
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        log.info("init master datasource");
        return DataSourceBuilder.create().build();
    }

    /**
     * 从数据源
     * @return
     */
    @Bean(DataSourceConst.DATASOURCE_SLAVE)
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        log.info("init slave datasource");
        return DataSourceBuilder.create().build();
    }

    /**
     * 主数据源
     *
     * @param masterDataSource
     * @param slaveDataSource
     * @return
     */
    @Bean
    @Primary
    DataSource primaryDataSource(@Qualifier(DataSourceConst.DATASOURCE_MASTER) DataSource masterDataSource,
                                 @Qualifier(DataSourceConst.DATASOURCE_SLAVE) DataSource slaveDataSource) {
        log.info("init datasource routing");
        Map<Object, Object> map = new HashMap<>(8);
        map.put(DataSourceConst.DATASOURCE_MASTER, masterDataSource);
        map.put(DataSourceConst.DATASOURCE_SLAVE, slaveDataSource);
        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setTargetDataSources(map);
        routingDataSource.setDefaultTargetDataSource(masterDataSource);
        return routingDataSource;
    }




}
