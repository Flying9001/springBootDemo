package com.ljq.demo.springboot.websocketspring.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: websocket 握手拦截器
 * @Author: junqiang.lu
 * @Date: 2020/3/19
 */
@Slf4j
@Component
public class SocketInterceptor implements HandshakeInterceptor {

    private static final String TOKEN_FIELD = "token";

    /**
     * websocket 握手之前
     *
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @param webSocketHandler
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
                                   WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        log.debug("websocket starts handshaking");
        // 获取请求参数
        HashMap<String, String> paramMap = HttpUtil.decodeParamMap(serverHttpRequest.getURI().getQuery(), "utf-8");
        String token = paramMap.get(TOKEN_FIELD);
        if (StrUtil.isNotBlank(token)) {
            map.put("token", token);
            log.debug("用户 [ {} ]握手成功", token);
            return true;
        }
        log.debug("用户登录已失效");
        return false;
    }

    /**
     * websocket 握手之后
     *
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @param webSocketHandler
     * @param e
     */
    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
                               WebSocketHandler webSocketHandler, Exception e) {
        log.debug("握手完成!");
    }
}
