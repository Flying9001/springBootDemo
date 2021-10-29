package com.ljq.demo.springboot.alibaba.server.consumer.feign.service;

import com.ljq.demo.springboot.alibaba.server.consumer.feign.common.constant.NacosConst;
import com.ljq.demo.springboot.alibaba.server.consumer.feign.model.param.HelloParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: Nacos 服务消费者业务层
 * @Author: junqiang.lu
 * @Date: 2020/12/2
 */
@FeignClient(NacosConst.NACOS_SERVER_PROVIDER_NAME)
@RequestMapping(NacosConst.NACOS_SERVER_PROVIDER_PATH)
public interface NacosConsumerService {

    /**
     * hello
     * @param helloParam
     * @return
     */
    @GetMapping(value = "/api/nacos/hello", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<String> hello(@SpringQueryMap HelloParam helloParam);

    /**
     * 回复
     *
     * @param helloParam
     * @return
     */
    @PostMapping(value = "/api/nacos/replay", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<String> replay(@RequestBody HelloParam helloParam);


}
