package com.ljq.demo.springboot.alibaba.server.consumer.controller;

import com.ljq.demo.springboot.alibaba.server.consumer.model.param.HelloParam;
import com.ljq.demo.springboot.alibaba.server.consumer.service.NacosConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: Nacos 服务消费者控制层
 * @Author: junqiang.lu
 * @Date: 2020/12/1
 */
@Slf4j
@RestController
@RequestMapping("/api/nacos/consumer")
public class NacosConsumerController {

    @Autowired
    private NacosConsumerService consumerService;
    @GetMapping(value = "/hello", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> sayHello(HelloParam helloParam) {
        String result = consumerService.hello(helloParam);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/replay", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> replay(@RequestBody HelloParam helloParam) {
        String result = consumerService.replay(helloParam);
        return ResponseEntity.ok(result);
    }
}
