package com.ljq.demo.springboot.cloud.zookeeper.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author junqiang.lu
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CloudZookeeperConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudZookeeperConfigClientApplication.class, args);
    }

}
