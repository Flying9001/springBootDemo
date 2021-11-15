package com.ljq.demo.springboot.alibaba.gateway.filter.controller;

import cn.hutool.json.JSONUtil;
import com.ljq.demo.springboot.alibaba.gateway.filter.common.api.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 通知
     *
     * @param obj
     * @return
     */
    @PostMapping(value = "/notify")
    public ResponseEntity<?> notify(@RequestBody Object obj) {
        log.warn("/api/user/notify, request param: {}", JSONUtil.toJsonStr(obj));
        return ResponseEntity.ok("Skywalking warning notify");
    }


}