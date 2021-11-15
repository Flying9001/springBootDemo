package com.ljq.demo.springboot.mongodb.controller;

import com.ljq.demo.springboot.mongodb.common.api.ApiResult;
import com.ljq.demo.springboot.mongodb.model.entity.BlogEntity;
import com.ljq.demo.springboot.mongodb.model.param.*;
import com.ljq.demo.springboot.mongodb.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 博客控制层
 * @Author: junqiang.lu
 * @Date: 2021/11/13
 */
@Slf4j
@RestController
@RequestMapping(value = "/springboot/mongo/blog")
@Api(value = "博客控制层", tags = "博客控制层")
public class BlogController {

    @Autowired
    private BlogService blogService;

    /**
     * 新增单条博客
     *
     * @param addParam
     * @return
     */
    @PostMapping(value = "/add/one")
    @ApiOperation(value = "新增单条博客", notes = "新增单条博客")
    public ResponseEntity<ApiResult<BlogEntity>> add(@RequestBody @Validated BlogAddParam addParam) {
        return ResponseEntity.ok(blogService.add(addParam));
    }

    /**
     * 批量新增博客
     *
     * @param addBatchParam
     * @return
     */
    @PostMapping(value = "/add/batch")
    @ApiOperation(value = "批量新增博客", notes = "批量新增博客")
    public ResponseEntity<ApiResult<Void>> addBatch(@RequestBody @Validated BlogAddBatchParam addBatchParam) {
        return ResponseEntity.ok(blogService.addBatch(addBatchParam));
    }

    /**
     * 查询单条博客
     *
     * @param queryOneParam
     * @return
     */
    @GetMapping(value = "/query/one")
    @ApiOperation(value = "查询单条博客", notes = "查询单条博客")
    public ResponseEntity<ApiResult<BlogEntity>> queryOne(@Validated BlogQueryOneParam queryOneParam) {
        return ResponseEntity.ok(blogService.queryOne(queryOneParam));
    }

    /**
     * 分页查询博客
     *
     * @param queryPageParam
     * @return
     */
    @GetMapping(value = "/query/page")
    @ApiOperation(value = "分页查询博客", notes = "分页查询博客")
    public ResponseEntity<ApiResult<Page<BlogEntity>>> queryPage(@Validated BlogQueryPageParam queryPageParam) {
        return ResponseEntity.ok(blogService.queryPage(queryPageParam));
    }

    /**
     * 查询博客阅读量
     *
     * @param readCountQueryParam
     * @return
     */
    @GetMapping(value = "/query/count/read")
    @ApiOperation(value = "查询博客阅读量", notes = "查询博客阅读量")
    public ResponseEntity<ApiResult<BlogEntity>> queryReadCount(@Validated BlogReadCountQueryParam
                                                                                  readCountQueryParam) {
        return ResponseEntity.ok(blogService.queryReadCount(readCountQueryParam));
    }

    /**
     * 查询博客阅读量统计
     *
     * @param readCountSummaryParam
     * @return
     */
    @GetMapping(value = "/query/summary/count/read")
    @ApiOperation(value = "查询博客阅读量统计", notes = "查询博客阅读量统计")
    public ResponseEntity<ApiResult<BlogEntity>> summaryReadCount(@Validated BlogReadCountSummaryParam
                                                                        readCountSummaryParam) {
        return ResponseEntity.ok(blogService.summaryReadCount(readCountSummaryParam));
    }

    /**
     * 更新单条博客
     *
     * @param updateParam
     * @return
     */
    @PutMapping(value = "/update/one")
    @ApiOperation(value = "更新单条博客", notes = "更新单条博客")
    public ResponseEntity<ApiResult<BlogEntity>> update(@RequestBody @Validated BlogUpdateParam updateParam) {
        return ResponseEntity.ok(blogService.update(updateParam));
    }

    /**
     * 批量更新博客
     *
     * @param updateBatchParam
     * @return
     */
    @PutMapping(value = "/update/batch")
    @ApiOperation(value = "批量更新博客", notes = "批量更新博客")
    public ResponseEntity<ApiResult<Void>> updateBatch(@RequestBody @Validated BlogUpdateBatchParam
                                                                         updateBatchParam) {
        return ResponseEntity.ok(blogService.updateBatch(updateBatchParam));
    }

    /**
     * 删除单条博客
     *
     * @param deleteOneParam
     * @return
     */
    @DeleteMapping(value = "/delete/one")
    @ApiOperation(value = "删除单条博客", notes = "删除单条博客")
    public ResponseEntity<ApiResult<Void>> delete(@RequestBody @Validated BlogDeleteOneParam deleteOneParam) {
        return ResponseEntity.ok(blogService.deleteOne(deleteOneParam));
    }

    /**
     * 批量删除博客
     *
     * @param deleteBatchParam
     * @return
     */
    @DeleteMapping(value = "/delete/batch")
    @ApiOperation(value = "批量删除博客", notes = "批量删除博客")
    public ResponseEntity<ApiResult<Void>> deleteBatch(@RequestBody @Validated BlogDeleteBatchParam deleteBatchParam) {
        return ResponseEntity.ok(blogService.deleteBatch(deleteBatchParam));
    }




}
