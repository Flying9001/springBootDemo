package com.ljq.demo.springboot.knife4j.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljq.demo.springboot.knife4j.common.api.ApiResult;
import com.ljq.demo.springboot.knife4j.model.entity.UserMessageEntity;
import com.ljq.demo.springboot.knife4j.model.param.messagepush.MessagePushParam;
import com.ljq.demo.springboot.knife4j.model.param.usermessage.*;
import com.ljq.demo.springboot.knife4j.service.UserMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户消息
 * 
 * @author junqiang.lu
 * @date 2023-08-15 16:41:26
 */
@RestController
@RequestMapping(value = "/knife4j/user/message")
@Tag(name = "用户消息控制层")
public class UserMessageController {

	@Resource
	private UserMessageService userMessageService;

    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "用户消息保存(单条)")
    public ResponseEntity<ApiResult<UserMessageEntity>> save(@RequestBody @Validated UserMessageSaveParam saveParam) {
        return ResponseEntity.ok(userMessageService.save(saveParam));
    }

    @GetMapping(value = "/query/info", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "用户消息查询详情(单条)")
    public ResponseEntity<ApiResult<UserMessageEntity>> info(@Validated @ParameterObject UserMessageInfoParam infoParam) {
        return ResponseEntity.ok(userMessageService.info(infoParam));
    }

    @GetMapping(value = "/query/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "用户消息查询列表")
    public ResponseEntity<ApiResult<IPage<UserMessageEntity>>> list(@Validated @ParameterObject UserMessageListParam listParam) {
        return ResponseEntity.ok(userMessageService.list(listParam));
    }

    @PutMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "用户消息修改(单条)")
    public ResponseEntity<ApiResult> update(@RequestBody @Validated UserMessageUpdateParam updateParam) {
        return ResponseEntity.ok(userMessageService.update(updateParam));
    }

    @DeleteMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "用户消息删除(单条)")
    public ResponseEntity<ApiResult> delete(@RequestBody @Validated UserMessageDeleteParam deleteParam) {
        return ResponseEntity.ok(userMessageService.delete(deleteParam));
    }

    @PostMapping(value = "/push", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "用户推送消息")
    public ResponseEntity<ApiResult> save(@RequestBody @Validated MessagePushParam pushParam) {
        return ResponseEntity.ok(userMessageService.push(pushParam));
    }



}
