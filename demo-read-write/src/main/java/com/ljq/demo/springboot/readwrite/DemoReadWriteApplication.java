package com.ljq.demo.springboot.readwrite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ls-ljq
 */
@MapperScan(basePackages = {"com.ljq.demo.springboot.readwrite.dao"})
@SpringBootApplication(scanBasePackages = {"com.ljq.demo.springboot.readwrite"})
public class DemoReadWriteApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoReadWriteApplication.class, args);
    }

}
