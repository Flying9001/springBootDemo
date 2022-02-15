package com.ljq.demo.springboot.readwrite.common.annotation;

import com.ljq.demo.springboot.readwrite.common.constant.DataSourceConst;

import java.lang.annotation.*;

/**
 * @Description: 数据源路由注解
 * @Author: junqiang.lu
 * @Date: 2022/2/14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataSourceRouting {

    String value() default DataSourceConst.DATASOURCE_MASTER;

}
