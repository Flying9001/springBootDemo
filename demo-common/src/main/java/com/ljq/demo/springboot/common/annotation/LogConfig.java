package com.ljq.demo.springboot.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 出入参日志配置参数
 * @Author: junqiang.lu
 * @Date: 2020/1/14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogConfig {


    /**
     * 是否忽略传入参数,默认为 false
     *
     * @return
     */
    boolean ignoreInput() default false;

    /**
     * 是否忽略返回参数,默认为 false
     *
     * @return
     */
    boolean ignoreOutput() default false;
}
