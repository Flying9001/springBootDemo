package com.ljq.demo.springboot.cloud.zookeeper.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author junqiang.lu
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CloudZookeeperRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudZookeeperRibbonApplication.class, args);
    }

    @LoadBalanced
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }


}
