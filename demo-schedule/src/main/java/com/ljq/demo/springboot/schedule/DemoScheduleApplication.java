package com.ljq.demo.springboot.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author junqiang.lu
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class},scanBasePackages = {"com.ljq.demo.springboot"})
@EnableScheduling
public class DemoScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoScheduleApplication.class, args);
    }

}
