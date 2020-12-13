package com.ljq.demo.springboot.alibaba.sentinel.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author junqiang.lu
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CloudAlibabaSentinelDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudAlibabaSentinelDashboardApplication.class, args);
    }

}
