package com.ljq.demo.springboot.dao.user;

import com.ljq.demo.springboot.dao.BaseDao;
import com.ljq.demo.springboot.entity.UserDO;
import org.springframework.stereotype.Repository;

/**
 * @Description: 用户模块DAO
 * @Author: junqiang.lu
 * @Date: 2018/10/8
 */
@Repository
public interface UserDao extends BaseDao<UserDO> {


    /**
     * 账号注册校验
     *
     * @param userDO 用户信息
     * @return
     */
    int signUpCheck(UserDO userDO);



}
