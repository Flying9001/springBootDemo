package com.ljq.demo.springboot.rocketmq.producer.controller;

import com.ljq.demo.springboot.rocketmq.producer.common.rocketmq.RocketMQProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: RocketMQ 生产者控制层
 * @Author: junqiang.lu
 * @Date: 2021/12/2
 */
@Slf4j
@RestController
@RequestMapping("/api/rocketmq/producer")
public class RocketMQProducerController {

    @Autowired
    private RocketMQProducer rocketMQProducer;

    /**
     * 向默认主题发送消息
     *
     * @param message
     * @return
     */
    @GetMapping(value = "/send")
    public ResponseEntity<?> send(String message) {
        log.info("request param: {}", message);
        rocketMQProducer.send(message);
        return ResponseEntity.ok(message);
    }

    /**
     * 向购物车主题发送消息
     *
     * @param message
     * @return
     */
    @GetMapping(value = "/send/cart")
    public ResponseEntity<?> sendCart(String message) {
        log.info("request param: {}", message);
        rocketMQProducer.sendCart(message);
        return ResponseEntity.ok(message);
    }

}
