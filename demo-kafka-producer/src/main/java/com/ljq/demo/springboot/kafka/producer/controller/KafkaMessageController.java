package com.ljq.demo.springboot.kafka.producer.controller;

import com.ljq.demo.springboot.kafka.producer.common.mq.KafkaMQProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: Kafka 消息控制层
 * @Author: junqiang.lu
 * @Date: 2022/2/25
 */
@Slf4j
@RestController
@RequestMapping("/api/demo/kafka")
public class KafkaMessageController {

    @Autowired
    private KafkaMQProducer kafkaMQProducer;

    /**
     * 发送消息
     *
     * @param message
     * @return
     */
    @PostMapping(value = "/send", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> send(String message) {
        log.info("request path: {}, param: {}", "/api/demo/kafka/send", message);
        kafkaMQProducer.send(message);
        return ResponseEntity.ok(message);
    }


}
