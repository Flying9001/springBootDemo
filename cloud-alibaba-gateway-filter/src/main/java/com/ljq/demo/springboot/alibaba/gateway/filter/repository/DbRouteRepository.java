package com.ljq.demo.springboot.alibaba.gateway.filter.repository;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ljq.demo.springboot.alibaba.gateway.filter.common.component.RedisComponent;
import com.ljq.demo.springboot.alibaba.gateway.filter.common.constant.RedisKeyConst;
import com.ljq.demo.springboot.alibaba.gateway.filter.common.util.RouteConvert;
import com.ljq.demo.springboot.alibaba.gateway.filter.dao.RouteMapper;
import com.ljq.demo.springboot.alibaba.gateway.filter.model.RouteEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description: 数据库路由持久层
 * @Author: junqiang.lu
 * @Date: 2021/10/19
 */
@Slf4j
@Component
public class DbRouteRepository implements RouteDefinitionRepository {

    @Autowired
    private RouteMapper routeMapper;
    @Autowired
    private RedisComponent redisComponent;

    /**
     * 获取路由信息
     *
     * @return
     */
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<RouteDefinition> routeDefinitionList = redisComponent.mapGetAll(RedisKeyConst.KEY_GATEWAY_ROUTE,
                RouteDefinition.class);
        if (CollUtil.isNotEmpty(routeDefinitionList)) {
            return  Flux.fromIterable(routeDefinitionList);
        }
        List<RouteEntity> routeEntityList = routeMapper.selectList(Wrappers.emptyWrapper());
        if (CollUtil.isEmpty(routeEntityList)) {
            log.info("数据库内没有定义路由");
            return Flux.empty();
        }
        List<RouteDefinition> routeDefinitionDBList = new ArrayList<>();
        routeEntityList.forEach(routeEntity -> {
            routeDefinitionDBList.add(RouteConvert.toRouteDefinition(routeEntity));
        });
        log.info("-------------加载数据库路由规则------------");
        redisComponent.mapPutBatch(RedisKeyConst.KEY_GATEWAY_ROUTE, routeDefinitionDBList.stream()
                .collect(Collectors.toMap(RouteDefinition::getId, Function.identity())));
        return  Flux.fromIterable(routeDefinitionDBList);
    }

    /**
     * 新增路由
     *
     * @param route
     * @return
     */
    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(routeDefinition -> {
            routeMapper.insert(RouteConvert.toRouteEntity(routeDefinition));
            redisComponent.mapPut(RedisKeyConst.KEY_GATEWAY_ROUTE, routeDefinition.getId(), routeDefinition);
            return Mono.empty();
        });
    }

    /**
     * 删除路由
     *
     * @param routeId
     * @return
     */
    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            routeMapper.delete(Wrappers.lambdaQuery(new RouteEntity()).eq(RouteEntity::getRouteId, id));
            redisComponent.mapRemove(RedisKeyConst.KEY_GATEWAY_ROUTE, id);
            return Mono.empty();
        });
    }
}
