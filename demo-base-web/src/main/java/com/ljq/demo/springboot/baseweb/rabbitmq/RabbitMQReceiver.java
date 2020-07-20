package com.ljq.demo.springboot.baseweb.rabbitmq;

import com.ljq.demo.springboot.baseweb.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: RabbitMQ 消息队列消费者
 * @Author: junqiang.lu
 * @Date: 2019/1/21
 */
@Service
public class RabbitMQReceiver {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQReceiver.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME_DEMO)
    public void receive(String message){
        logger.info("Received < {} >", message);
    }

}
