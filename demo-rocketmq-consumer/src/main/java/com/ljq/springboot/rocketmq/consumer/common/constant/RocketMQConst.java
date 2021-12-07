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
    public static final String GROUP_CONSUMER_SYNC = "rocketmq-consumer-group-sync";
    public static final String GROUP_CONSUMER_ASYNC = "rocketmq-consumer-group-async";
    public static final String GROUP_CONSUMER_TRANSACTION = "rocketmq-consumer-group-transaction";


    /**
     * 消息主题
     */
    public static final String TOPIC_DEMO = "rocketmq_topic_demo";
    public static final String TOPIC_CART = "rocketmq_topic_cart";
    public static final String TOPIC_SYNC = "rocketmq_topic_sync";
    public static final String TOPIC_ASYNC = "rocketmq_topic_async";
    public static final String TOPIC_TRANSACTION = "rocketmq_topic_transaction";



}
