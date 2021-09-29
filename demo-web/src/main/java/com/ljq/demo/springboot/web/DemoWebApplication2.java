package com.ljq.demo.springboot.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Caleb
 * @date 2018-10-09
 */
@SpringBootApplication
@EnableEurekaServer
@ComponentScan(basePackages = {"com.ljq.demo"})
@MapperScan("com.ljq.demo.springboot.dao")
@ServletComponentScan
@EnableCaching
public class DemoWebApplication2 extends SpringBootServletInitializer {

    public static void main(String[] args) {
        System.setProperty("server.port", "8089");
        SpringApplication.run(DemoWebApplication2.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoWebApplication2.class);
    }

    /// 取消使用 Filter 作为拦截器
//    /**
//     * 跨域处理
//     *
//     * @return
//     */
//    @Bean
//    public SimpleCORSFilter simpleCORSFilter(){
//        return new SimpleCORSFilter();
//    }

}
