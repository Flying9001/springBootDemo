package com.ljq.demo.springboot.swagger3.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljq.demo.springboot.swagger3.common.api.ApiResult;
import com.ljq.demo.springboot.swagger3.model.entity.AdminUserEntity;
import com.ljq.demo.springboot.swagger3.model.param.*;
import com.ljq.demo.springboot.swagger3.service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员用户
 * 
 * @author junqiang.lu
 * @date 2021-01-25 19:40:57
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/springboot/admin/user")
@Api(value = "管理员用户", tags = "管理员用户")
public class AdminUserController {

	@Autowired
	private AdminUserService adminUserService;

    /**
     * 新增(单条)
     *
     * @param saveParam
     * @return
     */
    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "管理员用户新增(单条)",  notes = "管理员用户新增(单条)")
    public ResponseEntity<ApiResult<AdminUserEntity>> save(@Validated @RequestBody AdminUserSaveParam saveParam) {
        return ResponseEntity.ok(ApiResult.success(adminUserService.save(saveParam)));
    }

    /**
     * 查询详情(单条)
     *
     * @param infoParam
     * @return
     */
    @GetMapping(value = "/info", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "管理员用户查询详情(单条)",  notes = "管理员用户查询详情(单条)")
    public ResponseEntity<ApiResult<AdminUserEntity>> info(@Validated AdminUserInfoParam infoParam) {
        return ResponseEntity.ok(ApiResult.success(adminUserService.info(infoParam)));
    }

    /**
     * 查询列表
     *
     * @param listParam
     * @return
     */
    @GetMapping(value = "/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "管理员用户查询列表",  notes = "管理员用户查询列表")
    public ResponseEntity<ApiResult<IPage<AdminUserEntity>>> page(@Validated AdminUserListParam listParam) {
        return ResponseEntity.ok(ApiResult.success(adminUserService.page(listParam)));
    }

    /**
     * 修改(单条)
     *
     * @param
     * @return
     */
    @PutMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "管理员用户修改(单条)",  notes = "管理员用户修改(单条)")
    public ResponseEntity<ApiResult<Void>> update(@Validated @RequestBody AdminUserUpdateParam updateParam) {
        adminUserService.update(updateParam);
        return ResponseEntity.ok(ApiResult.success());
    }

    /**
     * 删除(单条)
     *
     * @param deleteParam
     * @return
     */
    @DeleteMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "管理员用户删除(单条)",  notes = "管理员用户删除(单条)")
    public ResponseEntity<ApiResult<Void>> delete(@Validated @RequestBody AdminUserDeleteParam deleteParam) {
        adminUserService.delete(deleteParam);
        return ResponseEntity.ok(ApiResult.success());
    }

    /**
     * 批量删除
     *
     * @param deleteBatchParam
     * @return
     */
    @DeleteMapping(value = "/delete/batch", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "管理员用户批量删除",  notes = "管理员用户批量删除")
    public ResponseEntity<ApiResult<Void>> deleteBatch(@Validated @RequestBody AdminUserDeleteBatchParam deleteBatchParam) {
        adminUserService.deleteBatch(deleteBatchParam);
        return ResponseEntity.ok(ApiResult.success());
    }




}
