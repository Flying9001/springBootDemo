package com.ljq.demo.springboot.websocket.model.entity;

import lombok.Data;

/**
 * @Description: 聊天信息封装类
 * @Author: junqiang.lu
 * @Date: 2020/3/14
 */
@Data
public class SocketMessage {

    /**
     * 消息发送者
     */
    private String from;
    /**
     * 消息接收者
     */
    private String to;
    /**
     * 消息内容
     */
    private String content;
}