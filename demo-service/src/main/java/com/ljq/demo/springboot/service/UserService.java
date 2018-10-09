package com.ljq.demo.springboot.service;

import com.ljq.demo.common.api.ApiResult;

import java.util.Map;

/**
 * @Description: 用户模块业务
 * @Author: junqiang.lu
 * @Date: 2018/10/9
 */
public interface UserService {

    /**
     * 列表查询
     *
     * @param map 分页信息
     * @return
     */
    ApiResult queryList(Map<String, Object> map);

}
