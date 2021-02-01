package com.ljq.demo.springboot.swagger3.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljq.demo.springboot.swagger3.model.entity.AdminUserEntity;
import com.ljq.demo.springboot.swagger3.model.param.*;
import com.ljq.demo.springboot.swagger3.service.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 管理员用户业务层具体实现类
 *
 * @author junqiang.lu
 * @date 2021-01-25 19:31:07
 */
@Service("adminUserService")
@Transactional(rollbackFor = {Exception.class})
@Slf4j
public class AdminUserServiceImpl implements AdminUserService {


	/**
	 * 保存(单条)
	 *
	 * @param saveParam
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public AdminUserEntity save(AdminUserSaveParam saveParam) {
		// 请求参数获取
		// 保存

		return null;
	}

	/**
	 * 查询详情(单条)
	 *
	 * @param infoParam
	 * @return
	 */
	@Override
	public AdminUserEntity info(AdminUserInfoParam infoParam) {
		// 请求参数获取
		// 查询

		return null;
	}

	/**
	 * 分页查询
	 *
	 * @param listParam
	 * @return
	 */
	@Override
	public IPage<AdminUserEntity> page(AdminUserListParam listParam) {
		// 请求参数获
		// 分页查询


		return null;
	}

	/**
	 * 更新(单条)
	 *
	 * @param updateParam
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void update(AdminUserUpdateParam updateParam) {
		// 请求参数获取

		// 判断对象是否存在

		// 更新对象

	}

	/**
	 * 删除(单条)
	 *
	 * @param deleteParam
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void delete(AdminUserDeleteParam deleteParam) {
		// 请求参数获取
		// 判断对象是否存在

		// 更新对象

	}

	/**
	 * 批量删除
	 *
	 * @param deleteBatchParam
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void deleteBatch(AdminUserDeleteBatchParam deleteBatchParam) {
		// 请求参数获取
		// 判断对象是否存在

		// 更新对象

	}



}
