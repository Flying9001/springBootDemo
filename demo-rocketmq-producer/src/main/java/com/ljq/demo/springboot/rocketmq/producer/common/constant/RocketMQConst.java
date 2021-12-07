package com.ljq.demo.springboot.rocketmq.producer.common.constant;

/**
 * @Description: RocketMQ 常量
 * @Author: junqiang.lu
 * @Date: 2021/12/2
 */
public class RocketMQConst {

    private RocketMQConst() {
    }

    /**
     * 消息主题
     */
    public static final String TOPIC_DEMO = "rocketmq_topic_demo";
    public static final String TOPIC_CART = "rocketmq_topic_cart";
    public static final String TOPIC_SYNC = "rocketmq_topic_sync";
    public static final String TOPIC_ASYNC = "rocketmq_topic_async";
    public static final String TOPIC_TRANSACTION = "rocketmq_topic_transaction";

    /**
     * 事务状态
     */
    public static final String TRANSACTION_STATUS_SUCCESS = "success";
    public static final String TRANSACTION_STATUS_FAIL = "fail";





}
