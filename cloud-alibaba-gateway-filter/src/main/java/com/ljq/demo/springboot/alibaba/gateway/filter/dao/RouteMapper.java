package com.ljq.demo.springboot.alibaba.gateway.filter.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljq.demo.springboot.alibaba.gateway.filter.model.RouteEntity;
import org.springframework.stereotype.Repository;

/**
 * @Description: 路由数据库持久层
 * @Author: junqiang.lu
 * @Date: 2021/10/21
 */
@Repository
public interface RouteMapper extends BaseMapper<RouteEntity> {

}
