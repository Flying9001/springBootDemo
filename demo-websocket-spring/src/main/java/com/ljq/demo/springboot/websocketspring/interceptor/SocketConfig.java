package com.ljq.demo.springboot.websocketspring.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @Description: websocket 拦截器配置
 * @Author: junqiang.lu
 * @Date: 2020/3/19
 */
@Configuration
@EnableWebSocket
public class SocketConfig implements WebSocketConfigurer {

    @Autowired
    private SocketAuthHandler socketAuthHandler;
    @Autowired
    private SocketInterceptor socketInterceptor;

    private static final String WEB_SOCKET_PATH = "socketSpring";

    /**
     *
     * @param webSocketHandlerRegistry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(socketAuthHandler, WEB_SOCKET_PATH)
                .addInterceptors(socketInterceptor)
                .setAllowedOrigins("*");
    }
}
