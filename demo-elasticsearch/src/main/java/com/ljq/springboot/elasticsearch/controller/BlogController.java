package com.ljq.springboot.elasticsearch.controller;

import com.ljq.springboot.elasticsearch.common.api.ApiResult;
import com.ljq.springboot.elasticsearch.model.entity.BlogEntity;
import com.ljq.springboot.elasticsearch.model.param.*;
import com.ljq.springboot.elasticsearch.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 博客控制层
 * @Author: junqiang.lu
 * @Date: 2021/12/11
 */
@Slf4j
@Api(value = "博客控制层", tags = "博客控制层")
@RequestMapping(value = "/api/elasticsearch/blog")
@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    /**
     * 新增单条
     * @param addParam
     * @return
     */
    @ApiOperation(value = "新增单条", notes = "新增单条")
    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResult<BlogEntity>> save(@RequestBody @Validated BlogAddParam addParam) {
        log.info("requestParam: {}", addParam);
        return ResponseEntity.ok(blogService.save(addParam));
    }

    /**
     * 查询单条
     * @param queryOneParam
     * @return
     */
    @ApiOperation(value = "查询单条", notes = "查询单条")
    @GetMapping(value = "/query/one", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResult<BlogEntity>> queryOne(@Validated BlogQueryOneParam queryOneParam) {
        log.info("requestParam: {}", queryOneParam);
        return ResponseEntity.ok(blogService.queryOne(queryOneParam));
    }

    /**
     * 分页查询
     *
     * @param queryPageParam
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping(value = "/query/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResult<Page<BlogEntity>>> queryPage(@Validated BlogQueryPageParam queryPageParam) {
        log.info("requestParam: {}", queryPageParam);
        return ResponseEntity.ok(blogService.queryPage(queryPageParam));
    }

    /**
     * 更新单条
     * @param updateParam
     * @return
     */
    @ApiOperation(value = "更新单条", notes = "更新单条")
    @PutMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResult<BlogEntity>> update(@RequestBody @Validated BlogUpdateParam updateParam) {
        log.info("requestParam: {}", updateParam);
        return ResponseEntity.ok(blogService.update(updateParam));
    }

    /**
     * 删除单条
     * @param deleteOneParam
     * @return
     */
    @ApiOperation(value = "删除单条", notes = "删除单条")
    @DeleteMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResult<Void>> delete(@RequestBody @Validated BlogDeleteOneParam deleteOneParam) {
        log.info("requestParam: {}", deleteOneParam);
        return ResponseEntity.ok(blogService.delete(deleteOneParam));
    }


}
