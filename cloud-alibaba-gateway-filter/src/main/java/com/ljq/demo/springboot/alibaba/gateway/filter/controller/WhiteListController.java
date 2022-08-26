package com.ljq.demo.springboot.alibaba.gateway.filter.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljq.demo.springboot.alibaba.gateway.filter.common.api.ApiResult;
import com.ljq.demo.springboot.alibaba.gateway.filter.model.*;
import com.ljq.demo.springboot.alibaba.gateway.filter.service.WhiteListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 网关路由白名单控制器
 * @Author: junqiang.lu
 * @Date: 2022/8/23
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/gateway/whitelist")
public class WhiteListController {

    @Autowired
    private WhiteListService whiteListService;

    /**
     * 新增白名单
     *
     * @param addParam
     * @return
     */
    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResult<WhiteListEntity>> add(@RequestBody @Validated WhiteListAddParam addParam) {
        log.info("/add,新增白名单参数: {}", addParam);
        return ResponseEntity.ok(whiteListService.add(addParam));
    }

    /**
     * 查询单条白名单
     *
     * @param infoParam
     * @return
     */
    @GetMapping(value = "/info", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResult<WhiteListEntity>> info(@Validated WhiteListInfoParam infoParam) {
        log.info("/info,查询单条白名单参数: {}", infoParam);
        return ResponseEntity.ok(whiteListService.info(infoParam));
    }

    /**
     * 查询单条白名单
     *
     * @param pageParam
     * @return
     */
    @GetMapping(value = "/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResult<IPage<WhiteListEntity>>> page(@Validated WhiteListPageParam pageParam) {
        log.info("/page,分页查询白名单参数: {}", pageParam);
        return ResponseEntity.ok(whiteListService.page(pageParam));
    }

    /**
     * 修改白名单
     *
     * @param updateParam
     * @return
     */
    @PutMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResult<Boolean>> update(@RequestBody @Validated WhiteListUpdateParam updateParam) {
        log.info("/update,修改白名单参数: {}", updateParam);
        return ResponseEntity.ok(whiteListService.update(updateParam));
    }

    /**
     * 删除单条白名单
     *
     * @param deleteParam
     * @return
     */
    @DeleteMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResult<Boolean>> delete(@RequestBody @Validated WhiteListDeleteParam deleteParam) {
        log.info("/delete,删除单条白名单参数: {}", deleteParam);
        return ResponseEntity.ok(whiteListService.delete(deleteParam));
    }



}
