package com.ljq.demo.springboot.readwrite.interceptor;

import com.ljq.demo.springboot.readwrite.common.annotation.DataSourceRouting;
import com.ljq.demo.springboot.readwrite.common.config.RoutingDataSourceContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description: 数据源切换AOP
 * @Author: junqiang.lu
 * @Date: 2022/2/14
 */
@Slf4j
@Aspect
@Order(1)
@Component
public class DataSourceAop {

    /**
     * 设置动态数据源
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.ljq.demo.springboot.readwrite.common.annotation.DataSourceRouting)")
    public Object setDynamicDataSource(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        DataSourceRouting dataSourceRouting = method.getAnnotation(DataSourceRouting.class);
        try {
            RoutingDataSourceContext.set(dataSourceRouting.value());
            log.info("current request datasource: {}", RoutingDataSourceContext.get());
            return joinPoint.proceed();
        } finally {
            RoutingDataSourceContext.close();
        }
    }
}
