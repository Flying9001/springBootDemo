package com.ljq.demo.springboot.knife4j.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljq.demo.springboot.knife4j.common.api.ApiResult;
import com.ljq.demo.springboot.knife4j.mapper.UserMessageMapper;
import com.ljq.demo.springboot.knife4j.model.entity.UserMessageEntity;
import com.ljq.demo.springboot.knife4j.model.param.usermessage.*;
import com.ljq.demo.springboot.knife4j.service.UserMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户消息业务层具体实现类
 *
 * @author junqiang.lu
 * @date 2023-08-15 10:41:29
 */
@Slf4j
@Service("userMessageService")
@Transactional(rollbackFor = {Exception.class})
public class UserMessageServiceImpl extends ServiceImpl<UserMessageMapper, UserMessageEntity>
             implements UserMessageService {

	/**
	 * 保存(单条)
	 *
	 * @param saveParam
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public ApiResult save(UserMessageSaveParam saveParam) {
		// 参数校验


		// 请求参数获取
		UserMessageEntity userMessageEntity = new UserMessageEntity();
		BeanUtil.copyProperties(saveParam,userMessageEntity,CopyOptions.create()
				.ignoreNullValue().ignoreError());
		// 保存
		super.save(userMessageEntity);
		return ApiResult.success();
	}

	/**
	 * 查询详情(单条)
	 *
	 * @param infoParam
	 * @return
	 */
	@Override
	public ApiResult info(UserMessageInfoParam infoParam) {
		// 参数校验


		// 查询
		UserMessageEntity userMessageDb = super.getOne(Wrappers.lambdaQuery(UserMessageEntity.class)
				.eq(UserMessageEntity::getId, infoParam.getId()));
		return ApiResult.success(userMessageDb);
	}

	/**
	 * 查询列表
	 *
	 * @param listParam
	 * @return
	 */
	@Override
	public ApiResult list(UserMessageListParam listParam) {
		// 参数校验


		// 分页查询
		IPage<UserMessageEntity> page = new Page<>(listParam.getCurrentPage(), listParam.getPageSize());
		LambdaQueryWrapper<UserMessageEntity> queryWrapper = Wrappers.lambdaQuery(UserMessageEntity.class);
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
	public ApiResult update(UserMessageUpdateParam updateParam) {
		// 参数校验


		// 请求参数获取
		UserMessageEntity userMessageEntity = new UserMessageEntity();
		BeanUtil.copyProperties(updateParam, userMessageEntity, CopyOptions.create()
				.ignoreNullValue().ignoreError());
		// 更新对象
		boolean updateFlag = super.updateById(userMessageEntity);
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
	public ApiResult delete(UserMessageDeleteParam deleteParam) {
		// 参数校验


		// 设置条件参数
		LambdaQueryWrapper<UserMessageEntity> queryWrapper = Wrappers.lambdaQuery(UserMessageEntity.class);
		queryWrapper.eq(UserMessageEntity::getId, deleteParam.getId());

		boolean deleteFlag = super.remove(queryWrapper);
		if (!deleteFlag) {
			return ApiResult.fail();
		}
		return ApiResult.success();
	}
	

	
}
