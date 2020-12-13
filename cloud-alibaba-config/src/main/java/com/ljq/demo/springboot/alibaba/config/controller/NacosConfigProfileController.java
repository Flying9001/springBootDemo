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
 * @Description: Spring Cloud Alibaba Config 多环境测试控制层
 * @Author: junqiang.lu
 * @Date: 2020/12/13
 */
@Slf4j
@RestController
@RefreshScope
@RequestMapping("/api/cloud/alibaba/config")
public class NacosConfigProfileController {

    /**
     * 用户手机号
     */
    @Value("${currentProfile: default}")
    private String currentProfile;

    /**
     * 当前环境
     *
     * @return
     */
    @GetMapping(value = "/profile", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> queryUserInfo() {
        log.info("当前环境: {}", this.currentProfile);
        return ResponseEntity.ok(this.currentProfile);
    }

}
