package com.ljq.demo.springboot.alibaba.gateway.filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author junqiang.lu
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CloudAlibabaGatewayFilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudAlibabaGatewayFilterApplication.class, args);
    }

}
