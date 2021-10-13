package com.ljq.demo.springboot.baseweb.rabbitmq;

import com.ljq.demo.springboot.baseweb.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Description: RabbitMQ 消息队列消费者
 * @Author: junqiang.lu
 * @Date: 2019/1/21
 */
@Service
public class RabbitMQReceiver {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQReceiver.class);

    /**
     * 消息接收
     *
     * @param message
     */
    @RabbitHandler
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME_DEMO)
    public void receiveDemo(String message){
        logger.info("Received queueName = {}, message = {}",RabbitMQConfig.QUEUE_NAME_DEMO, message);
    }

    /**
     * 消息接收
     *
     * @param message
     */
    @RabbitHandler
    @RabbitListener(queues = {RabbitMQConfig.QUEUE_NAME_API})
    public void receiveApi(String message) {
        logger.info("Received queueName = {}, message = {}",RabbitMQConfig.QUEUE_NAME_API, message);
    }

    /**
     * 消息接收
     *
     * @param message
     */
    @RabbitHandler
    @RabbitListener(queues = {RabbitMQConfig.QUEUE_NAME_DELAY_CART})
    public void receiveDelayCart(String message) {
        logger.info("Received queueName = {}, message = {}",RabbitMQConfig.QUEUE_NAME_DELAY_CART, message);
    }





}
