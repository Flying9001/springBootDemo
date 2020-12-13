package com.ljq.demo.springboot.alibaba.server.consumer.feign;

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
public class CloudAlibabaServerConsumerFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudAlibabaServerConsumerFeignApplication.class, args);
    }

}
