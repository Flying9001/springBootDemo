package com.ljq.demo.springboot.baseweb.rabbitmq;

import com.ljq.demo.springboot.baseweb.config.RabbitMQConfig;
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

    /**
     * 使用简单模式发送消息
     * (使用默认的路由键,默认的交换机)
     *
     * @param message 消息
     */
    public void send(String message){
        logger.info("sent by RabbitMQ ... ...{}",message);
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME_DEMO, "Hello world ---RabbitMQ demo" + message);
    }

    /**
     * 使用直连交换机向 demo key 发送消息
     * 交换机类型: {@link org.springframework.amqp.core.DirectExchange}
     * 交换机名称: {@link RabbitMQConfig#DIRECT_EXCHANGE_NAME_DEMO},
     * 生产者路由键: {@link RabbitMQConfig#QUEUE_SENDER_ROUTING_KEY_DEMO}
     * @param message
     */
    public void sendDirectDemo(String message) {
        logger.info("exchangeName = {}, queue sender outing key = {}, message = {}",
                RabbitMQConfig.DIRECT_EXCHANGE_NAME_DEMO, RabbitMQConfig.QUEUE_SENDER_ROUTING_KEY_DEMO, message);
        rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE_NAME_DEMO,
                RabbitMQConfig.QUEUE_SENDER_ROUTING_KEY_DEMO, message);
    }

    /**
     * 使用主题交换机向 demo key 发送消息
     * 交换机类型: {@link org.springframework.amqp.core.TopicExchange}
     * 交换机名称: {@link RabbitMQConfig#TOPIC_EXCHANGE_NAME_DEMO}
     * 生产者路由键: {@link RabbitMQConfig#QUEUE_SENDER_ROUTING_KEY_DEMO}
     * @param message
     */
    public void sendTopicDemo(String message) {
        logger.info("exchangeName = {}, queue sender outing key = {}, message = {}",
                RabbitMQConfig.TOPIC_EXCHANGE_NAME_DEMO, RabbitMQConfig.QUEUE_SENDER_ROUTING_KEY_DEMO, message);
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_NAME_DEMO,
                RabbitMQConfig.QUEUE_SENDER_ROUTING_KEY_DEMO, message);
    }

    /**
     * 使用主题交换机向 demo2 key 发送消息
     * 交换机类型: {@link org.springframework.amqp.core.TopicExchange}
     * 交换机名称: {@link RabbitMQConfig#TOPIC_EXCHANGE_NAME_DEMO}
     * 生产者路由键: {@link RabbitMQConfig#QUEUE_SENDER_ROUTING_KEY_DEMO_2}
     * @param message
     */
    public void sendTopicDemo2(String message) {
        logger.info("exchangeName = {}, queue sender outing key = {}, message = {}",
                RabbitMQConfig.TOPIC_EXCHANGE_NAME_DEMO, RabbitMQConfig.QUEUE_SENDER_ROUTING_KEY_DEMO_2, message);
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_NAME_DEMO,
                RabbitMQConfig.QUEUE_SENDER_ROUTING_KEY_DEMO_2, message);
    }

    /**
     * 使用主题交换机向 api user key 发送消息
     * 交换机类型: {@link org.springframework.amqp.core.TopicExchange}
     * 交换机名称: {@link RabbitMQConfig#TOPIC_EXCHANGE_NAME_API}
     * 生产者路由键: {@link RabbitMQConfig#QUEUE_SENDER_ROUTING_KEY_API_USER}
     * @param message
     */
    public void sendTopicApiUser(String message) {
        logger.info("exchangeName = {}, queue sender outing key = {}, message = {}",
                RabbitMQConfig.TOPIC_EXCHANGE_NAME_API, RabbitMQConfig.QUEUE_SENDER_ROUTING_KEY_API_USER, message);
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_NAME_API,
                RabbitMQConfig.QUEUE_SENDER_ROUTING_KEY_API_USER, message);
    }

    /**
     * 使用广播交换机发送消息
     * 交换机类型: {@link org.springframework.amqp.core.FanoutExchange}
     * 交换机名称: {@link RabbitMQConfig#FANOUT_EXCHANGE_NAME_DEMO}
     * @param message
     */
    public void sendFanoutDemo(String message) {
        logger.info("exchangeName = {}, message = {}", RabbitMQConfig.FANOUT_EXCHANGE_NAME_DEMO, message);
        rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE_NAME_DEMO, "", message);
    }



}
