package com.ljq.demo.springboot.alibaba.gateway.filter.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljq.demo.springboot.alibaba.gateway.filter.common.api.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @Description: 鉴权拦截器
 * @Author: junqiang.lu
 * @Date: 2020/12/8
 */
@Slf4j
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    private static final String TOKEN_KEY = "token";

    /**
     * 权限过滤
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("requestPath: {}",exchange.getRequest().getPath());
        List<String> tokenList = exchange.getRequest().getHeaders().get(TOKEN_KEY);
        log.info("token: {}", tokenList);
        if (CollectionUtils.isEmpty(tokenList) || tokenList.get(0).trim().isEmpty()) {
            ServerHttpResponse response = exchange.getResponse();
            // 错误信息
            byte[] data = new byte[0];
            try {
                data = new ObjectMapper().writeValueAsBytes(ApiResult.fail("Token is null"));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            DataBuffer buffer = response.bufferFactory().wrap(data);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            return response.writeWith(Mono.just(buffer));
        }
        return chain.filter(exchange);
    }

    /**
     * 设置执行级别
     *
     * @return
     */
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
