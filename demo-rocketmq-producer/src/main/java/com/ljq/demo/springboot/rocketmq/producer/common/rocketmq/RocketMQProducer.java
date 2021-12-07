package com.ljq.demo.springboot.rocketmq.producer.common.rocketmq;

import com.ljq.demo.springboot.rocketmq.producer.common.constant.RocketMQConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

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

    /**
     * 发送同步消息
     *
     * @param message
     * @return
     */
    public SendResult sendSync(String message) {
        log.info("RocketMQ producer, sync message, topic: {}, message: {}", RocketMQConst.TOPIC_SYNC, message);
        return rocketMQTemplate.syncSend(RocketMQConst.TOPIC_SYNC, message);
    }

    /**
     * 发送异步消息
     *
     * @param message
     */
    public void sendAsync(String message) {
        log.info("RocketMQ producer, async message, topic: {}, message: {}", RocketMQConst.TOPIC_ASYNC, message);
        rocketMQTemplate.asyncSend(RocketMQConst.TOPIC_ASYNC, message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("async message send success !!! \n topic: {}, message: {}, result: {}",
                        RocketMQConst.TOPIC_ASYNC, message, sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                log.info("async message send fail ... \n topic: {}, message: {}, error: {}", RocketMQConst.TOPIC_ASYNC,
                        message, throwable.getMessage());
            }
        });
    }

    /**
     * 发送事务消息
     *
     * @param message
     */
    public TransactionSendResult sendTransaction(String message) {
        String transactionId = UUID.randomUUID().toString();
        log.info("{RocketMQ producer, transaction message, topic: {}, message: {}, transactionId: {}}",
                RocketMQConst.TOPIC_TRANSACTION, message, transactionId);
        Message<String> msg = MessageBuilder.withPayload(message).setHeader(RocketMQHeaders.TRANSACTION_ID,
                transactionId).build();
        return rocketMQTemplate.sendMessageInTransaction(RocketMQConst.TOPIC_TRANSACTION, msg, message);
    }



}
