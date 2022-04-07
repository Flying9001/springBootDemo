package com.ljq.demo.springboot.sharding.jdbc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljq.demo.springboot.sharding.jdbc.model.entity.RouterConfigEntity;
import com.ljq.demo.springboot.sharding.jdbc.model.param.RouterConfigAddParam;
import com.ljq.demo.springboot.sharding.jdbc.model.param.RouterConfigQueryOneParam;
import com.ljq.demo.springboot.sharding.jdbc.model.param.RouterConfigQueryPageParam;

/**
 * @Description: 路由器配置业务接口
 * @Author: junqiang.lu
 * @Date: 2022/4/1
 */
public interface RouterConfigService {

    /**
     * 路由器配置新增
     *
     * @param addParam
     * @return
     */
    RouterConfigEntity add(RouterConfigAddParam addParam);

    /**
     * 路由器配置查询单条
     *
     * @param queryOneParam
     * @return
     */
    RouterConfigEntity queryOne(RouterConfigQueryOneParam queryOneParam);

    /**
     * 路由器配置分页查询
     *
     * @param queryPageParam
     * @return
     */
    IPage<RouterConfigEntity> queryPage(RouterConfigQueryPageParam queryPageParam);



}
