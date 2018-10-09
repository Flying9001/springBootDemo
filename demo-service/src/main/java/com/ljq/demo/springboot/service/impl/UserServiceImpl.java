package com.ljq.demo.springboot.service.impl;

import com.ljq.demo.common.api.ApiResult;
import com.ljq.demo.springboot.dao.user.UserDao;
import com.ljq.demo.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description: 用户业务具体实现
 * @Author: junqiang.lu
 * @Date: 2018/10/9
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 列表查询
     *
     * @param map 分页信息
     * @return
     */
    @Override
    public ApiResult queryList(Map<String, Object> map) {

        // TODO 分页数据处理

        return ApiResult.success(userDao.queryList(map));
    }
}
