package com.ljq.demo.pringboot.alibaba.server.provider.controller;

import com.ljq.demo.pringboot.alibaba.server.provider.model.param.HelloParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: nacos 服务提供者控制层
 * @Author: junqiang.lu
 * @Date: 2020/12/1
 */
@Slf4j
@RestController
@RequestMapping("/api/nacos")
public class NacosProviderController {

    @Value("${server.port}")
    private Integer port;

    /**
     * hello 方法
     *
     * @param helloParam
     * @return
     */
    @GetMapping(value = "/hello", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> sayHello(HelloParam helloParam) {
        String result = "Hello," + helloParam.getName();
        log.info("serverPort: {}", port);
        log.info("result: {}", result);
        return ResponseEntity.ok(result);
    }

    /**
     * 回复方法
     *
     * @param helloParam
     * @return
     */
    @PostMapping(value = "/replay", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> replay(@RequestBody HelloParam helloParam) {
        String result = "Hi," + helloParam.getName() + ",I'm fine,Thank you.";
        log.info("result: {}", result);
        return ResponseEntity.ok(result);
    }



}
