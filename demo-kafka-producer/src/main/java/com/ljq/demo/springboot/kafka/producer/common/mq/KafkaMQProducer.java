package com.ljq.demo.springboot.kafka.producer.common.mq;

import cn.hutool.json.JSONUtil;
import com.ljq.demo.springboot.kafka.producer.common.constant.KafkaMessageConst;
import com.ljq.demo.springboot.kafka.producer.model.entity.KafkaMessageEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description: Kafka 消息生产者
 * @Author: junqiang.lu
 * @Date: 2022/2/24
 */
@Slf4j
@Component
public class KafkaMQProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 发送消息
     *
     * @param msg
     */
    public void send(String msg) {
        KafkaMessageEntity kafkaMessage = new KafkaMessageEntity();
        kafkaMessage.setId("message-" + System.currentTimeMillis());
        kafkaMessage.setMessage(msg);
        log.info("kafka message: {}", JSONUtil.toJsonStr(kafkaMessage));
        kafkaTemplate.send(KafkaMessageConst.KAFKA_TOPIC_DEMO, JSONUtil.toJsonStr(kafkaMessage));
    }



}
