package com.ljq.demo.springboot.common.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description: redis 工具类
 * @Author: junqiang.lu
 * @Date: 2018/10/29
 */
@Component
public class RedisUtil implements Serializable {

    private static final long serialVersionUID = 894291893913244121L;

    @Autowired
    private RedisTemplate<Serializable, Object> redisTemplate;

    /**
     * 写入缓存
     * 不指定保存时间,永久保存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存
     * 指定保存时间,单位：秒,超时将自动删除
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 判断缓存中是否有对应的 key
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        Object result = operations.get(key);
        return result;
    }

    /**
     * 删除一条记录
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 批量删除
     *
     * @param keyList
     */
    public void removeBatch(final List<String> keyList) {
        Set<Serializable> keys = new HashSet<>(keyList);
        if (keys.size() > 0){
            redisTemplate.delete(keys);
        }
    }

}
