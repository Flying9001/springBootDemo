package com.ljq.demo.springboot.common.rabbitmq;

import com.ljq.demo.springboot.common.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: rabbitMQ 消息发送者
 * @Author: junqiang.lu
 * @Date: 2019/1/21
 */
@Service
public class RabbitMQSender {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQSender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(){
        logger.info("sent by RabbitMQ ... ...");
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME_DEMO, "Hello world ---RabbitMQ demo");
    }



}
