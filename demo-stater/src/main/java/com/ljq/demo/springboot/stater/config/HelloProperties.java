package com.ljq.demo.springboot.stater.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: 自定义 Spring Boot stater 示例配置信息
 * @Author: junqiang.lu
 * @Date: 2020/10/26
 */
@ConfigurationProperties(prefix = "hello-stater")
public class HelloProperties {

    private static final String DEFAULT_NAME = "helloWorld";

    private String name = DEFAULT_NAME;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
