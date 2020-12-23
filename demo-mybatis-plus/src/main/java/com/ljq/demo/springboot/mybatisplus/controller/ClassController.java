package com.ljq.demo.springboot.mybatisplus.controller;

import com.ljq.demo.springboot.mybatisplus.common.api.ApiResult;
import com.ljq.demo.springboot.mybatisplus.model.param.clazz.*;
import com.ljq.demo.springboot.mybatisplus.service.ClassService;
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

import java.sql.SQLException;

/**
 * 班级
 * 
 * @author junqiang.lu
 * @date 2020-10-14 16:18:38
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/mybatis/plus/class")
@Api(value = "班级控制层", tags = "班级控制层")
public class ClassController {

	@Autowired
	private ClassService classService;

    /**
     * 保存(单条)
     *
     * @param classSaveParam
     * @return
     */
    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "班级保存(单条)",  notes = "班级保存(单条)")
    public ResponseEntity<ApiResult> save(@Validated @RequestBody ClassSaveParam classSaveParam) {
        ApiResult apiResult = classService.save(classSaveParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 班级教师批量保存
     *
     * @param classTeacherSaveBatchParam
     * @return
     */
    @PostMapping(value = "/add/teacher/batch", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "班级教师批量保存",  notes = "班级教师批量保存")
    public ResponseEntity<ApiResult> saveClassTeacherBatch(@Validated @RequestBody ClassTeacherSaveBatchParam classTeacherSaveBatchParam) {
        ApiResult apiResult = classService.saveClassTeacherBatch(classTeacherSaveBatchParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 查询详情(单条)
     *
     * @param classInfoParam
     * @return
     */
    @GetMapping(value = "/info", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "班级查询详情(单条)",  notes = "班级查询详情(单条)")
    public ResponseEntity<ApiResult> info(@Validated ClassInfoParam classInfoParam) {
        ApiResult apiResult = classService.info(classInfoParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 查询班级集合
     *
     * @param collectionParam
     * @return
     */
    @GetMapping(value = "/collection", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "查询班级集合",  notes = "查询班级集合")
    public ResponseEntity<ApiResult> collection(@Validated ClassCollectionParam collectionParam) {
        ApiResult apiResult = classService.collection(collectionParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 查询列表
     *
     * @param classListParam
     * @return
     */
    @GetMapping(value = "/list", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "班级查询列表",  notes = "班级查询列表")
    public ResponseEntity<ApiResult> list(@Validated ClassListParam classListParam) throws SQLException {
        ApiResult apiResult = classService.list(classListParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 修改(单条)
     *
     * @param classUpdateParam
     * @return
     */
    @PutMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "班级修改(单条)",  notes = "班级修改(单条)")
    public ResponseEntity<ApiResult> update(@Validated @RequestBody ClassUpdateParam classUpdateParam) {
        ApiResult apiResult = classService.update(classUpdateParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 删除(单条)
     *
     * @param classDeleteParam
     * @return
     */
    @DeleteMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "班级删除(单条)",  notes = "班级删除(单条)")
    public ResponseEntity<ApiResult> delete(@Validated @RequestBody ClassDeleteParam classDeleteParam) {
        ApiResult apiResult = classService.delete(classDeleteParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 批量删除
     *
     * @param deleteBatchParam
     * @return
     */
    @DeleteMapping(value = "/delete/batch", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "批量删除",  notes = "批量删除")
    public ResponseEntity<ApiResult> deleteBatch(@Validated @RequestBody ClassDeleteBatchParam deleteBatchParam) {
        ApiResult apiResult = classService.deleteBatch(deleteBatchParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }






}
