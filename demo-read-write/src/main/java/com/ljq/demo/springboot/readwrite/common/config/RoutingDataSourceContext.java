package com.ljq.demo.springboot.readwrite.common.config;

import com.ljq.demo.springboot.readwrite.common.constant.DataSourceConst;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 路由数据源路由上下文
 * @Author: junqiang.lu
 * @Date: 2022/2/14
 */
@Slf4j
public class RoutingDataSourceContext {

    private RoutingDataSourceContext(){
    }

    private static final ThreadLocal<String> threadDataSourceKey = new ThreadLocal<>();

    /**
     * 设置数据源 key
     *
     * @param key
     */
    public static void set(String key) {
        threadDataSourceKey.set(key);
    }

    /**
     * 获取数据源 key
     *
     * @return
     */
    public static String get() {
        log.info("thread local datasource key:{}", threadDataSourceKey.get());
        return threadDataSourceKey.get() == null ? DataSourceConst.DATASOURCE_MASTER : threadDataSourceKey.get();
    }

    /**
     * 清理数据源 key
     */
    public static void close() {
        threadDataSourceKey.remove();
    }


}
