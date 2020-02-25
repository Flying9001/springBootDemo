package com.ljq.demo.springboot.cloud.zookeeper.feign.controller;

import com.ljq.demo.springboot.cloud.zookeeper.feign.service.CloudZookeeperFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Description: Spring Cloud Zookeeper 分布式服务消费者-Feign 控制层
 * @Author: junqiang.lu
 * @Date: 2020/2/25
 */
@RestController
@RequestMapping("/api/cloud/zookeeper/feign")
public class CloudZookeeperFeignController {

    @Autowired
    private CloudZookeeperFeignService cloudZookeeperFeignService;

    /**
     * 打印用户名称
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/sayHello", method = {RequestMethod.GET, RequestMethod.POST})
    public String sayHello(@RequestParam("name") String name) {
        System.out.println(new Date() + "-" + name);
        return cloudZookeeperFeignService.sayHello(name);
    }


}
