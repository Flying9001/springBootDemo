package com.ljq.demo.springboot.baseweb.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: tabbitmq 配置信息
 * @Author: junqiang.lu
 * @Date: 2019/1/21
 */
@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME_DEMO = "rabbitmq_spring_boot_demo";

    @Bean
    public Queue queue(){
        return new Queue(QUEUE_NAME_DEMO);
    }


}
