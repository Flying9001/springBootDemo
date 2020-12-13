package com.ljq.demo.springboot.alibaba.server.consumer.service;

import com.ljq.demo.springboot.alibaba.server.consumer.model.param.HelloParam;

/**
 * @Description: Nacos 服务消费者业务层
 * @Author: junqiang.lu
 * @Date: 2020/12/2
 */
public interface NacosConsumerService {

    /**
     * hello
     *
     * @param helloParam
     * @return
     */
    String hello(HelloParam helloParam);

    /**
     * 回复
     *
     * @param helloParam
     * @return
     */
    String replay(HelloParam helloParam);
}
