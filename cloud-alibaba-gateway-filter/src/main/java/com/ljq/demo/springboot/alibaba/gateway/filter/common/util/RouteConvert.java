package com.ljq.demo.springboot.alibaba.gateway.filter.common.util;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.ljq.demo.springboot.alibaba.gateway.filter.model.RouteEntity;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.net.URI;
import java.util.Map;

/**
 * @Description: 路由器转换工具类
 * @Author: junqiang.lu
 * @Date: 2021/10/21
 */
public class RouteConvert {

    private RouteConvert(){
    }

    /**
     * 转换为 RouteDefinition
     *
     * @param routeEntity
     * @return
     */
    public static RouteDefinition toRouteDefinition(RouteEntity routeEntity) {
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(routeEntity.getRouteId());
        routeDefinition.setOrder(routeEntity.getFilterOrder());
        routeDefinition.setUri(URI.create(routeEntity.getUri()));
        JSONArray predicateArray = new JSONArray(JSONUtil.toJsonStr(routeEntity.getPredicates()));
        if (!predicateArray.isEmpty()) {
            routeDefinition.setPredicates(predicateArray.toList(PredicateDefinition.class));
        }
        JSONArray filtersArray = new JSONArray(JSONUtil.toJsonStr(routeEntity.getFilters()));
        if (!filtersArray.isEmpty()) {
            routeDefinition.setFilters(filtersArray.toList(FilterDefinition.class));
        }
        routeDefinition.setMetadata(JSONUtil.toBean(routeEntity.getMetadata(), Map.class));
        return routeDefinition;
    }

    /**
     * 转换为路由数据库实体类
     *
     * @param routeDefinition
     * @return
     */
    public static RouteEntity toRouteEntity(RouteDefinition routeDefinition) {
        RouteEntity routeEntity = new RouteEntity();
        routeEntity.setRouteId(routeDefinition.getId());
        routeEntity.setUri(routeDefinition.getUri().toString());
        routeEntity.setPredicates(JSONUtil.toJsonStr(routeDefinition.getPredicates()));
        routeEntity.setFilters(JSONUtil.toJsonStr(routeDefinition.getFilters()));
        routeEntity.setMetadata(JSONUtil.toJsonStr(routeDefinition.getMetadata()));
        routeEntity.setFilterOrder(routeDefinition.getOrder());
        return routeEntity;
    }


}
