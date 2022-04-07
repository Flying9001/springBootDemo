package com.ljq.demo.springboot.sharding.jdbc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljq.demo.springboot.sharding.jdbc.model.entity.RouterConfigEntity;
import org.springframework.stereotype.Repository;

/**
 * @Description: 路由器配置 DAO
 * @Author: junqiang.lu
 * @Date: 2022/4/1
 */
@Repository
public interface RouterConfigDao extends BaseMapper<RouterConfigEntity> {


}
