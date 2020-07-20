package com.ljq.demo.springboot.web.controller;

import com.ljq.demo.springboot.baseweb.api.ApiResult;
import com.ljq.demo.springboot.baseweb.api.ResponseCode;
import com.ljq.demo.springboot.baseweb.exception.ParamsCheckException;
import com.ljq.demo.springboot.service.Ehcache3Service;
import com.ljq.demo.springboot.vo.ehcache3.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: Ehcache 3 示例控制中心
 * @Author: junqiang.lu
 * @Date: 2019/3/21
 */
@RestController
@RequestMapping(value = "api/ehcache3")
@Slf4j
public class Ehcache3Controller {

    @Autowired
    private Ehcache3Service ehcache3Service;

    /**
     * 不使用缓存
     *
     * @param noCacheBean
     * @return
     */
    @RequestMapping(value = "noCache", method = RequestMethod.POST)
    public ApiResult noCache(@RequestBody NoCacheBean noCacheBean) {

        try {
            return ehcache3Service.noCache(noCacheBean);
        } catch (Exception e) {
            if (ParamsCheckException.class.isAssignableFrom(e.getClass())){
                log.error("参数错误");
                return ApiResult.failure(e.getMessage());
            }
            log.error("未知异常",e);
            return ApiResult.failure(ResponseCode.UNKNOWN_ERROR);
        }

    }

    /**
     * 使用 @CacheResult 注解
     *
     * @param cacheResultBean
     * @return
     */
    @RequestMapping(value = "cacheResult", method = RequestMethod.POST)
    public ApiResult cacheResult(@RequestBody CacheResultBean cacheResultBean) {

        try {
            return ehcache3Service.cacheResult(cacheResultBean);
        } catch (Exception e) {
            if (ParamsCheckException.class.isAssignableFrom(e.getClass())){
                log.error("参数错误");
                return ApiResult.failure(e.getMessage());
            }
            log.error("未知异常",e);
            return ApiResult.failure(ResponseCode.UNKNOWN_ERROR);
        }
    }

    /**
     * 使用 @CachePut 注解
     *
     * @param cacheResultBean
     * @return
     */
    @RequestMapping(value = "cachePut", method = RequestMethod.POST)
    public ApiResult cachePut(@RequestBody CachePutBean cacheResultBean) {

        try {
            ehcache3Service.cachePut(cacheResultBean,ApiResult.success());
        } catch (Exception e) {
            if (ParamsCheckException.class.isAssignableFrom(e.getClass())){
                log.error("参数错误");
                return ApiResult.failure(e.getMessage());
            }
            log.error("未知异常",e);
            return ApiResult.failure(ResponseCode.UNKNOWN_ERROR);
        }

        return ApiResult.success();
    }

    /**
     * 校验 @CachePut 注解
     *
     * @param cachePutBean
     * @return
     */
    @RequestMapping(value = "cachePutValidate", method = RequestMethod.POST)
    public ApiResult cachePutValidate(@RequestBody CachePutBean cachePutBean) {

        ApiResult apiResult = null;
        try {
            return ehcache3Service.cachePutValidate(cachePutBean);
        } catch (Exception e) {
            if (ParamsCheckException.class.isAssignableFrom(e.getClass())){
                log.error("参数错误");
                return ApiResult.failure(e.getMessage());
            }
            log.error("未知异常",e);
            return ApiResult.failure(ResponseCode.UNKNOWN_ERROR);
        }
    }

    /**
     * 使用 @CacheRemove 注解
     *
     * @param cacheRemoveBean
     * @return
     */
    @RequestMapping(value = "cacheRemove", method = RequestMethod.POST)
    public ApiResult cacheRemove(@RequestBody CacheRemoveBean cacheRemoveBean) {

        try {
            return ehcache3Service.cacheRemove(cacheRemoveBean);
        } catch (Exception e) {
            if (ParamsCheckException.class.isAssignableFrom(e.getClass())){
                log.error("参数错误");
                return ApiResult.failure(e.getMessage());
            }
            log.error("未知异常",e);
            return ApiResult.failure(ResponseCode.UNKNOWN_ERROR);
        }
    }

    /**
     * 校验 @CacheRemove 注解
     *
     * @param cacheRemoveBean
     * @return
     */
    @RequestMapping(value = "cacheRemoveValidate", method = RequestMethod.POST)
    public ApiResult cacheRemoveValidate(@RequestBody CacheRemoveBean cacheRemoveBean) {

        try {
            return ehcache3Service.cacheRemoveValidate(cacheRemoveBean);
        } catch (Exception e) {
            if (ParamsCheckException.class.isAssignableFrom(e.getClass())){
                log.error("参数错误");
                return ApiResult.failure(e.getMessage());
            }
            log.error("未知异常",e);
            return ApiResult.failure(ResponseCode.UNKNOWN_ERROR);
        }
    }

    /**
     * 使用 @CacheRemoveAll 注解
     *
     * @param cacheRemoveAllBean
     * @return
     */
    @RequestMapping(value = "cacheRemoveAll", method = RequestMethod.POST)
    public ApiResult cacheRemoveAll(@RequestBody CacheRemoveAllBean cacheRemoveAllBean) {

        try {
            return ehcache3Service.cacheRemoveAll(cacheRemoveAllBean);
        } catch (Exception e) {
            if (ParamsCheckException.class.isAssignableFrom(e.getClass())){
                log.error("参数错误");
                return ApiResult.failure(e.getMessage());
            }
            log.error("未知异常",e);
            return ApiResult.failure(ResponseCode.UNKNOWN_ERROR);
        }
    }

    /**
     * 校验 @CacheRemoveAll 注解
     *
     * @param cacheRemoveAllBean
     * @return
     */
    @RequestMapping(value = "cacheRemoveAllValidate", method = RequestMethod.POST)
    public ApiResult cacheRemoveAllValidate(@RequestBody CacheRemoveAllBean cacheRemoveAllBean) {

        try {
           return ehcache3Service.cacheRemoveAllValidate(cacheRemoveAllBean);
        } catch (Exception e) {
            if (ParamsCheckException.class.isAssignableFrom(e.getClass())){
                log.error("参数错误");
                return ApiResult.failure(e.getMessage());
            }
            log.error("未知异常",e);
            return ApiResult.failure(ResponseCode.UNKNOWN_ERROR);
        }
    }


}
