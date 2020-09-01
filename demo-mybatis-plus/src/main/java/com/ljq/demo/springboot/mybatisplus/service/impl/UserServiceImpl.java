package com.ljq.demo.springboot.mybatisplus.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljq.demo.springboot.mybatisplus.common.api.ApiResult;
import com.ljq.demo.springboot.mybatisplus.dao.UserDao;
import com.ljq.demo.springboot.mybatisplus.model.entity.UserEntity;
import com.ljq.demo.springboot.mybatisplus.model.param.*;
import com.ljq.demo.springboot.mybatisplus.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 用户表业务层具体实现类
 *
 * @author junqiang.lu
 * @date 2020-08-31 14:09:53
 */
@Service("userService")
@Transactional(rollbackFor = {Exception.class})
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * 保存(单条)
	 *
	 * @param userSaveParam
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public ApiResult save(UserSaveParam userSaveParam) throws Exception {
		// 请求参数获取
		UserEntity userParam = new UserEntity();
		BeanUtil.copyProperties(userSaveParam,userParam,CopyOptions.create()
				.setIgnoreNullValue(true).setIgnoreError(true));
		// 保存
		String nowTime = String.valueOf(System.currentTimeMillis());
		userParam.setUserInsertTime(nowTime);
		userParam.setUserUpdateTime(nowTime);
		userDao.insert(userParam);

		return ApiResult.success(userParam.getId());
	}

	/**
	 * 查询详情(单条)
	 *
	 * @param userInfoParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public ApiResult info(UserInfoParam userInfoParam) throws Exception {
		UserEntity userDB = userDao.selectById(userInfoParam.getId());
		return ApiResult.success(userDB);
	}

	/**
	 * 查询列表
	 *
	 * @param userListParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public ApiResult list(UserListParam userListParam) throws Exception {
		LambdaQueryWrapper<UserEntity> userWrapper = new LambdaQueryWrapper<>();
		userWrapper.like(Objects.nonNull(userListParam.getUserName()), UserEntity::getUserName,
				userListParam.getUserName());
		IPage<UserEntity> page = new Page<>(userListParam.getCurrentPage(),userListParam.getPageSize());
		userWrapper.orderBy(true, userListParam.isAscFlag(), UserEntity::getId);
		page = userDao.selectPage(page, userWrapper);

		return ApiResult.success(page);
	}

	/**
	 * 更新(单条)
	 *
	 * @param userUpdateParam
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public ApiResult update(UserUpdateParam userUpdateParam) throws Exception {
		LambdaQueryWrapper<UserEntity> userWrapper = new LambdaQueryWrapper<>();
		userWrapper.eq(true, UserEntity::getId, userUpdateParam.getId());
		int countUser = userDao.selectCount(userWrapper);
		if (countUser < 1) {
			return ApiResult.failure("用户不存在");
		}
		// 请求参数获取
		UserEntity userParam = new UserEntity();
		BeanUtil.copyProperties(userUpdateParam, userParam, CopyOptions.create().ignoreNullValue().ignoreError());
		userParam.setUserUpdateTime(String.valueOf(System.currentTimeMillis()));
		userDao.updateById(userParam);

		return ApiResult.success();
	}

	/**
	 * 删除(单条)
	 *
	 * @param userDeleteParam
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public ApiResult delete(UserDeleteParam userDeleteParam) throws Exception {
		int countUser = userDao.deleteById(userDeleteParam.getId());
		if (countUser < 1) {
			return ApiResult.failure("用户不存在");
		}
		return ApiResult.success();
	}
	

	
}
