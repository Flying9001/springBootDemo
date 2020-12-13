package com.ljq.demo.springboot.alibaba.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author junqiang.lu
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CloudAlibabaGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudAlibabaGatewayApplication.class, args);
    }

}
