package com.ljq.demo.springboot.websocket.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljq.demo.springboot.websocket.model.entity.SocketMessage;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * @Description: websocket 消息解码器
 * @Author: junqiang.lu
 * @Date: 2020/3/14
 */
public class SocketDecoder implements Decoder.Text<SocketMessage> {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public SocketMessage decode(String s) throws DecodeException {

        try {
            return objectMapper.readValue(s, SocketMessage.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}