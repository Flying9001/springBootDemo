package com.ljq.demo.springboot.mybatisplus.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CachePenetrationProtect;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljq.demo.springboot.mybatisplus.common.constant.JetCacheConst;
import com.ljq.demo.springboot.mybatisplus.dao.UserDao;
import com.ljq.demo.springboot.mybatisplus.model.entity.UserEntity;
import com.ljq.demo.springboot.mybatisplus.model.param.user.*;
import com.ljq.demo.springboot.mybatisplus.service.IJetCacheUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @Description: JetCache 缓存示例用户业务实现类
 * @Author: junqiang.lu
 * @Date: 2023/3/25
 */
@Service
public class JetCacheUserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements IJetCacheUserService {


    /**
     * 保存(单条)
     *
     * @param userSaveParam
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    @Override
    public UserEntity save(UserSaveParam userSaveParam) {
        // 请求参数获取
        UserEntity userParam = new UserEntity();
        BeanUtil.copyProperties(userSaveParam,userParam,CopyOptions.create().ignoreNullValue().ignoreError());
        // 保存
        String nowTime = String.valueOf(System.currentTimeMillis());
        userParam.setUserInsertTime(nowTime);
        userParam.setUserUpdateTime(nowTime);
        super.save(userParam);
        return userParam;
    }

    /**
     * 查询详情(单条)
     *
     * @param userInfoParam
     * @return
     */
    @CachePenetrationProtect
    @Cached(area = JetCacheConst.CACHE_AREA, name = ":getUserById", key = "#userInfoParam.id",
            cacheType = CacheType.BOTH, expire = 300, localExpire = 180)
    @Override
    public UserEntity info(UserInfoParam userInfoParam) {
        return super.getById(userInfoParam.getId());
    }

    /**
     * 查询列表
     *
     * @param userListParam
     * @return
     */
    @Override
    public IPage<UserEntity> list(UserListParam userListParam) {
        LambdaQueryWrapper<UserEntity> userWrapper = Wrappers.lambdaQuery();
        userWrapper.likeRight(StrUtil.isNotBlank(userListParam.getUserName()), UserEntity::getUserName,
                userListParam.getUserName());
        IPage<UserEntity> page = new Page<>(userListParam.getCurrentPage(),userListParam.getPageSize());
        userWrapper.orderBy(true, Objects.isNull(userListParam.getAscFlag()) ?
                        false : userListParam.getAscFlag(), UserEntity::getId);
        return super.page(page,userWrapper);
    }

    /**
     * 更新(单条)
     *
     * @param userUpdateParam
     * @return
     */
    @CacheInvalidate(area = JetCacheConst.CACHE_AREA, name = ":getUserById", key = "#userUpdateParam.id")
    @Override
    public UserEntity update(UserUpdateParam userUpdateParam) {
        LambdaQueryWrapper<UserEntity> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(true, UserEntity::getId, userUpdateParam.getId());
        int countUser = super.count(userWrapper);
        if (countUser < 1) {
            return null;
        }
        // 请求参数获取
        UserEntity userParam = new UserEntity();
        BeanUtil.copyProperties(userUpdateParam, userParam, CopyOptions.create().ignoreNullValue().ignoreError());
        userParam.setUserUpdateTime(String.valueOf(System.currentTimeMillis()));
        super.updateById(userParam);
        return userParam;
    }

    /**
     * 删除(单条)
     *
     * @param userDeleteParam
     * @return
     */
    @CacheInvalidate(area = JetCacheConst.CACHE_AREA, name = ":getUserById", key = "#userDeleteParam.id")
    @Override
    public boolean delete(UserDeleteParam userDeleteParam) {
        boolean deleteFlag = super.removeById(userDeleteParam.getId());
        if (!deleteFlag) {
            return false;
        }
        return true;
    }
}
