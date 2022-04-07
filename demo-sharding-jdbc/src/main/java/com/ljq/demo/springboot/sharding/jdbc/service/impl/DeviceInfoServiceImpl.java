package com.ljq.demo.springboot.sharding.jdbc.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljq.demo.springboot.sharding.jdbc.dao.DeviceInfoDao;
import com.ljq.demo.springboot.sharding.jdbc.model.entity.DeviceInfoEntity;
import com.ljq.demo.springboot.sharding.jdbc.model.param.DeviceInfoAddParam;
import com.ljq.demo.springboot.sharding.jdbc.model.param.DeviceInfoQueryOneParam;
import com.ljq.demo.springboot.sharding.jdbc.model.param.DeviceInfoQueryPageParam;
import com.ljq.demo.springboot.sharding.jdbc.service.DeviceInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @Description: 设备信息业务实现类
 * @Author: junqiang.lu
 * @Date: 2022/4/2
 */
@Service("deviceInfoService")
public class DeviceInfoServiceImpl extends ServiceImpl<DeviceInfoDao, DeviceInfoEntity> implements DeviceInfoService {


    /**
     * 设备信息新增
     *
     * @param addParam
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    @Override
    public DeviceInfoEntity add(DeviceInfoAddParam addParam) {
        DeviceInfoEntity  deviceInfoEntity = new DeviceInfoEntity();
        BeanUtil.copyProperties(addParam, deviceInfoEntity, CopyOptions.create().ignoreError().ignoreNullValue());
        this.save(deviceInfoEntity);
        return deviceInfoEntity;
    }

    /**
     * 设备信息查询单条
     *
     * @param queryOneParam
     * @return
     */
    @Override
    public DeviceInfoEntity queryOne(DeviceInfoQueryOneParam queryOneParam) {
        return this.getById(queryOneParam.getId());
    }

    /**
     * 设备信息分页查询
     *
     * @param queryPageParam
     * @return
     */
    @Override
    public IPage<DeviceInfoEntity> queryPage(DeviceInfoQueryPageParam queryPageParam) {
        Page<DeviceInfoEntity> page = new Page<>(queryPageParam.getCurrentPage(), queryPageParam.getPageSize());
        LambdaQueryWrapper<DeviceInfoEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Objects.nonNull(queryPageParam.getType()), DeviceInfoEntity::getType, queryPageParam.getType());
        queryWrapper.orderBy(true, true, DeviceInfoEntity::getType);
        return this.page(page, queryWrapper);
    }
}
