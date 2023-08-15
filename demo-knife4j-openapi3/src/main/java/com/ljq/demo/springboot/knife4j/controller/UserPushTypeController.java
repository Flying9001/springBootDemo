package com.ljq.demo.springboot.knife4j.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljq.demo.springboot.knife4j.common.api.ApiResult;
import com.ljq.demo.springboot.knife4j.model.entity.UserPushTypeEntity;
import com.ljq.demo.springboot.knife4j.model.param.userpushtype.*;
import com.ljq.demo.springboot.knife4j.service.UserPushTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户消息推送方式
 * 
 * @author junqiang.lu
 * @date 2023-08-15 10:41:29
 */
@RestController
@RequestMapping(value = "/knife4j/user/pushtype")
@Tag(name = "用户消息推送方式控制层")
public class UserPushTypeController {

	@Resource
	private UserPushTypeService userPushTypeService;

    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "用户消息推送方式保存(单条)")
    public ResponseEntity<ApiResult<UserPushTypeEntity>> save(@RequestBody @Validated UserPushTypeSaveParam saveParam) {
        return ResponseEntity.ok(userPushTypeService.save(saveParam));
    }

    @GetMapping(value = "/query/info", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "用户消息推送方式查询详情(单条)")
    public ResponseEntity<ApiResult<UserPushTypeEntity>> info(@Validated @ParameterObject UserPushTypeInfoParam infoParam) {
        return ResponseEntity.ok(userPushTypeService.info(infoParam));
    }

    @GetMapping(value = "/query/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "用户消息推送方式查询列表")
    public ResponseEntity<ApiResult<IPage<UserPushTypeEntity>>> list(@Validated @ParameterObject UserPushTypeListParam listParam) {
        return ResponseEntity.ok(userPushTypeService.list(listParam));
    }

    @PutMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "用户消息推送方式修改(单条)")
    public ResponseEntity<ApiResult> update(@RequestBody @Validated UserPushTypeUpdateParam updateParam) {
        return ResponseEntity.ok(userPushTypeService.update(updateParam));
    }

    @DeleteMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "用户消息推送方式删除(单条)")
    public ResponseEntity<ApiResult> delete(@RequestBody @Validated UserPushTypeDeleteParam deleteParam) {
        return ResponseEntity.ok(userPushTypeService.delete(deleteParam));
    }





}
