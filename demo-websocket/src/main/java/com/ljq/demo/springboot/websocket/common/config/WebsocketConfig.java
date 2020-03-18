package com.ljq.demo.springboot.websocket.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Description: websocket 配置信息
 * @Author: junqiang.lu
 * @Date: 2020/3/14
 */
@Configuration
@EnableWebSocket
public class WebsocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}