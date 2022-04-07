package com.ljq.demo.springboot.sharding.jdbc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljq.demo.springboot.sharding.jdbc.model.entity.DeviceInfoEntity;
import org.springframework.stereotype.Repository;

/**
 * @Description: 设备信息 DAO
 * @Author: junqiang.lu
 * @Date: 2022/4/1
 */
@Repository
public interface DeviceInfoDao extends BaseMapper<DeviceInfoEntity> {
}
