package com.ljq.demo.pringboot.alibaba.server.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author junqiang.lu
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CloudAlibabaServerProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudAlibabaServerProviderApplication.class, args);
    }

}
