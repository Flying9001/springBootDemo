package com.ljq.springboot.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.config.EnableElasticsearchAuditing;

/**
 * @author ls-ljq
 */
@EnableElasticsearchAuditing
@SpringBootApplication(scanBasePackages = "com.ljq.springboot.elasticsearch")
public class DemoElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoElasticsearchApplication.class, args);
    }

}
