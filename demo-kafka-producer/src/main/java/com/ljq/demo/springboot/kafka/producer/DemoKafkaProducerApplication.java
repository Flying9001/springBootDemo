package com.ljq.demo.springboot.kafka.producer;

import com.ljq.demo.springboot.kafka.producer.common.constant.KafkaMessageConst;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

/**
 * @author ls-ljq
 */
@SpringBootApplication
public class DemoKafkaProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoKafkaProducerApplication.class, args);
    }


    @Bean
    public KafkaAdmin.NewTopics topics() {
        return new KafkaAdmin.NewTopics(TopicBuilder.name(KafkaMessageConst.KAFKA_TOPIC_DEMO).build());
    }
}
