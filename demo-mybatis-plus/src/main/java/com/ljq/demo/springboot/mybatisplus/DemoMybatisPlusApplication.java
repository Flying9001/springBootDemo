package com.ljq.demo.springboot.mybatisplus;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author junqiang.lu
 */
@EnableSwagger2
@SpringBootApplication(scanBasePackages = {"com.ljq.demo.springboot.mybatisplus.*"})
@MapperScan(basePackages = {"com.ljq.demo.springboot.mybatisplus.dao"})
@EnableMethodCache(basePackages = "com.ljq.demo.springboot")
public class DemoMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMybatisPlusApplication.class, args);
    }

}
