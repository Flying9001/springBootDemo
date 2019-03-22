package com.ljq.demo.springboot.common.cache;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.stereotype.Component;

import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.Duration;
import javax.cache.expiry.TouchedExpiryPolicy;
import java.util.concurrent.TimeUnit;

/**
 * @Description: Ehcache 3 配置类
 * @Author: junqiang.lu
 * @Date: 2019/3/16
 */
@Component
public class Ehcache3Config implements JCacheManagerCustomizer {

    private static final String CACHE_NAME = "userCache";

    @Override
    public void customize(CacheManager cacheManager) {
        cacheManager.createCache(CACHE_NAME,new MutableConfiguration<>()
                .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, 30)))
                .setStoreByValue(false)
                .setStatisticsEnabled(true)
        );

    }


}
