package com.ljq.demo.springboot.alibaba.gateway.filter.common.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Description: redis 工具类
 * @Author: junqiang.lu
 * @Date: 2018/10/29
 */
@Component
public class RedisComponent implements Serializable {

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
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
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
    public void set(String key, Object value, Long expireTime) {
        redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 判断缓存中是否有对应的 key
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除一条记录
     *
     * @param key
     * @return
     */
    public boolean remove(String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    /**
     * 批量删除
     *
     * @param keyList
     */
    public void removeBatch(List<String> keyList) {
        Set<Serializable> keys = new HashSet<>(keyList);
        if (keys.size() > 0){
            redisTemplate.delete(keys);
        }
    }

    /**
     * 向 map 集合插入一条数据
     *
     * @param key 集合 key
     * @param hashKey 元素 key
     * @param value 元素值
     */
    public void mapPut(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 向 map 集合插入多条数据
     *
     * @param key 集合 key
     * @param elementMap 元素 map 集合
     */
    public void mapPutBatch(String key, Map<String, Object> elementMap) {
        redisTemplate.opsForHash().putAll(key, elementMap);
    }

    /**
     * 从 map 集合中获取一个元素
     *
     * @param key 集合 key
     * @param hashKey 元素 key
     * @param clazz 元素值类
     * @return
     */
    public <V> V mapGet(String key, String hashKey, Class<V> clazz) {
        return (V) redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 从 map 集合中读取所有元素
     *
     * @param key 集合 key
     * @param clazz 元素值类
     * @return
     */
    public <V> List<V> mapGetAll(String key, Class<V> clazz) {
        return redisTemplate.opsForHash().values(key).stream().map(o -> (V)o).collect(Collectors.toList());
    }

    /**
     * 删除 map 集合中一个元素
     *
     * @param key 集合 key
     * @param hashKey 元素 key
     */
    public void mapRemove(String key, String hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    /**
     * 批量删除 map 集合元素
     *
     * @param key 集合 key
     * @param hashKeyList 元素 key 列表
     */
    public void mapRemoveBatch(String key, List<String> hashKeyList) {
        redisTemplate.opsForHash().delete(key, hashKeyList.toArray());
    }


}
