package com.ljq.demo.springboot.websocketspring.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: websocket 会话管理
 * @Author: junqiang.lu
 * @Date: 2020/3/19
 */
@Slf4j
public class SocketSessionManager {

    /**
     * websocket 会话池
     */
    private static ConcurrentHashMap<String, WebSocketSession> webSocketSessionMap = new ConcurrentHashMap<>();

    /**
     * 添加 websocket 会话
     *
     * @param key
     * @param session
     */
    public static void add(String key, WebSocketSession session) {
        webSocketSessionMap.put(key, session);
    }

    /**
     * 移除 websocket 会话,并将该会话内容返回
     *
     * @param key
     * @return
     */
    public static WebSocketSession remove(String key) {
        return webSocketSessionMap.remove(key);
    }

    /**
     * 删除 websocket,并关闭连接
     *
     * @param key
     */
    public static void removeAndClose(String key) {
        WebSocketSession session = remove(key);
        if (session != null) {
            try {
                session.close();
            } catch (IOException e) {
                log.error("Websocket session close exception ",e);
            }
        }
    }

    /**
     * 获取 websocket 会话
     *
     * @param key
     * @return
     */
    public static WebSocketSession get(String key) {
        return webSocketSessionMap.get(key);
    }

    /**
     * 获取会话数量
     *
     * @return
     */
    public static int count() {
        return webSocketSessionMap.size();
    }




}
