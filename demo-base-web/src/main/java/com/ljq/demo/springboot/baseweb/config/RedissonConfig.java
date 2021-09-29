package com.ljq.demo.springboot.baseweb.config;

import lombok.Getter;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: Redisson 分布式锁配置类
 * @Author: junqiang.lu
 * @Date: 2021/9/27
 */
@Getter
@Configuration
public class RedissonConfig {

    /**
     * redis 地址
     */
    @Value(value = "${redisson.address}")
    private String address;
    /**
     * redis 数据库编号
     */
    @Value(value = "${redisson.database}")
    private Integer database;
    /**
     * redis 密码
     */
    @Value(value = "${redisson.password}")
    private String password;
    /**
     * redis 最小连接数量
     */
    @Value(value = "${redisson.connectionMinimumIdleSize}")
    private Integer connectionMinimumIdleSize;
    /**
     * redis 最大连接池大小
     */
    @Value(value = "${redisson.connectionPoolSize}")
    private Integer connectionPoolSize;
    /**
     * redis 连接超时时间(毫秒)
     */
    @Value(value = "${redisson.connectionTimeout}")
    private Integer connectionTimeout;
    /**
     * redis 服务器响应时间(毫秒)
     */
    @Value(value = "${redisson.timeout}")
    private Integer timeout;

    /**
     * 创建 Redisson 客户端
     *
     * @return
     */
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        /**
         * 运行模式
         * useSingleServer: 单机模式
         * useMasterSlaveServers: 主从模式
         * useSentinelServers: 哨兵模式
         */
        config.useSingleServer()
                .setAddress(address)
                .setDatabase(database)
                .setPassword(password)
                .setConnectionMinimumIdleSize(connectionMinimumIdleSize)
                .setConnectionPoolSize(connectionPoolSize)
                .setConnectTimeout(connectionTimeout)
                .setTimeout(timeout);
        return Redisson.create(config);

    }
}
