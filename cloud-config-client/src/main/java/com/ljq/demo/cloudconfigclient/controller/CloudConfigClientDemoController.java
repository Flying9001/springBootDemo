package com.ljq.demo.cloudconfigclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: spring cloud config client demo controller
 * @Author: junqiang.lu
 * @Date: 2018/11/5
 */
@RefreshScope
@RestController
public class CloudConfigClientDemoController {

    @Value("${message: Hello default}")
    private String message;

    @Value("${foo: foo default}")
    private String foo;

    @RequestMapping("/message")
    String getMessage() {
        return this.message;
    }

    @RequestMapping("/foo")
    String getFoo(){
        System.out.println("foo: " + this.foo);
        return this.foo;
    }

}
