package com.ljq.demo.springboot.alibaba.gateway.filter.common.util;

import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class RouteConvertTest {

    @Test
    void createDemoRoute() {
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(RandomUtil.randomString(10));
        routeDefinition.setUri(URI.create("http://127.0.0.1:9000"));
        routeDefinition.setOrder(1);
        // 断言
        PredicateDefinition predicateDefinition = new PredicateDefinition();
        predicateDefinition.setName("Path");
        Map<String, String> predicateArgs = new HashMap<>(8);
        predicateArgs.put("pattern", "/api/**");
        predicateDefinition.setArgs(predicateArgs);
        routeDefinition.setPredicates(Collections.singletonList(predicateDefinition));
        // 过滤器
        FilterDefinition filterDefinition = new FilterDefinition();
        filterDefinition.setName("StripPrefix");
        Map<String, String> filterArgs = new HashMap<>(8);
        filterArgs.put("prefix", "");
        filterDefinition.setArgs(filterArgs);
        routeDefinition.setFilters(Collections.singletonList(filterDefinition));
        System.out.println(routeDefinition);
    }
}