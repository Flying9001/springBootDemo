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

    @RequestMapping(value = "list", method = {RequestMethod.POST})
    public ApiResult queryList(@RequestBody Map<String, Object> params){

        return userService.queryList(params);
    }

    @RequestMapping(value = "list", method = {RequestMethod.GET})
    public ApiResult queryList(){
        Map<String, Object> params = new HashMap<>();
        return userService.queryList(params);
    }

    @RequestMapping(value = "info/{id}",method = RequestMethod.POST)
    public ApiResult info(@PathVariable("id") long id, @RequestBody Map<String, Object> params){

        return userService.queryList(params);
    }

}
