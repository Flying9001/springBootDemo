package com.ljq.demo.springboot.web.controller;

import com.ljq.demo.springboot.baseweb.api.ApiResult;
import com.ljq.demo.springboot.common.page.PageUtil;
import com.ljq.demo.springboot.service.RestUserService;
import com.ljq.demo.springboot.vo.restuser.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * REST示例-用户表
 * 
 * @author junqiang.lu
 * @date 2019-09-19 17:19:42
 */
@Controller
@RequestMapping(value = "/api/rest/user")
@Slf4j
@Api(value = "REST示例-用户表控制层", tags = "REST示例-用户表控制层")
public class RestUserController {

	@Autowired
	private RestUserService restUserService;

    /**
     * 保存(单条)
     *
     * @param restUserSaveParam
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = {"application/json"})
    @ApiOperation(value = "REST示例-用户表保存(单条)",  notes = "REST示例-用户表保存(单条)")
    @ResponseBody
    public ResponseEntity<ApiResult<RestUserVO>> save(@RequestBody RestUserSaveParam restUserSaveParam) throws Exception{
        ApiResult apiResult = restUserService.save(restUserSaveParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.CREATED);
    }

    /**
     * 保存(单条)-2
     *
     * @param userName
     * @param passcode
     * @return
     */
    @RequestMapping(value = "/save2", method = RequestMethod.POST, produces = {"application/json"})
    @ApiOperation(value = "REST示例-用户表保存(单条)-2",  notes = "REST示例-用户表保存(单条)-2")
    @ResponseBody
    public ResponseEntity<ApiResult<RestUserVO>> save2(@RequestParam("userName") String userName, @RequestParam("passcode") String passcode) throws Exception{
        RestUserSaveParam restUserSaveParam = new RestUserSaveParam();
        restUserSaveParam.setUserName(userName);
        restUserSaveParam.setPasscode(passcode);
        ApiResult apiResult = restUserService.save(restUserSaveParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.CREATED);
    }

    /**
     * 查询详情(单条)
     *
     * @param restUserInfoParam
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = {"application/json"})
    @ApiOperation(value = "REST示例-用户表查询详情(单条)",  notes = "REST示例-用户表查询详情(单条)")
    @ResponseBody
    public ResponseEntity<ApiResult<RestUserVO>> info(RestUserInfoParam restUserInfoParam) throws Exception {
        ApiResult apiResult = restUserService.info(restUserInfoParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 查询列表
     *
     * @param restUserListParam
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = {"application/json"})
    @ApiOperation(value = "REST示例-用户表查询列表",  notes = "REST示例-用户表查询列表")
    @ResponseBody
    public ResponseEntity<ApiResult<PageUtil<RestUserVO>>> list(RestUserListParam restUserListParam) throws Exception {
        ApiResult apiResult = restUserService.list(restUserListParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 修改(单条)
     *
     * @param restUserUpdateParam
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {"application/json"})
    @ApiOperation(value = "REST示例-用户表修改(单条)",  notes = "REST示例-用户表修改(单条)")
    @ResponseBody
    public ResponseEntity<ApiResult> update(@RequestBody RestUserUpdateParam restUserUpdateParam) throws Exception {
        ApiResult apiResult = restUserService.update(restUserUpdateParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.CREATED);
    }

    /**
     * 删除(单条)
     *
     * @param restUserDeleteParam
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = {"application/json"})
    @ApiOperation(value = "REST示例-用户表删除(单条)",  notes = "REST示例-用户表删除(单条)")
    @ResponseBody
    public ResponseEntity<ApiResult> delete(RestUserDeleteParam restUserDeleteParam) throws Exception {
        ApiResult apiResult = restUserService.delete(restUserDeleteParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.NO_CONTENT);
    }





}
