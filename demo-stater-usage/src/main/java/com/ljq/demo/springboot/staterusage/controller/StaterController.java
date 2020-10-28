package com.ljq.demo.springboot.staterusage.controller;

import com.ljq.demo.springboot.stater.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 自定义 stater 控制层
 * @Author: junqiang.lu
 * @Date: 2020/10/27
 */
@Slf4j
@RestController
@RequestMapping("/api/stater")
public class StaterController {

    @Autowired
    private HelloService helloService;

    @GetMapping(value = "/hello", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok(helloService.hello());
    }
}
