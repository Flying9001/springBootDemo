package com.ljq.springboot.rocketmq.consumer.common.constant;

/**
 * @Description: RocketMQ 常量
 * @Author: junqiang.lu
 * @Date: 2021/12/2
 */
public class RocketMQConst {

    private RocketMQConst() {
    }

    /**
     * 消费者分组
     */
    public static final String GROUP_CONSUMER_DEMO = "rocketmq-consumer-group-demo";
    public static final String GROUP_CONSUMER_CART = "rocketmq-consumer-group-cart";

    /**
     * 消息主题
     */
    public static final String TOPIC_DEMO = "rocketmq_topic_demo";
    public static final String TOPIC_CART = "rocketmq_topic_cart";


}
