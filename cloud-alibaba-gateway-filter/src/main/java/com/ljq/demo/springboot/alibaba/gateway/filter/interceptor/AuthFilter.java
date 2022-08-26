package com.ljq.demo.springboot.alibaba.gateway.filter.interceptor;

import cn.hutool.json.JSONUtil;
import com.ljq.demo.springboot.alibaba.gateway.filter.common.api.ApiResult;
import com.ljq.demo.springboot.alibaba.gateway.filter.common.component.RedisComponent;
import com.ljq.demo.springboot.alibaba.gateway.filter.common.constant.RedisKeyConst;
import com.ljq.demo.springboot.alibaba.gateway.filter.model.WhiteListEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.nio.charset.StandardCharsets;
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

    @Autowired
    private RedisComponent redisComponent;

    /**
     * 权限过滤
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestPath = exchange.getRequest().getPath().value();
        log.info("requestPath: {}",requestPath);
        // 判断是否符合白名单
        if (validateWhiteList(requestPath)) {
            return chain.filter(exchange);
        }
        List<String> tokenList = exchange.getRequest().getHeaders().get(TOKEN_KEY);
        log.info("token: {}", tokenList);
        if (CollectionUtils.isEmpty(tokenList) || tokenList.get(0).trim().isEmpty()) {
            ServerHttpResponse response = exchange.getResponse();
            // 错误信息
            byte[] data = JSONUtil.toJsonStr(ApiResult.fail("Token is null")).getBytes(StandardCharsets.UTF_8);
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

    /**
     * 请求路径
     *
     * @param requestPath
     * @return
     */
    public boolean validateWhiteList(String requestPath) {
        List<WhiteListEntity> whiteListList = redisComponent.mapGetAll(RedisKeyConst.KEY_GATEWAY_WHITE_LIST,
                WhiteListEntity.class);
        for (WhiteListEntity whiteList : whiteListList) {
            if (requestPath.contains(whiteList.getPath()) || requestPath.matches(whiteList.getPath())) {
                return true;
            }
        }
        return false;
    }
}
