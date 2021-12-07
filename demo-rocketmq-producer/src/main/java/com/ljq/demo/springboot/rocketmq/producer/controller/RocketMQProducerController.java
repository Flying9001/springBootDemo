package com.ljq.demo.springboot.rocketmq.producer.controller;

import com.ljq.demo.springboot.rocketmq.producer.common.rocketmq.RocketMQProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
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

    /**
     * 发送同步消息
     *
     * @param message
     * @return
     */
    @GetMapping(value = "/send/sync")
    public ResponseEntity<?> sendSync(String message) {
        log.info("request param: {}", message);
        SendResult sendResult = rocketMQProducer.sendSync(message);
        log.info("sendResult: {}", sendResult);
        return ResponseEntity.ok(message);
    }

    /**
     * 发送异步消息
     *
     * @param message
     * @return
     */
    @GetMapping(value = "/send/async")
    public ResponseEntity<?> sendAsync(String message) {
        log.info("request param: {}", message);
        rocketMQProducer.sendAsync(message);
        return ResponseEntity.ok(message);
    }

    /**
     * 发送事物消息
     *
     * @param message
     * @return
     */
    @GetMapping(value = "/send/transaction")
    public ResponseEntity<?> sendTransaction(String message) {
        log.info("request param: {}", message);
        TransactionSendResult sendResult = rocketMQProducer.sendTransaction(message);
        log.info("transactionSendResult: {}", sendResult);
        return ResponseEntity.ok(message);
    }

}
