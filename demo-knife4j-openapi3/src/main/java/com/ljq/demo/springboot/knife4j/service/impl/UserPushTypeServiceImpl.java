package com.ljq.demo.springboot.knife4j.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljq.demo.springboot.knife4j.common.api.ApiResult;
import com.ljq.demo.springboot.knife4j.mapper.UserPushTypeMapper;
import com.ljq.demo.springboot.knife4j.model.entity.UserPushTypeEntity;
import com.ljq.demo.springboot.knife4j.model.param.userpushtype.*;
import com.ljq.demo.springboot.knife4j.service.UserPushTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户消息推送方式业务层具体实现类
 *
 * @author junqiang.lu
 * @date 2023-08-15 10:32:24
 */
@Slf4j
@Service("userPushTypeService")
@Transactional(rollbackFor = {Exception.class})
public class UserPushTypeServiceImpl extends ServiceImpl<UserPushTypeMapper, UserPushTypeEntity>
             implements UserPushTypeService {

	/**
	 * 保存(单条)
	 *
	 * @param saveParam
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public ApiResult save(UserPushTypeSaveParam saveParam) {
		// 参数校验


		// 请求参数获取
		UserPushTypeEntity userPushTypeEntity = new UserPushTypeEntity();
		BeanUtil.copyProperties(saveParam,userPushTypeEntity,CopyOptions.create()
				.ignoreNullValue().ignoreError());
		// 保存
		super.save(userPushTypeEntity);
		return ApiResult.success(userPushTypeEntity);
	}

	/**
	 * 查询详情(单条)
	 *
	 * @param infoParam
	 * @return
	 */
	@Override
	public ApiResult info(UserPushTypeInfoParam infoParam) {
		// 参数校验


		// 查询
		UserPushTypeEntity userPushTypeDb = super.getOne(Wrappers.lambdaQuery(UserPushTypeEntity.class)
				.eq(UserPushTypeEntity::getId, infoParam.getId()));
		return ApiResult.success(userPushTypeDb);
	}

	/**
	 * 查询列表
	 *
	 * @param listParam
	 * @return
	 */
	@Override
	public ApiResult list(UserPushTypeListParam listParam) {
		// 参数校验


		// 分页查询
		IPage<UserPushTypeEntity> page = new Page<>(listParam.getCurrentPage(), listParam.getPageSize());
		LambdaQueryWrapper<UserPushTypeEntity> queryWrapper = Wrappers.lambdaQuery(UserPushTypeEntity.class);
		// 设置请求参数


		page = super.page(page, queryWrapper);
		return ApiResult.success(page);
	}

	/**
	 * 更新(单条)
	 *
	 * @param updateParam
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public ApiResult update(UserPushTypeUpdateParam updateParam) {
		// 参数校验


		// 请求参数获取
		UserPushTypeEntity userPushTypeEntity = new UserPushTypeEntity();
		BeanUtil.copyProperties(updateParam, userPushTypeEntity, CopyOptions.create()
				.ignoreNullValue().ignoreError());
		// 更新对象
		boolean updateFlag = super.updateById(userPushTypeEntity);
		if (!updateFlag) {
			return ApiResult.fail();
		}
		return ApiResult.success();
	}

	/**
	 * 删除(单条)
	 *
	 * @param deleteParam
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public ApiResult delete(UserPushTypeDeleteParam deleteParam) {
		// 参数校验


		// 设置条件参数
		LambdaQueryWrapper<UserPushTypeEntity> queryWrapper = Wrappers.lambdaQuery(UserPushTypeEntity.class);
		queryWrapper.eq(UserPushTypeEntity::getId, deleteParam.getId());

		boolean deleteFlag = super.remove(queryWrapper);
		if (!deleteFlag) {
			return ApiResult.fail();
		}
		return ApiResult.success();
	}
	

	
}
