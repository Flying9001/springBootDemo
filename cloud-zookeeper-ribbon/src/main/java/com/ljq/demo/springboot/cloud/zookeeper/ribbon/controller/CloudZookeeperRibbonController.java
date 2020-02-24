package com.ljq.demo.springboot.cloud.zookeeper.ribbon.controller;

import com.ljq.demo.springboot.cloud.zookeeper.ribbon.service.CloudZookeeperRibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: Spring cloud Zookeeper Ribbon 服务消费者控制层
 * @Author: junqiang.lu
 * @Date: 2020/2/24
 */
@RestController
@RequestMapping("/api/cloud/zookeeper/ribbon")
public class CloudZookeeperRibbonController {

    @Autowired
    private CloudZookeeperRibbonService cloudZookeeperRibbonService;


    /**
     * 打印用户名称
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/sayHello", method = {RequestMethod.GET, RequestMethod.POST},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public String sayHello(@RequestParam("name") String name) {
        return cloudZookeeperRibbonService.sayHello(name);
    }


}
