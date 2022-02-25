package com.ljq.demo.springboot.kafka.producer.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: Kafka 消息实体类
 * @Author: junqiang.lu
 * @Date: 2022/2/24
 */
@Data
public class KafkaMessageEntity implements Serializable {

    private static final long serialVersionUID = -3812375964256200394L;

    private String id;

    private String message;

}
