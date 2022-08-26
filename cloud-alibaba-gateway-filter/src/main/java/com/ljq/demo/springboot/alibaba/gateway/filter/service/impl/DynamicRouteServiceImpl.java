package com.ljq.demo.springboot.alibaba.gateway.filter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljq.demo.springboot.alibaba.gateway.filter.dao.RouteMapper;
import com.ljq.demo.springboot.alibaba.gateway.filter.model.RouteDeleteParam;
import com.ljq.demo.springboot.alibaba.gateway.filter.model.RouteEntity;
import com.ljq.demo.springboot.alibaba.gateway.filter.repository.DbRouteRepository;
import com.ljq.demo.springboot.alibaba.gateway.filter.service.DynamicRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @Description: 动态路由业务实现类
 * @Author: junqiang.lu
 * @Date: 2021/10/19
 */
@Slf4j
@Service
public class DynamicRouteServiceImpl extends ServiceImpl<RouteMapper, RouteEntity> implements DynamicRouteService,
        ApplicationEventPublisherAware {

    @Autowired
    private DbRouteRepository dbRouteRepository;

    private ApplicationEventPublisher applicationEventPublisher;


    /**
     * 查询启用的路由列表
     *
     * @return
     */
    @Override
    public List<RouteDefinition> listActive() {
        Flux<RouteDefinition> routeDefinitionFlux = dbRouteRepository.getRouteDefinitions();
        if (Objects.isNull(routeDefinitionFlux)) {
            return Collections.emptyList();
        }
        List<RouteDefinition> routeDefinitionList = new ArrayList<>();
        routeDefinitionFlux.collectList().subscribe(routeDefinitionList::addAll);
        return routeDefinitionList;
    }

    /**
     * 新增路由
     *
     * @param routeDefinition
     */
    @Override
    public void add(RouteDefinition routeDefinition) {
        dbRouteRepository.save(Mono.just(routeDefinition)).subscribe();
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }

    /**
     * 修改路由
     *
     * @param routeDefinition
     */
    @Override
    public void update(RouteDefinition routeDefinition) {
        dbRouteRepository.delete(Mono.just(routeDefinition.getId())).subscribe();
        dbRouteRepository.save(Mono.just(routeDefinition)).subscribe();
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }

    /**
     * 删除路由
     *
     * @param routeDeleteParam
     */
    @Override
    public void delete(RouteDeleteParam routeDeleteParam) {
        dbRouteRepository.delete(Mono.just(routeDeleteParam.getRouteId())).subscribe();
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

}
