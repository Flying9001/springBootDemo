package com.ljq.demo.springboot.cloud.zookeeper.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Description: Zookeeper 注册中心控制层
 * @Author: junqiang.lu
 * @Date: 2020/2/23
 */
@RestController
@RequestMapping(value = "/api/cloud/zookeeper")
public class CloudZookeeperProviderController {

    /**
     * 服务端口
     */
    @Value("${server.port: 6666}")
    private String serverPort;

    @RequestMapping(value = "/hello", method = {RequestMethod.GET, RequestMethod.POST},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> hello(@RequestParam("name") String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hello !");
        stringBuilder.append(name).append("\n");
        stringBuilder.append("server port : ").append(serverPort).append("\n");
        stringBuilder.append("server timestamp: ").append(System.currentTimeMillis());

        System.out.println(new Date() + "-" + stringBuilder.toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(stringBuilder.toString(), headers, HttpStatus.OK);
    }
}
