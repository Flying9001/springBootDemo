package com.ljq.demo.springboot.websocket.web;

import com.ljq.demo.springboot.websocket.common.util.SocketDecoder;
import com.ljq.demo.springboot.websocket.common.util.SocketEncoder;
import com.ljq.demo.springboot.websocket.model.entity.SocketMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Description: websocket 服务端站点
 * @Author: junqiang.lu
 * @Date: 2020/3/14
 */
@Slf4j
@Component
@ServerEndpoint(value = "/chat/{username}", encoders = {SocketEncoder.class}, decoders = {SocketDecoder.class})
public class ChatEndPoint {

    private Session session;

    private static Set<ChatEndPoint> chatEndPoints = new CopyOnWriteArraySet<>();

    private static Map<String, String> users = new HashMap<>();

    /**
     * 打开 socket 连接
     *
     * @param session
     * @param username
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        log.debug("Websocket is opening ");

        this.session = session;
        chatEndPoints.add(this);
        users.put(session.getId(), username);
        log.debug("username: {}", username);

        SocketMessage message = new SocketMessage();
        message.setFrom(username);
        message.setContent("Connected !!!, online user: " + users.size());
        broadcast(message);
        log.debug("online user: {}", users.size());
    }

    /**
     * 发送信息
     *
     * @param session
     * @param message
     */
    @OnMessage
    public void onMessage(Session session, SocketMessage message) {
        message.setFrom(users.getOrDefault(session.getId(), ""));
        log.debug("message: {}", message.toString());

        broadcast(message);
        log.debug("online user: {}", users.size());
    }

    /**
     * 关闭 socket 连接
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        log.debug("Socket is closing .");
        log.debug("username: {}", users.getOrDefault(session.getId(), ""));

        chatEndPoints.remove(this);
        users.remove(session.getId());

        SocketMessage message = new SocketMessage();
        message.setFrom(users.getOrDefault(session.getId(), ""));
        message.setContent("Socket disconnected .");
        broadcast(message);
        log.debug("online user: {}", users.size());
    }

    /**
     * 异常
     *
     * @param session
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("Socket exception", throwable);
        log.debug("online user: {}", users.size());
    }

    /**
     * 信息发送
     *
     * @param message
     */
    private static void broadcast(SocketMessage message) {
        chatEndPoints.forEach(chatEndPoint -> {
            synchronized (chatEndPoint) {
                try {
                    chatEndPoint.session.getBasicRemote().sendObject(message);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}