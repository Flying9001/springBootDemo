package com.ljq.demo.springboot.web.controller;

import com.ljq.demo.springboot.baseweb.api.ApiResult;
import com.ljq.demo.springboot.baseweb.rabbitmq.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    /**
     * 发送测试
     *
     * @return
     */
    @GetMapping(value = "/send")
    public ApiResult send(){
        int count = 5;
        for (int i = 0; i < count; i++) {
            rabbitMQSender.send("" + (i+1));
        }
        return ApiResult.success();
    }

    /**
     * 发送测试-交换机
     *
     * @return
     */
    @GetMapping(value = "/send/exchange")
    public ApiResult send2(){
        rabbitMQSender.sendDirectDemo("send direct demo");
        rabbitMQSender.sendTopicDemo("send topic demo");
        rabbitMQSender.sendTopicDemo2("send topic demo2");
        rabbitMQSender.sendTopicApiUser("send topic api user");
        rabbitMQSender.sendFanoutDemo("send fanout message");

        return ApiResult.success();
    }

    /**
     * 发送测试-延时队列
     *
     * @return
     */
    @GetMapping(value = "/send/delay")
    public ApiResult sendDelay(){
        rabbitMQSender.sendDirectDelayCart("send direct delay cart");

        return ApiResult.success();
    }


}
