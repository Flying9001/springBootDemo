package com.ljq.demo.springboot.alibaba.consumer.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author junqiang.lu
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class CloudAlibabaConsumerSentinelApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudAlibabaConsumerSentinelApplication.class, args);
    }

}
