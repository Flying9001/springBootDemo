package com.ljq.demo.springboot.mongodb.controller;

import com.ljq.demo.springboot.mongodb.common.api.ApiResult;
import com.ljq.demo.springboot.mongodb.model.entity.UserEntity;
import com.ljq.demo.springboot.mongodb.model.param.*;
import com.ljq.demo.springboot.mongodb.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 
 * 
 * @author junqiang.lu
 * @date 2021-01-06 20:03:33
 */
@Slf4j
@RestController
@RequestMapping(value = "/springboot/mongo/user")
@Api(value = "Mongo-用户控制层", tags = "Mongo-用户控制层")
public class UserController {

	@Autowired
	private UserService userService;

    /**
     * 保存(单条)
     *
     * @param userSaveParam
     * @return
     */
    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Mongo-用户保存(单条)",  notes = "用户保存(单条)")
    public ResponseEntity<ApiResult> save(@Validated @RequestBody UserSaveParam userSaveParam) {
        return ResponseEntity.ok(ApiResult.success(userService.save(userSaveParam)));
    }

    /**
     * 查询详情(单条)
     *
     * @param userInfoParam
     * @return
     */
    @GetMapping(value = "/info", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Mongo-用户查询详情(单条)",  notes = "用户查询详情(单条)")
    public ResponseEntity<ApiResult> info(@Validated UserInfoParam userInfoParam) {
        return ResponseEntity.ok(ApiResult.success(userService.info(userInfoParam)));
    }

    /**
     * 查询列表
     *
     * @param userListParam
     * @return
     */
    @GetMapping(value = "/list", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Mongo-用户查询列表",  notes = "用户查询列表")
    public ResponseEntity<ApiResult> list(@Validated UserListParam userListParam) {
        return ResponseEntity.ok(ApiResult.success(userService.list(userListParam)));
    }

    /**
     * 修改(单条)
     *
     * @param userUpdateParam
     * @return
     */
    @PutMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Mongo-用户修改(单条)",  notes = "用户修改(单条)")
    public ResponseEntity<ApiResult<UserEntity>> update(@Validated @RequestBody UserUpdateParam userUpdateParam) {
        return ResponseEntity.ok(ApiResult.success(userService.update(userUpdateParam)));
    }

    /**
     * 删除(单条)
     *
     * @param userDeleteParam
     * @return
     */
    @DeleteMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Mongo-用户删除(单条)",  notes = "用户删除(单条)")
    public ResponseEntity<ApiResult<Void>> delete(@Validated @RequestBody UserDeleteParam userDeleteParam) {
        userService.delete(userDeleteParam);
        return ResponseEntity.ok(ApiResult.success());
    }

    /**
     * Mongo-用户批量删除
     *
     * @param deleteBatchParam
     * @return
     */
    @DeleteMapping(value = "/delete/batch", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Mongo-用户批量删除",  notes = "Mongo-用户批量删除")
    public ResponseEntity<ApiResult<Void>> deleteBatch(@Validated @RequestBody UserDeleteBatchParam deleteBatchParam) {
        userService.deleteBatch(deleteBatchParam);
        return ResponseEntity.ok(ApiResult.success());
    }






}
