package com.ljq.demo.springboot.web.controller;

import com.ljq.demo.common.api.ApiResult;
import com.ljq.demo.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "list")
    public ApiResult queryList(@RequestBody Map<String, Object> params){
        System.out.println("UserController api/user/list");

        return userService.queryList(params);
    }
}
