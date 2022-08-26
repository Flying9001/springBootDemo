package com.ljq.demo.springboot.alibaba.gateway.filter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljq.demo.springboot.alibaba.gateway.filter.common.api.ApiResult;
import com.ljq.demo.springboot.alibaba.gateway.filter.common.component.RedisComponent;
import com.ljq.demo.springboot.alibaba.gateway.filter.common.constant.RedisKeyConst;
import com.ljq.demo.springboot.alibaba.gateway.filter.dao.WhiteListMapper;
import com.ljq.demo.springboot.alibaba.gateway.filter.model.*;
import com.ljq.demo.springboot.alibaba.gateway.filter.service.WhiteListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @Description: 网关路由白名单业务实现类
 * @Author: junqiang.lu
 * @Date: 2022/8/23
 */
@Service
public class WhiteListServiceImpl extends ServiceImpl<WhiteListMapper, WhiteListEntity> implements WhiteListService {

    @Autowired
    private RedisComponent redisComponent;

    /**
     * 新增单条
     *
     * @param addParam
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public ApiResult add(WhiteListAddParam addParam) {
        WhiteListEntity whiteListParam = new WhiteListEntity();
        BeanUtil.copyProperties(addParam, whiteListParam, CopyOptions.create().ignoreError().ignoreNullValue());
        long nowTime = System.currentTimeMillis();
        whiteListParam.setCreateDate(nowTime);
        whiteListParam.setUpdateDate(nowTime);
        this.save(whiteListParam);
        redisComponent.mapPut(RedisKeyConst.KEY_GATEWAY_WHITE_LIST, whiteListParam.getId().toString(), whiteListParam);
        return ApiResult.success(whiteListParam);
    }

    /**
     * 查询单条
     *
     * @param infoParam
     * @return
     */
    @Override
    public ApiResult info(WhiteListInfoParam infoParam) {
        WhiteListEntity whiteList = redisComponent.mapGet(RedisKeyConst.KEY_GATEWAY_WHITE_LIST,
                infoParam.getId().toString(), WhiteListEntity.class);
        if (Objects.isNull(whiteList)) {
            whiteList = this.getById(infoParam.getId());
        }
        return ApiResult.success(whiteList);
    }

    /**
     * 分页查询
     *
     * @param pageParam
     * @return
     */
    @Override
    public ApiResult page(WhiteListPageParam pageParam) {
        IPage<WhiteListEntity> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        LambdaQueryWrapper<WhiteListEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(StrUtil.isNotBlank(pageParam.getRouteType()), WhiteListEntity::getRouteType,
                        pageParam.getRouteType())
                .like(StrUtil.isNotBlank(pageParam.getPath()), WhiteListEntity::getPath, pageParam.getPath())
                .like(StrUtil.isNotBlank(pageParam.getComment()), WhiteListEntity::getComment, pageParam.getComment());
        return ApiResult.success(this.page(page, queryWrapper));
    }

    /**
     * 更新单条
     *
     * @param updateParam
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public ApiResult update(WhiteListUpdateParam updateParam) {
        WhiteListEntity whiteListParam = new WhiteListEntity();
        BeanUtil.copyProperties(updateParam, whiteListParam, CopyOptions.create().ignoreError().ignoreNullValue());
        long nowTime = System.currentTimeMillis();
        whiteListParam.setUpdateDate(nowTime);
        boolean flag = this.updateById(whiteListParam);
        if (flag) {
            redisComponent.mapPut(RedisKeyConst.KEY_GATEWAY_WHITE_LIST, whiteListParam.getId().toString(),
                    whiteListParam);
        }
        return ApiResult.success(flag);
    }

    /**
     * 删除单条
     *
     * @param deleteParam
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public ApiResult delete(WhiteListDeleteParam deleteParam) {
        boolean flag = this.removeById(deleteParam.getId());
        if (flag) {
            redisComponent.mapRemove(RedisKeyConst.KEY_GATEWAY_WHITE_LIST, deleteParam.getId().toString());
        }
        return ApiResult.success(flag);
    }
}
