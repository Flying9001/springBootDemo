package com.ljq.demo.springboot.cloud.zookeeper.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author junqiang.lu
 */
@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class CloudZookeeperConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudZookeeperConfigServerApplication.class, args);
    }

}
