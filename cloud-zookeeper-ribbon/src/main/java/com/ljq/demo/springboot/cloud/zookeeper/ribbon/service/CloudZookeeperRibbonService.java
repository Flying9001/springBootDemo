package com.ljq.demo.springboot.cloud.zookeeper.ribbon.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description: Spring Cloud Zookeeper Ribbon 服务消费者业务层
 * @Author: junqiang.lu
 * @Date: 2020/2/24
 */
@Service("cloudZookeeperRibbonService")
public class CloudZookeeperRibbonService {

    /**
     * Zookeeper 服务注册中心服务名称
     */
    private static final String CLOUD_ZOOKEEPER_PROVIDER_NAME = "cloud-zookeeper-provider";
    /**
     * Zookeeper 服务注册中心接口地址-打印用户名称
     */
    private static final String API_PATH_ZOOKEEPER_HELLO = "/api/cloud/zookeeper/hello";


    @Resource
    private RestTemplate restTemplate;

    /**
     * 打印用户名称
     *
     * @param name
     * @return
     */
    public String sayHello(String name) {
        StringBuilder reqUrl = new StringBuilder("http://");
        reqUrl.append(CLOUD_ZOOKEEPER_PROVIDER_NAME);
        reqUrl.append(API_PATH_ZOOKEEPER_HELLO);
        reqUrl.append("?name=").append(name);

        System.out.println(new Date() + "-" + reqUrl.toString());

        return restTemplate.getForEntity(reqUrl.toString(), String.class).getBody();
    }


}
