package com.ljq.demo.springboot.web.controller;

import com.ljq.demo.common.api.ApiResult;
import com.ljq.demo.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 用户控制中心
 * @Author: junqiang.lu
 * @Date: 2018/10/9
 */
@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 列表查询,post 请求, json 参数
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "list", method = {RequestMethod.POST})
    public ApiResult queryList(@RequestBody Map<String, Object> params){

        return userService.queryList(params);
    }

    /**
     * 列表查询,post 请求, parameter 参数
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "lists", method = {RequestMethod.POST})
    public ApiResult queryLists(Map<String, Object> params){

        return userService.queryList(params);
    }

    /**
     * 列表查询, get 请求
     * @return
     */
    @RequestMapping(value = "list", method = {RequestMethod.GET})
    public ApiResult queryList(){
        Map<String, Object> params = new HashMap<>();
        return userService.queryList(params);
    }

    /**
     * 信息查询, @PathVariable 与 json 参数混合
     *
     * @param id
     * @param params
     * @return
     */
    @RequestMapping(value = "info/{id}",method = RequestMethod.POST)
    public ApiResult info(@PathVariable("id") long id, @RequestBody Map<String, Object> params){

        return userService.queryList(params);
    }

}
