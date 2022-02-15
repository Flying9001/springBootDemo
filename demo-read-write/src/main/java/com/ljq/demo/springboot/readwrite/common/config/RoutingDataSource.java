package com.ljq.demo.springboot.readwrite.common.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Description: 路由数据库
 * @Author: junqiang.lu
 * @Date: 2022/2/14
 */
public class RoutingDataSource extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        return RoutingDataSourceContext.get();
    }
}
