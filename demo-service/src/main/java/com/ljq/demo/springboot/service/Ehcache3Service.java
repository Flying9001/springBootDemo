package com.ljq.demo.springboot.service;

import com.ljq.demo.springboot.baseweb.api.ApiResult;
import com.ljq.demo.springboot.vo.ehcache3.*;

/**
 * @Description: Ehcache3 Demo 业务
 * @Author: junqiang.lu
 * @Date: 2019/3/16
 */
public interface Ehcache3Service {

    /**
     * 不使用缓存
     *
     * @param noCacheBean
     * @return
     * @throws Exception
     */
    ApiResult noCache(NoCacheBean noCacheBean) throws Exception;

    /**
     * 使用 @CacheResult 注解
     *
     * @param cacheResultBean
     * @return
     * @throws Exception
     */
    ApiResult cacheResult(CacheResultBean cacheResultBean) throws Exception;

    /**
     * 使用 @CachePut 注解
     *
     * @param cachePutBean
     * @param apiResult 用户储存缓存数据
     * @return
     * @throws Exception
     */
    void cachePut(CachePutBean cachePutBean, ApiResult apiResult) throws Exception;

    /**
     * 校验 @CachePut 注解
     *
     * @param cachePutBean
     * @return
     * @throws Exception
     */
    ApiResult cachePutValidate(CachePutBean cachePutBean) throws Exception;

    /**
     * 使用 @CacheRemove 注解
     *
     * @param cacheRemoveBean
     * @return
     * @throws Exception
     */
    ApiResult cacheRemove(CacheRemoveBean cacheRemoveBean) throws Exception;

    /**
     * 校验 @CacheRemove 注解
     *
     * @param cacheRemoveBean
     * @return
     * @throws Exception
     */
    ApiResult cacheRemoveValidate(CacheRemoveBean cacheRemoveBean) throws Exception;

    /**
     * 使用 @CacheRemoveAll 注解
     *
     * @param cacheRemoveAllBean
     * @return
     * @throws Exception
     */
    ApiResult cacheRemoveAll(CacheRemoveAllBean cacheRemoveAllBean) throws Exception;

    /**
     * 校验 @CacheRemoveAll 注解
     *
     * @param cacheRemoveAllBean
     * @return
     * @throws Exception
     */
    ApiResult cacheRemoveAllValidate(CacheRemoveAllBean cacheRemoveAllBean) throws Exception;

}
