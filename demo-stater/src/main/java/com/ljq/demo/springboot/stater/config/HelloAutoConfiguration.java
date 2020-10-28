package com.ljq.demo.springboot.stater.config;

import com.ljq.demo.springboot.stater.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 自定义 Spring Boot Stater 示例配置自动化配置
 * @Author: junqiang.lu
 * @Date: 2020/10/26
 */
@Configuration
@ConditionalOnClass(value = {HelloService.class})
@EnableConfigurationProperties(value = HelloProperties.class)
public class HelloAutoConfiguration {

    @Autowired
    private HelloProperties helloProperties;

    @Bean
    @ConditionalOnMissingBean(HelloService.class)
    public HelloService helloService() {
        HelloService helloService = new HelloService();
        helloService.setName(helloProperties.getName());
        return helloService;
    }
}
