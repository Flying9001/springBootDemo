package com.ljq.demo.springboot.activiti.controller;

import com.ljq.demo.springboot.activiti.model.param.*;
import com.ljq.demo.springboot.activiti.service.LeaveInfoService;
import com.ljq.demo.springboot.baseweb.api.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 请假信息
 * 
 * @author junqiang.lu
 * @date 2020-07-09 16:25:39
 */
@RestController
@RequestMapping(value = "/api/workflow/leave")
@Slf4j
@Api(value = "请假信息控制层", tags = "请假信息控制层")
public class LeaveInfoController {

	@Autowired
	private LeaveInfoService leaveInfoService;

    /**
     * 新增(单条)
     *
     * @param leaveInfoAddParam
     * @return
     */
    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "请假信息新增(单条)",  notes = "请假信息新增(单条)")
    public ResponseEntity<ApiResult> add(@RequestBody @Validated LeaveInfoAddParam leaveInfoAddParam) throws Exception{
        ApiResult apiResult = leaveInfoService.add(leaveInfoAddParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 查询详情(单条)
     *
     * @param leaveInfoInfoParam
     * @return
     */
    @GetMapping(value = "/query/unique", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "请假信息查询详情(单条)",  notes = "请假信息查询详情(单条)")
    public ResponseEntity<ApiResult> info(@Validated LeaveInfoInfoParam leaveInfoInfoParam) throws Exception {
        ApiResult apiResult = leaveInfoService.info(leaveInfoInfoParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 查询待审批列表
     *
     * @param jobListParam
     * @return
     */
    @GetMapping(value = "/query/job/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "查询待审批列表",  notes = "查询待审批列表")
    public ResponseEntity<ApiResult> jobList(@Validated LeaveInfoJobListParam jobListParam) throws Exception {
        ApiResult apiResult = leaveInfoService.jobList(jobListParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 教师审批
     *
     * @param approvalParam
     * @return
     */
    @PutMapping(value = "/approval", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "教师审批",  notes = "教师审批")
    public ResponseEntity<ApiResult> approval(@RequestBody @Validated LeaveInfoApprovalParam approvalParam) throws Exception {
        ApiResult apiResult = leaveInfoService.approval(approvalParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 查询列表
     *
     * @param leaveInfoListParam
     * @return
     */
    @GetMapping(value = "/query/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "请假信息查询列表",  notes = "请假信息查询列表")
    public ResponseEntity<ApiResult> list(@Validated LeaveInfoListParam leaveInfoListParam) throws Exception {
        ApiResult apiResult = leaveInfoService.list(leaveInfoListParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 修改(单条)
     *
     * @param leaveInfoUpdateParam
     * @return
     */
    @PutMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "请假信息修改(单条)",  notes = "请假信息修改(单条)")
    public ResponseEntity<ApiResult> update(@RequestBody @Validated LeaveInfoUpdateParam leaveInfoUpdateParam) throws Exception {
        ApiResult apiResult = leaveInfoService.update(leaveInfoUpdateParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 删除(单条)
     *
     * @param leaveInfoDeleteParam
     * @return
     */
    @DeleteMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "请假信息删除(单条)",  notes = "请假信息删除(单条)")
    public ResponseEntity<ApiResult> delete(@RequestBody @Validated LeaveInfoDeleteParam leaveInfoDeleteParam) throws Exception {
        ApiResult apiResult = leaveInfoService.delete(leaveInfoDeleteParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 批量删除
     *
     * @param leaveInfoDeleteBatchParam
     * @return
     */
    @DeleteMapping(value = "/delete/batch", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "请假信息批量删除",  notes = "请假信息批量删除")
    public ResponseEntity<ApiResult> deleteBatch(@RequestBody @Validated LeaveInfoDeleteBatchParam leaveInfoDeleteBatchParam) throws Exception {
        ApiResult apiResult = leaveInfoService.deleteBatch(leaveInfoDeleteBatchParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }






}
