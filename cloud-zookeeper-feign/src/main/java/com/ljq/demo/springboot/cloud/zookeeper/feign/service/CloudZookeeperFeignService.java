package com.ljq.demo.springboot.cloud.zookeeper.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: Spring Cloud Zookeeper 分布式服务消费者-Feign 业务层
 * @Author: junqiang.lu
 * @Date: 2020/2/25
 */
@FeignClient(value = "cloud-zookeeper-provider")
@Service("cloudZookeeperFeignService")
public interface CloudZookeeperFeignService {


    /**
     * 打印用户名称
     *
     * @param name
     * @return
     */
    @GetMapping(value = "/api/cloud/zookeeper/hello", produces = {MediaType.APPLICATION_JSON_VALUE})
    String sayHello(@RequestParam("name") String name);


}
