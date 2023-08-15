package com.ljq.demo.springboot.knife4j;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dell
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.ljq.demo.springboot.knife4j.mapper"})
public class DemoKnife4jOpenapi3Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoKnife4jOpenapi3Application.class, args);
    }

}
