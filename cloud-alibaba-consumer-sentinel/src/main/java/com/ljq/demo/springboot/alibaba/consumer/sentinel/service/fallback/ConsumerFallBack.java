package com.ljq.demo.springboot.alibaba.consumer.sentinel.service.fallback;

import com.ljq.demo.springboot.alibaba.consumer.sentinel.model.param.HelloParam;
import com.ljq.demo.springboot.alibaba.consumer.sentinel.service.NacosConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

/**
 * @Description: 熔断回调方法
 * @Author: junqiang.lu
 * @Date: 2020/12/2
 */
@Slf4j
public class ConsumerFallBack implements NacosConsumerService {

    private Throwable throwable;

    public ConsumerFallBack(Throwable throwable) {
        this.throwable = throwable;
    }

    /**
     * hello
     * @param helloParam
     * @return
     */
    @Override
    public ResponseEntity<String> hello(HelloParam helloParam) {
        String result = "Sorry," + helloParam.getName() + ",网络拥堵,稍后重试";
        log.info("call back result: {}", result);
        log.error("{}", throwable.getMessage());
        return ResponseEntity.ok(result);
    }

    /**
     * 回复
     *
     * @param helloParam
     * @return
     */
    @Override
    public ResponseEntity<String> replay(HelloParam helloParam) {
        String result = "Sorry," + helloParam.getName() + ",网络拥堵,稍后重试";
        log.info("call back result: {}", result);
        log.error("{}", throwable.getMessage());
        return ResponseEntity.ok(result);
    }


}
