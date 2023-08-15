package com.ljq.demo.springboot.knife4j.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljq.demo.springboot.knife4j.common.api.ApiResult;
import com.ljq.demo.springboot.knife4j.model.entity.MessagePushResultEntity;
import com.ljq.demo.springboot.knife4j.model.param.messagepushresult.*;
import com.ljq.demo.springboot.knife4j.service.MessagePushResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 消息推送结果
 * 
 * @author junqiang.lu
 * @date 2023-08-15 16:41:26
 */
@RestController
@RequestMapping(value = "/knife4j/message/pushresult")
@Tag(name = "消息推送结果控制层")
public class MessagePushResultController {

	@Resource
	private MessagePushResultService messagePushResultService;

    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "消息推送结果保存(单条)")
    public ResponseEntity<ApiResult<MessagePushResultEntity>> save(@RequestBody @Validated MessagePushResultSaveParam saveParam) {
        return ResponseEntity.ok(messagePushResultService.save(saveParam));
    }

    @GetMapping(value = "/query/info", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "消息推送结果查询详情(单条)")
    public ResponseEntity<ApiResult<MessagePushResultEntity>> info(@Validated @ParameterObject MessagePushResultInfoParam infoParam) {
        return ResponseEntity.ok(messagePushResultService.info(infoParam));
    }

    @GetMapping(value = "/query/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "消息推送结果查询列表")
    public ResponseEntity<ApiResult<IPage<MessagePushResultEntity>>> list(@Validated @ParameterObject MessagePushResultListParam listParam) {
        return ResponseEntity.ok(messagePushResultService.list(listParam));
    }

    @PutMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "消息推送结果修改(单条)")
    public ResponseEntity<ApiResult> update(@RequestBody @Validated MessagePushResultUpdateParam updateParam) {
        return ResponseEntity.ok(messagePushResultService.update(updateParam));
    }

    @DeleteMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "消息推送结果删除(单条)")
    public ResponseEntity<ApiResult> delete(@RequestBody @Validated MessagePushResultDeleteParam deleteParam) {
        return ResponseEntity.ok(messagePushResultService.delete(deleteParam));
    }





}
