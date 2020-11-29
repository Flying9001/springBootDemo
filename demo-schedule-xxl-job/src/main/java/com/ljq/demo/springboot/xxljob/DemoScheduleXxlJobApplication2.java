package com.ljq.demo.springboot.xxljob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author junqiang.lu
 */
@SpringBootApplication
public class DemoScheduleXxlJobApplication2 {

    public static void main(String[] args) {
        System.setProperty("server.port", "8562");
        System.setProperty("xxl.job.executor.port","8567");
        SpringApplication.run(DemoScheduleXxlJobApplication2.class, args);
    }

}
