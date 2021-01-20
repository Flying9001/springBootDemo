package com.ljq.demo.springboot.mongodb.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.ljq.demo.springboot.mongodb.model.entity.UserEntity;
import com.ljq.demo.springboot.mongodb.model.param.*;
import com.ljq.demo.springboot.mongodb.repository.UserRepository;
import com.ljq.demo.springboot.mongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Description: 用户业务层实现类
 * @Author: junqiang.lu
 * @Date: 2021/1/7
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 保存(单条)
     *
     * @param userSaveParam
     * @return
     */
    @Override
    public UserEntity save(UserSaveParam userSaveParam) {
        UserEntity userParam = new UserEntity();
        BeanUtil.copyProperties(userSaveParam,userParam, CopyOptions.create().ignoreError().ignoreNullValue());
        userParam = userRepository.save(userParam);
        return userParam;
    }

    /**
     * 查询详情(单条)
     *
     * @param userInfoParam
     * @return
     */
    @Override
    public UserEntity info(UserInfoParam userInfoParam) {
        Optional<UserEntity> userDBOpt = userRepository.findById(userInfoParam.getId());
        return userDBOpt.get();
    }

    /**
     * 查询列表
     *
     * @param userListParam
     * @return
     */
    @Override
    public Page<UserEntity> list(UserListParam userListParam) {
        Pageable pageable = PageRequest.of(userListParam.getCurrentPage() - 1, userListParam.getPageSize());
        Page<UserEntity> userDBPage = userRepository.findAll(pageable);
        return userDBPage;
    }

    /**
     * 更新(单条)
     *
     * @param userUpdateParam
     * @return
     */
    @Override
    public UserEntity update(UserUpdateParam userUpdateParam) {
        UserEntity userParam = new UserEntity();
        BeanUtil.copyProperties(userUpdateParam, userParam, CopyOptions.create().ignoreNullValue());
        userRepository.save(userParam);
        return userParam;
    }

    /**
     * 删除(单条)
     *
     * @param userDeleteParam
     * @return
     */
    @Override
    public void delete(UserDeleteParam userDeleteParam) {
        userRepository.deleteById(userDeleteParam.getId());
    }

    @Override
    public void deleteBatch(UserDeleteBatchParam deleteBatchParam) {
        List<UserEntity> list = new ArrayList<>();
        UserEntity userEntity;
        for (int i = 0; i < deleteBatchParam.getIds().length; i++) {
            userEntity = new UserEntity();
            userEntity.setId(deleteBatchParam.getIds()[i]);
            list.add(userEntity);
        }
        userRepository.deleteAll(list);
    }
}
