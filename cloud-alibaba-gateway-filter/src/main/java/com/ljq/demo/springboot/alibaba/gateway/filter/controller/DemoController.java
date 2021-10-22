package com.ljq.demo.springboot.alibaba.gateway.filter.controller;

import com.ljq.demo.springboot.alibaba.gateway.filter.common.api.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 测试接口控制层
 * @Author: junqiang.lu
 * @Date: 2021/10/22
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/shop/user")
public class DemoController {

    /**
     * 查询列表
     *
     * @return
     */
    @GetMapping(value = "/list")
    public ResponseEntity<?> list(){
        log.info("/api/user/list");
        return ResponseEntity.ok(ApiResult.success("请求成功啦!!!"));
    }


}
