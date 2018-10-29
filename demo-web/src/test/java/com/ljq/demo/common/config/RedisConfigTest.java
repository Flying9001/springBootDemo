package com.ljq.demo.common.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {com.ljq.demo.common.config.RedisConfig.class})
@ComponentScan(basePackages = {"com.ljq.demo.springboot"})
@MapperScan("com.ljq.demo.springboot.dao")
public class RedisConfigTest {

    /**
     * redisTemplate 泛型必须与 RedisConfig 类中定义的一致,否则会出现类型不匹配错误
     */
    @Autowired
    private RedisTemplate<Serializable, Object> redisTemplate;

    /**
     * 测试 spring boot 2.0 整合 redis
     */
    @Test
    public void redisTest() {
        String keyDemo = "date";
        redisTemplate.opsForValue().set(keyDemo,new Date());
        Date result = (Date) redisTemplate.opsForValue().get(keyDemo);
        System.out.println(String.format("key: %s, value: %s.", keyDemo, result));
    }





}