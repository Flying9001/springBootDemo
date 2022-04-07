package com.ljq.demo.springboot.sharding.jdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lujunqiang
 */
@MapperScan(basePackages = {"com.ljq.demo.springboot.sharding.jdbc.dao"})
@SpringBootApplication
public class DemoShardingJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoShardingJdbcApplication.class, args);
    }

}
