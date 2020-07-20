package com.ljq.demo.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ljq.demo.springboot.baseweb.api.ApiResult;
import com.ljq.demo.springboot.baseweb.api.ResponseCode;
import com.ljq.demo.springboot.baseweb.exception.ParamsCheckException;
import com.ljq.demo.springboot.common.page.PageUtil;
import com.ljq.demo.springboot.common.page.QueryUtil;
import com.ljq.demo.springboot.dao.user.UserDao;
import com.ljq.demo.springboot.entity.UserDO;
import com.ljq.demo.springboot.service.Ehcache3Service;
import com.ljq.demo.springboot.vo.ehcache3.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.cache.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * @Description: Ehcache3 Demo 业务具体实现类
 * @Author: junqiang.lu
 * @Date: 2019/3/16
 */
@Service("ehcache3Service")
@Transactional(rollbackFor = Exception.class)
@CacheDefaults(cacheName = "userCache")
@Slf4j
public class Ehcache3ServiceImpl implements Ehcache3Service {

    @Autowired
    private UserDao userDao;


    /**
     * 不使用缓存
     *
     * @param noCacheBean
     * @return
     * @throws Exception
     */
    @Override
    public ApiResult noCache(NoCacheBean noCacheBean) throws Exception {
        /**
         * 获取请求参数
         */
        UserDO userParams = new UserDO();
        BeanUtils.copyProperties(noCacheBean, userParams);

        UserDO userDB = userDao.queryObject(userParams);
        if (userDB == null || userDB.getId() == 0) {
            return ApiResult.failure(ResponseCode.ACCOUNT_NOT_EXIST.getMsg());
        }

        return ApiResult.success(userDB);
    }

    /**
     * 使用 @CacheResult 注解
     *
     * @param cacheResultBean
     * @return
     * @throws Exception
     */
    @CacheResult
    @Override
    public ApiResult cacheResult(CacheResultBean cacheResultBean) throws Exception {
        /**
         * 获取参数
         */
        Map<String, Object> map = BeanUtil.beanToMap(cacheResultBean);
        QueryUtil queryUtil = new QueryUtil(map);
        // 列表查询
        List<UserDO> userDBList = userDao.queryListComplex(queryUtil);
        if (userDBList == null || userDBList.isEmpty()) {
            return ApiResult.success(new PageUtil(null, 0, queryUtil.getPageSize(), queryUtil.getCurrentPage()));
        }

        int total = userDao.countComplex(queryUtil);
        // 分页处理
        PageUtil pageUtil = new PageUtil(userDBList, total, queryUtil.getPageSize(), queryUtil.getCurrentPage());

        return ApiResult.success(pageUtil);
    }

    /**
     * 使用 @CachePut 以及 @CacheValue 注解
     *
     * @param cachePutBean
     * @param apiResult 用户储存缓存数据
     * @return
     * @throws Exception
     */
    @CachePut(cacheName = "userCache")
    @Override
    public void cachePut(@CacheKey CachePutBean cachePutBean, @CacheValue ApiResult apiResult) throws Exception {
        if (cachePutBean != null && apiResult != null) {
            return;
        }
        throw new ParamsCheckException(ResponseCode.PARAM_ERROR.getMsg());
    }

    /**
     * 校验 @CachePut 注解
     *
     * @param cachePutBean
     * @return
     * @throws Exception
     */
    @CacheResult
    @Override
    public ApiResult cachePutValidate(CachePutBean cachePutBean) throws Exception {
        /**
         * 获取请求参数
         */
        UserDO userParams = new UserDO();
        BeanUtils.copyProperties(cachePutBean, userParams);

        UserDO userDB = userDao.queryObject(userParams);
        if (userDB == null || userDB.getId() == 0) {
            return ApiResult.failure(ResponseCode.ACCOUNT_NOT_EXIST.getMsg());
        }

        return ApiResult.success(userDB);
    }

    /**
     * 使用 @CacheRemove 注解
     *
     * @param cacheRemoveBean
     * @return
     * @throws Exception
     */
    @CacheRemove(cacheName = "userCache")
    @Override
    public ApiResult cacheRemove(CacheRemoveBean cacheRemoveBean) throws Exception {
        /**
         * 获取请求参数
         */
        UserDO userParams = new UserDO();
        BeanUtils.copyProperties(cacheRemoveBean, userParams);

        UserDO userDB = userDao.queryObject(userParams);
        if (userDB == null || userDB.getId() == 0) {
            return ApiResult.failure(ResponseCode.ACCOUNT_NOT_EXIST.getMsg());
        }

        return ApiResult.success(userDB);
    }

    /**
     * 校验 @CacheRemove 注解
     *
     * @param cacheRemoveBean
     * @return
     * @throws Exception
     */
    @CacheResult
    @Override
    public ApiResult cacheRemoveValidate(CacheRemoveBean cacheRemoveBean) throws Exception {
        /**
         * 获取请求参数
         */
        UserDO userParams = new UserDO();
        BeanUtils.copyProperties(cacheRemoveBean, userParams);

        UserDO userDB = userDao.queryObject(userParams);
        if (userDB == null || userDB.getId() == 0) {
            return ApiResult.failure(ResponseCode.ACCOUNT_NOT_EXIST.getMsg());
        }

        return ApiResult.success(userDB);
    }

    /**
     * 使用 @CacheRemoveAll 注解
     *
     * @param cacheRemoveAllBean
     * @return
     * @throws Exception
     */
    @CacheRemoveAll(cacheName = "userCache")
    @Override
    public ApiResult cacheRemoveAll(CacheRemoveAllBean cacheRemoveAllBean) throws Exception {
        /**
         * 获取参数
         */
        Map<String, Object> map = BeanUtil.beanToMap(cacheRemoveAllBean);
        QueryUtil queryUtil = new QueryUtil(map);
        // 列表查询
        List<UserDO> userDBList = userDao.queryListComplex(queryUtil);
        if (userDBList == null || userDBList.isEmpty()) {
            return ApiResult.success(new PageUtil(null, 0, queryUtil.getPageSize(), queryUtil.getCurrentPage()));
        }

        int total = userDao.countComplex(queryUtil);
        // 分页处理
        PageUtil pageUtil = new PageUtil(userDBList, total, queryUtil.getPageSize(), queryUtil.getCurrentPage());

        return ApiResult.success(pageUtil);
    }

    /**
     * 校验 @CacheRemoveAll 注解
     *
     * @param cacheRemoveAllBean
     * @return
     * @throws Exception
     */
    @CacheResult
    @Override
    public ApiResult cacheRemoveAllValidate(CacheRemoveAllBean cacheRemoveAllBean) throws Exception {
        /**
         * 获取参数
         */
        Map<String, Object> map = BeanUtil.beanToMap(cacheRemoveAllBean);
        QueryUtil queryUtil = new QueryUtil(map);
        // 列表查询
        List<UserDO> userDBList = userDao.queryListComplex(queryUtil);
        if (userDBList == null || userDBList.isEmpty()) {
            return ApiResult.success(new PageUtil(null, 0, queryUtil.getPageSize(), queryUtil.getCurrentPage()));
        }

        int total = userDao.countComplex(queryUtil);
        // 分页处理
        PageUtil pageUtil = new PageUtil(userDBList, total, queryUtil.getPageSize(), queryUtil.getCurrentPage());

        return ApiResult.success(pageUtil);
    }


}
