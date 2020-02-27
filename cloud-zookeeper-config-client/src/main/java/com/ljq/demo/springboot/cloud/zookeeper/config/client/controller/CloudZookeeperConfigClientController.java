package com.ljq.demo.springboot.cloud.zookeeper.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Description: Spring Cloud Zookeeper 分布式配置客户端
 * @Author: junqiang.lu
 * @Date: 2020/2/26
 */
@RestController
@RequestMapping("/api/cloud/zookeeper/config")
public class CloudZookeeperConfigClientController {

    @Value("${spring.application.name: springCloudZookeeperConfigClientDefault}")
    private String springApplicationName;

    @Value("${applicationName: applicationNameDefault}")
    private String applicationName;

    @Value("${server.port: 8080}")
    private String serverPort;

    @Value("${address.detail: ShanghaiDefault}")
    private String detailAddress;

    @Value("${fileName: fileNameDefault}")
    private String fileName;


    @RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public String readConfig() {
        StringBuilder configBuilder = new StringBuilder();
        configBuilder.append("springApplicationName: ").append(this.springApplicationName).append(",\n");
        configBuilder.append("applicationName: ").append(this.applicationName).append(",\n");
        configBuilder.append("serverPort: ").append(this.serverPort).append(",\n");
        configBuilder.append("detailAddress: ").append(this.detailAddress).append(",\n");
        configBuilder.append("fileName:").append(fileName).append("\n");

        System.out.println(new Date() + "-" + configBuilder.toString());

        return configBuilder.toString();
    }

}
