package com.ljq.demo.springboot.rocketmq.producer.common.rocketmq;

import com.ljq.demo.springboot.rocketmq.producer.common.constant.RocketMQConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: RocketMQ 生产者
 * @Author: junqiang.lu
 * @Date: 2021/12/2
 */
@Slf4j
@Component
public class RocketMQProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 向默认主题发送消息
     *
     * @param message
     */
    public void send(String message) {
        log.info("RocketMQ producer,topic:{}, message:{}", RocketMQConst.TOPIC_DEMO, message);
        rocketMQTemplate.convertAndSend(RocketMQConst.TOPIC_DEMO, message);
    }

    /**
     * 向购物车主题发送消息
     *
     * @param message
     */
    public void sendCart(String message) {
        log.info("RocketMQ producer,topic:{}, message:{}", RocketMQConst.TOPIC_CART, message);
        rocketMQTemplate.convertAndSend(RocketMQConst.TOPIC_CART, message);
    }



}
