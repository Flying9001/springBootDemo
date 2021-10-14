package com.ljq.demo.springboot.web.controller;

import com.ljq.demo.springboot.baseweb.api.ApiResult;
import com.ljq.demo.springboot.baseweb.util.RedisDelayQueueUtil;
import com.ljq.demo.springboot.vo.order.OrderDelayCreateParam;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 订单控制层
 * @Author: junqiang.lu
 * @Date: 2021/10/14
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/order")
@Api(value = "订单控制层", tags = "订单控制层")
public class OrderController {

    @Autowired
    private RedisDelayQueueUtil redisDelayQueueUtil;

    /**
     * 创建延时订单
     *
     * @param orderDelayCreateParam
     * @return
     */
    @PostMapping(value = "/delay")
    public ResponseEntity<ApiResult<Void>> createDelayOrder(@RequestBody @Validated OrderDelayCreateParam
                                                                        orderDelayCreateParam) {
        redisDelayQueueUtil.setOrderDelayTask(orderDelayCreateParam, RedisDelayQueueUtil.QUEUE_DELAY_TIME_ORDER);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(ApiResult.success(), headers, HttpStatus.OK);
    }
}
