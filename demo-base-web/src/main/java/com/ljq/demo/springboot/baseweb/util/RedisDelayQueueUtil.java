package com.ljq.demo.springboot.baseweb.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * @Description: Redis 延时队列工具类
 * @Author: junqiang.lu
 * @Date: 2021/10/14
 */
@Slf4j
@Component
public class RedisDelayQueueUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 队列名称
     */
    public static final String QUEUE_NAME_ORDER = "orderDelayQueue";

    /**
     * 延时时长
     */
    public static final long QUEUE_DELAY_TIME_ORDER = 30000;

    /**
     * 设置订单延时任务
     *
     * @param orderMsg 订单消息
     * @param delayTime 延时时间,距离当前时间的时间间隔(单位:毫秒)
     */
    public void setOrderDelayTask(Object orderMsg, long delayTime) {
        long expireTime = System.currentTimeMillis() + delayTime;
        boolean addFlag = redisTemplate.opsForZSet().add(QUEUE_NAME_ORDER, JSONUtil.toJsonStr(orderMsg), expireTime);
        if (addFlag) {
            // TODO 记录订单状态

            log.info("订单延时消息创建成功,{},过期时间: {}", orderMsg, expireTime);
        }
    }

    /**
     * 消费订单延时队列
     */
    @PostConstruct
    public void consumeOrderQueue() {
        log.info("订单延时队列扫描已启动.....");
        ThreadUtil.newSingleExecutor().execute(() -> {
            while (true) {
                Set<String> set = redisTemplate.opsForZSet().rangeByScore(QUEUE_NAME_ORDER, 0,
                        System.currentTimeMillis(), 0L, 1L);
                // 如果没有需要消费的消息,则间隔一段时间再扫描
                if (CollUtil.isEmpty(set)) {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                String orderMsgStr = set.iterator().next();
                // TODO 将 orderMsgStr 转化为 orderMsg 对象
//                JSONUtil.toBean(orderMsgStr, OrderMsgObject.class);

                boolean deleteFlag = redisTemplate.opsForZSet().remove(QUEUE_NAME_ORDER, orderMsgStr) > 0;
                if (deleteFlag) {
                    // TODO 消费订单消息

                    log.info("订单延时消息已成功消费,{}", orderMsgStr);
                }
            }
        });

    }



}
