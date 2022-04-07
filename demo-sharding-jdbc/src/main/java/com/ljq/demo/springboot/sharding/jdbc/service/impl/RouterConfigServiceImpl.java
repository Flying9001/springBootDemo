package com.ljq.demo.springboot.sharding.jdbc.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljq.demo.springboot.sharding.jdbc.dao.RouterConfigDao;
import com.ljq.demo.springboot.sharding.jdbc.model.entity.RouterConfigEntity;
import com.ljq.demo.springboot.sharding.jdbc.model.param.RouterConfigAddParam;
import com.ljq.demo.springboot.sharding.jdbc.model.param.RouterConfigQueryOneParam;
import com.ljq.demo.springboot.sharding.jdbc.model.param.RouterConfigQueryPageParam;
import com.ljq.demo.springboot.sharding.jdbc.service.RouterConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @Description: 路由器配置业务实现类
 * @Author: junqiang.lu
 * @Date: 2022/4/1
 */
@Service("routerConfigService")
public class RouterConfigServiceImpl extends ServiceImpl<RouterConfigDao, RouterConfigEntity>
        implements RouterConfigService {


    /**
     * 设备信息新增
     *
     * @param addParam
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    @Override
    public RouterConfigEntity add(RouterConfigAddParam addParam) {
        RouterConfigEntity routerConfigEntity = new RouterConfigEntity();
        BeanUtil.copyProperties(addParam, routerConfigEntity, CopyOptions.create().ignoreError().ignoreNullValue());
        this.save(routerConfigEntity);
        return routerConfigEntity;
    }

    /**
     * 设备信息查询单条
     *
     * @param queryOneParam
     * @return
     */
    @Override
    public RouterConfigEntity queryOne(RouterConfigQueryOneParam queryOneParam) {
        return this.getById(queryOneParam.getId());
    }

    /**
     * 设备信息分页查询
     *
     * @param queryPageParam
     * @return
     */
    @Override
    public IPage<RouterConfigEntity> queryPage(RouterConfigQueryPageParam queryPageParam) {
        IPage<RouterConfigEntity> page = new Page<>(queryPageParam.getCurrentPage(), queryPageParam.getPageSize());

        LambdaQueryWrapper<RouterConfigEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Objects.nonNull(queryPageParam.getDeviceId()), RouterConfigEntity::getDeviceId,
                queryPageParam.getDeviceId());
        queryWrapper.orderBy(true,true,RouterConfigEntity::getId);
        return this.page(page, queryWrapper);
    }
}
