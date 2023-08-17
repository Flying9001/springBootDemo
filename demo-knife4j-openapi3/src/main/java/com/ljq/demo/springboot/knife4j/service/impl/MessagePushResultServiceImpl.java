package com.ljq.demo.springboot.knife4j.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljq.demo.springboot.knife4j.common.api.ApiResult;
import com.ljq.demo.springboot.knife4j.mapper.MessagePushResultMapper;
import com.ljq.demo.springboot.knife4j.model.entity.MessagePushResultEntity;
import com.ljq.demo.springboot.knife4j.model.param.messagepushresult.*;
import com.ljq.demo.springboot.knife4j.service.MessagePushResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 消息推送结果业务层具体实现类
 *
 * @author junqiang.lu
 * @date 2023-08-15 10:41:29
 */
@Slf4j
@Service("messagePushResultService")
@Transactional(rollbackFor = {Exception.class})
public class MessagePushResultServiceImpl extends ServiceImpl<MessagePushResultMapper, MessagePushResultEntity>
             implements MessagePushResultService {

	/**
	 * 保存(单条)
	 *
	 * @param saveParam
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public ApiResult save(MessagePushResultSaveParam saveParam) {
		// 参数校验


		// 请求参数获取
		MessagePushResultEntity messagePushResultEntity = new MessagePushResultEntity();
		BeanUtil.copyProperties(saveParam,messagePushResultEntity,CopyOptions.create()
				.ignoreNullValue().ignoreError());
		// 保存
		super.save(messagePushResultEntity);
		return ApiResult.success(messagePushResultEntity);
	}

	/**
	 * 查询详情(单条)
	 *
	 * @param infoParam
	 * @return
	 */
	@Override
	public ApiResult info(MessagePushResultInfoParam infoParam) {
		// 参数校验


		// 查询
		MessagePushResultEntity messagePushResultDb = super.getOne(Wrappers.lambdaQuery(MessagePushResultEntity.class)
				.eq(MessagePushResultEntity::getId, infoParam.getId()));
		return ApiResult.success(messagePushResultDb);
	}

	/**
	 * 查询列表
	 *
	 * @param listParam
	 * @return
	 */
	@Override
	public ApiResult list(MessagePushResultListParam listParam) {
		// 参数校验


		// 分页查询
		IPage<MessagePushResultEntity> page = new Page<>(listParam.getCurrentPage(), listParam.getPageSize());
		LambdaQueryWrapper<MessagePushResultEntity> queryWrapper = Wrappers.lambdaQuery(MessagePushResultEntity.class);
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
	public ApiResult update(MessagePushResultUpdateParam updateParam) {
		// 参数校验


		// 请求参数获取
		MessagePushResultEntity messagePushResultEntity = new MessagePushResultEntity();
		BeanUtil.copyProperties(updateParam, messagePushResultEntity, CopyOptions.create()
				.ignoreNullValue().ignoreError());
		// 更新对象
		boolean updateFlag = super.updateById(messagePushResultEntity);
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
	public ApiResult delete(MessagePushResultDeleteParam deleteParam) {
		// 参数校验


		// 设置条件参数
		LambdaQueryWrapper<MessagePushResultEntity> queryWrapper = Wrappers.lambdaQuery(MessagePushResultEntity.class);
		queryWrapper.eq(MessagePushResultEntity::getId, deleteParam.getId());

		boolean deleteFlag = super.remove(queryWrapper);
		if (!deleteFlag) {
			return ApiResult.fail();
		}
		return ApiResult.success();
	}
	

	
}
