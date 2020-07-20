package com.ljq.demo.springboot.web.controller;

import com.ljq.demo.springboot.baseweb.api.ApiResult;
import com.ljq.demo.springboot.baseweb.rabbitmq.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: RabbitMQ 消息队列测试 controller
 * @Author: junqiang.lu
 * @Date: 2019/1/21
 */
@RestController
@RequestMapping(value = "api/demo/rabbitmq")
public class RabbitMQController {

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @RequestMapping(value = "send", method = {RequestMethod.GET, RequestMethod.POST})
    public ApiResult send(){
        rabbitMQSender.send();
        return ApiResult.success();
    }



}
