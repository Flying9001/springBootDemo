package com.ljq.demo.springboot.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 参数校验注解
 * @Author: junqiang.lu
 * @Date: 2019/1/24
 */
@Target({ ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamsCheck {

    /**
     * 是否忽略
     * 当 ignore = true 时,其他属性设置无效
     *
     * @return
     */
    boolean ignore() default false;

}
