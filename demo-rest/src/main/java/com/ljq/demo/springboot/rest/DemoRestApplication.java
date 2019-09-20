package com.ljq.demo.springboot.rest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author junqiang.lu
 * @date 2019-09-05
 */
@SpringBootApplication(scanBasePackages = {"com.ljq.demo.springboot.rest"})
@MapperScan({"com.ljq.demo.springboot.rest.dao"})
public class DemoRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoRestApplication.class, args);
    }

}
