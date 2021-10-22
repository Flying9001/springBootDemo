package com.ljq.demo.springboot.alibaba.gateway.filter.controller;

import com.ljq.demo.springboot.alibaba.gateway.filter.common.api.ApiResult;
import com.ljq.demo.springboot.alibaba.gateway.filter.model.RouteDeleteParam;
import com.ljq.demo.springboot.alibaba.gateway.filter.service.DynamicRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 动态路由控制层
 * @Author: junqiang.lu
 * @Date: 2021/10/19
 */
@Slf4j
@RestController
@RequestMapping("/api/gateway/route")
public class DynamicRouteController {

    @Autowired
    private DynamicRouteService routeService;

    /**
     * 查询启用的路由列表
     *
     * @return
     */
    @GetMapping(value = "/list/active", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResult<List<RouteDefinition>>> listActive() {
        log.info("/list/active");
        return ResponseEntity.ok(ApiResult.success(routeService.listActive()));
    }

    /**
     * 新增路由
     *
     * @param routeDefinition
     * @return
     */
    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResult<RouteDefinition>> add(@RequestBody @Validated RouteDefinition routeDefinition) {
        log.info("/add,新增路由参数: {}", routeDefinition);
        routeService.add(routeDefinition);
        return ResponseEntity.ok(ApiResult.success(routeDefinition));
    }

    /**
     * 更新路由
     *
     * @param routeDefinition
     * @return
     */
    @PutMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResult<RouteDefinition>> update(@RequestBody @Validated RouteDefinition routeDefinition) {
        log.info("/update,新增路由参数: {}", routeDefinition);
        routeService.update(routeDefinition);
        return ResponseEntity.ok(ApiResult.success(routeDefinition));
    }

    /**
     * 删除路由
     *
     * @param routeDeleteParam
     * @return
     */
    @DeleteMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResult<RouteDeleteParam>> delete(@RequestBody RouteDeleteParam routeDeleteParam) {
        log.info("/delete,删除路由参数: {}", routeDeleteParam);
        routeService.delete(routeDeleteParam);
        return ResponseEntity.ok(ApiResult.success(routeDeleteParam));
    }



}
