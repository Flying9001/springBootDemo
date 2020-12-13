package com.ljq.demo.springboot.alibaba.config.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: Spring Cloud Alibaba Nacos 分布式配置中心控制层
 * @Author: junqiang.lu
 * @Date: 2020/12/9
 */
@Slf4j
@RestController
@RefreshScope
@RequestMapping("/api/cloud/alibaba/config")
public class NacosConfigController {

    /**
     * 用户名
     */
    @Value("${userInfo.name: default}")
    private String userName;
    /**
     * 用户年龄
     */
    @Value("${userInfo.age: 0}")
    private Integer userAge;
    /**
     * 用户手机号
     */
    @Value("${userInfo.phone: default}")
    private String userPhone;

    /**
     * 用户信息
     *
     * @return
     */
    @GetMapping(value = "/user", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> queryUserInfo() {
        StringBuilder userBuilder = new StringBuilder();
        userBuilder.append("用户名:").append(userName)
                .append(",年龄:").append(userAge)
                .append(",手机号:").append(userPhone);
        log.info("@Value 用户信息: {}", userBuilder.toString());

        return ResponseEntity.ok(userBuilder.toString());
    }


}
