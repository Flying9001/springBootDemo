package com.ljq.demo.common.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {com.ljq.demo.common.cache.RedisUtil.class,com.ljq.demo.common.config.RedisConfig.class})
@ComponentScan(basePackages = {"com.ljq.demo.springboot"})
@MapperScan("com.ljq.demo.springboot.dao")
public class RedisUtilTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void set() {
        String keyDemo = "date1";
        Date valueDemo = new Date();
        redisUtil.set(keyDemo, valueDemo);
    }

    @Test
    public void set1() {
        String keyDemo = "date2";
        Date valueDemo = new Date();
        Long expireTime = 5 * 60L;
        redisUtil.set(keyDemo, valueDemo, expireTime);
    }

    @Test
    public void exists() {
        String keyDemo = "date1";
        boolean flag = redisUtil.exists(keyDemo);
        System.out.println(String.format("flag: %s, key: %s ", flag, keyDemo));
    }

    @Test
    public void get() {
        String keyDemo = "date1";
        Object valueDemo = redisUtil.get(keyDemo);
        System.out.println(String.format("key: %s, value: %s ", keyDemo, valueDemo));
    }

    @Test
    public void remove() {
        String keyDemo = "date1";
        redisUtil.remove(keyDemo);
    }

    @Test
    public void removeBatch() {
        List<String> keyDemoList = new ArrayList<>(16);
        keyDemoList.add("date1");
        keyDemoList.add("date2");
        redisUtil.removeBatch(keyDemoList);

    }
}