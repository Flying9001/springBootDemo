package com.ljq.demo.springboot.alibaba.consumer.sentinel.service.fallback;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: Nacos 熔断工厂
 * @Author: junqiang.lu
 * @Date: 2020/12/3
 */
@Component
public class ConsumerFallBackFactory implements FallbackFactory<ConsumerFallBack> {

    @Override
    public ConsumerFallBack create(Throwable cause) {
        return new ConsumerFallBack(cause);
    }
}
