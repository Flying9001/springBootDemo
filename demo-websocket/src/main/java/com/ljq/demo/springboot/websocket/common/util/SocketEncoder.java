package com.ljq.demo.springboot.websocket.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljq.demo.springboot.websocket.model.entity.SocketMessage;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @Description: socket 消息编码器
 * @Author: junqiang.lu
 * @Date: 2020/3/14
 */
public class SocketEncoder implements Encoder.Text<SocketMessage> {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String encode(SocketMessage socketMessage) throws EncodeException {
        try {
            return objectMapper.writeValueAsString(socketMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}