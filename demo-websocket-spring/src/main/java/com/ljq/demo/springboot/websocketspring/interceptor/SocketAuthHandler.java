package com.ljq.demo.springboot.websocketspring.interceptor;

import com.ljq.demo.springboot.websocketspring.web.SocketSessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @Description: websocket 连接拦截器
 * @Author: junqiang.lu
 * @Date: 2020/3/19
 */
@Slf4j
@Component
public class SocketAuthHandler extends TextWebSocketHandler {

    private static final String TOKEN_FIELD = "token";

    /**
     * 握手成功之后
     *
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Object token = session.getAttributes().get(TOKEN_FIELD);
        if (Objects.nonNull(token)) {
            // 用户连接成功,缓存用户会话
            log.debug("用户[ {} ]创建连接");
            SocketSessionManager.add(String.valueOf(token), session);
        } else {
            throw new RuntimeException("用户登录已失效");
        }
    }

    /**
     * 接收客户端消息
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 读取客户端消息
        Object token = session.getAttributes().get(TOKEN_FIELD);
        String payload = message.getPayload();
        log.debug("收到用户 [{}] 的消息,消息内容为: {}",token, payload);

        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder.append("服务端已接收到用户 [").append(token).append("] 的消息,消息内容为:");
        responseBuilder.append(payload).append(",当前服务器时间: ");
        responseBuilder.append(LocalDateTime.now());

        session.sendMessage(new TextMessage(responseBuilder.toString()));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Object token = session.getAttributes().get(TOKEN_FIELD);
        if (Objects.nonNull(token)) {
            log.debug("用户 [{}] 断开连接");
            SocketSessionManager.remove(String.valueOf(token));
        }
    }
}
