package com.ljq.demo.springboot.knife4j.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljq.demo.springboot.knife4j.common.api.ApiMsgEnum;
import com.ljq.demo.springboot.knife4j.common.api.ApiResult;
import com.ljq.demo.springboot.knife4j.common.constant.MessagePushConst;
import com.ljq.demo.springboot.knife4j.mapper.UserMessageMapper;
import com.ljq.demo.springboot.knife4j.model.BasePageParam;
import com.ljq.demo.springboot.knife4j.model.entity.MessagePushResultEntity;
import com.ljq.demo.springboot.knife4j.model.entity.UserMessageEntity;
import com.ljq.demo.springboot.knife4j.model.entity.UserPushTypeEntity;
import com.ljq.demo.springboot.knife4j.model.param.messagepush.MessagePushParam;
import com.ljq.demo.springboot.knife4j.model.param.usermessage.*;
import com.ljq.demo.springboot.knife4j.service.MessagePushResultService;
import com.ljq.demo.springboot.knife4j.service.UserMessageService;
import com.ljq.demo.springboot.knife4j.service.UserPushTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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

	@Resource
	private UserPushTypeService userPushTypeService;
	@Resource
	private MessagePushResultService pushResultService;

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
		return ApiResult.success(userMessageEntity);
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

	/**
	 * 查询未推送成功的消息
	 *
	 * @param pageParam
	 * @return
	 */
	@Override
	public IPage<UserMessageEntity> queryPageFailMessage(BasePageParam pageParam) {
		IPage<UserMessageEntity> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
		return this.baseMapper.queryPageFailMessage(page);
	}

	/**
	 * 推送消息
	 *
	 * @param pushParam
	 * @return
	 */
	@Override
	public ApiResult push(MessagePushParam pushParam) {
		// 参数校验
		UserMessageEntity userMessageDb = super.getById(pushParam.getMessageId());
		// 校验消息是否存在
		if (Objects.isNull(userMessageDb)) {
			return ApiResult.fail(ApiMsgEnum.USER_MESSAGE_NOT_EXIST);
		}
		// 校验消息是否重复推送
		if (userMessageDb.getPushTotal() > 0) {
			return ApiResult.fail(ApiMsgEnum.USER_MESSAGE_PUSH_REPEAT);
		}

		// 查询用户支持的推送方式
		List<UserPushTypeEntity> userPushTypeDbList = userPushTypeService.list(Wrappers
				.lambdaQuery(UserPushTypeEntity.class)
				.eq(UserPushTypeEntity::getUserId, userMessageDb.getUserId()));
		// 设置推送总次数
		userMessageDb.setPushCount(0);
		userMessageDb.setPushTotal(userPushTypeDbList.size());
		super.updateById(userMessageDb);
		// TODO 升级思路: 异步推送

		// 根据支持类型推送
		for (UserPushTypeEntity pushTypeEntity: userPushTypeDbList) {
			switch (pushTypeEntity.getPushType()) {
				case MessagePushConst.USER_PUSH_TYPE_SMS:
					// 短信推送
					messagePushBySms(userMessageDb, pushTypeEntity.getReceiveAddress());
					continue;
				case MessagePushConst.USER_PUSH_TYPE_EMAIL:
					// 邮件推送
					messagePushByEmail(userMessageDb, pushTypeEntity.getReceiveAddress());
					continue;
				case MessagePushConst.USER_PUSH_TYPE_APP:
					// APP推送
					messagePushByApp(userMessageDb, pushTypeEntity.getReceiveAddress());
					continue;
				case MessagePushConst.USER_PUSH_TYPE_WECHAT:
					// 微信推送
					messagePushByWechat(userMessageDb, pushTypeEntity.getReceiveAddress());
					continue;
				default:
					log.warn("user push type is invalid. pushType:{}", pushTypeEntity.getPushType());
			}
		}

		return ApiResult.success();
	}

	/**
	 * 重新推送消息
	 *
	 * @param userMessage
	 * @return
	 */
	@Override
	public void repush(UserMessageEntity userMessage) {
		// 查询用户支持的推送方式
		List<UserPushTypeEntity> userPushTypeDbList = userPushTypeService.list(Wrappers
				.lambdaQuery(UserPushTypeEntity.class)
				.eq(UserPushTypeEntity::getUserId, userMessage.getUserId()));
		Map<Integer, String> pushTypeMap = userPushTypeDbList.stream()
				.collect(Collectors.toMap(UserPushTypeEntity::getPushType, UserPushTypeEntity::getReceiveAddress));
		// 查询推送失败的结果
		List<MessagePushResultEntity> pushResultList = pushResultService.list(Wrappers
				.lambdaQuery(MessagePushResultEntity.class)
				.eq(MessagePushResultEntity::getMessageId, userMessage.getId())
				.eq(MessagePushResultEntity::getPushResult, MessagePushConst.MESSAGE_SEND_FAIL));
		// TODO 升级思路: 异步推送

		// 根据支持类型推送
		for (MessagePushResultEntity pushResult: pushResultList) {
			switch (pushResult.getPushType()) {
				case MessagePushConst.USER_PUSH_TYPE_SMS:
					// 短信推送
					messagePushBySms(userMessage, pushTypeMap.get(pushResult.getPushType()));
					continue;
				case MessagePushConst.USER_PUSH_TYPE_EMAIL:
					// 邮件推送
					messagePushByEmail(userMessage, pushTypeMap.get(pushResult.getPushType()));
					continue;
				case MessagePushConst.USER_PUSH_TYPE_APP:
					// APP推送
					messagePushByApp(userMessage, pushTypeMap.get(pushResult.getPushType()));
					continue;
				case MessagePushConst.USER_PUSH_TYPE_WECHAT:
					// 微信推送
					messagePushByWechat(userMessage, pushTypeMap.get(pushResult.getPushType()));
					continue;
				default:
					log.warn("user push type is invalid. pushType:{}", pushResult.getPushType());
			}
		}
	}


	/**
	 * 短信推送消息
	 *
	 * @param userMessage
	 * @param receiveAddress
	 */
	private void messagePushBySms(UserMessageEntity userMessage, String receiveAddress) {
		log.info("message pushed by sms. messageId:{},receiveAddress:{},messageTitle:{}",
				userMessage.getId(), receiveAddress, userMessage.getTitle());
		// 查询推送结果
		LambdaQueryWrapper<MessagePushResultEntity> wrapper = Wrappers.lambdaQuery(MessagePushResultEntity.class)
				.eq(MessagePushResultEntity::getMessageId, userMessage.getId())
				.eq(MessagePushResultEntity::getPushType, MessagePushConst.USER_PUSH_TYPE_SMS);
		MessagePushResultEntity pushResultDb = pushResultService.getOne(wrapper);
		// 预先创建推送结果
		MessagePushResultEntity pushResult = new MessagePushResultEntity();
		pushResult.setMessageId(userMessage.getId());
		pushResult.setPushType(MessagePushConst.USER_PUSH_TYPE_SMS);
		try {
			// 模拟消息发送
			Thread.sleep(100L);

			// 更新推送结果
			pushResult.setPushResult(MessagePushConst.MESSAGE_SEND_SUCCESS);
			if (Objects.isNull(pushResultDb)) {
				// 初次推送
				pushResult.setRetryTime(0);
				pushResultService.save(pushResult);
			} else {
				// 重试推送
				pushResultService.update(pushResult, wrapper);
			}
			// 更新推送次数
			userMessage.setPushCount(userMessage.getPushCount() + 1);
			super.updateById(userMessage);
		} catch (Exception e) {
			log.error("sms message push error", e);
			// 设置重试次数
			pushResult.setPushResult(MessagePushConst.MESSAGE_SEND_FAIL);
			if (Objects.isNull(pushResultDb)) {
				// 初次推送
				pushResult.setRetryTime(1);
				pushResultService.save(pushResult);
			} else {
				// 重试推送
				pushResult.setRetryTime(pushResultDb.getRetryTime());
				pushResultService.update(pushResult, wrapper);
			}
		}
	}

	/**
	 * 邮件推送消息
	 *
	 * @param userMessage
	 * @param receiveAddress
	 */
	private void messagePushByEmail(UserMessageEntity userMessage, String receiveAddress) {
		log.info("message pushed by email. messageId:{},receiveAddress:{},messageTitle:{}",
				userMessage.getId(), receiveAddress, userMessage.getTitle());
		// 查询推送结果
		LambdaQueryWrapper<MessagePushResultEntity> wrapper = Wrappers.lambdaQuery(MessagePushResultEntity.class)
				.eq(MessagePushResultEntity::getMessageId, userMessage.getId())
				.eq(MessagePushResultEntity::getPushType, MessagePushConst.USER_PUSH_TYPE_EMAIL);
		MessagePushResultEntity pushResultDb = pushResultService.getOne(wrapper);
		// 预先创建推送结果
		MessagePushResultEntity pushResult = new MessagePushResultEntity();
		pushResult.setMessageId(userMessage.getId());
		pushResult.setPushType(MessagePushConst.USER_PUSH_TYPE_EMAIL);
		try {
			// 模拟消息发送
			Thread.sleep(100L);


			// 更新推送结果
			pushResult.setPushResult(MessagePushConst.MESSAGE_SEND_SUCCESS);
			if (Objects.isNull(pushResultDb)) {
				// 初次推送
				pushResult.setRetryTime(0);
				pushResultService.save(pushResult);
			} else {
				// 重试推送
				pushResultService.update(pushResult, wrapper);
			}
			// 更新推送次数
			userMessage.setPushCount(userMessage.getPushCount() + 1);
			super.updateById(userMessage);
		} catch (Exception e) {
			log.error("email message push error", e);
			// 设置重试次数
			pushResult.setPushResult(MessagePushConst.MESSAGE_SEND_FAIL);
			if (Objects.isNull(pushResultDb)) {
				// 初次推送
				pushResult.setRetryTime(1);
				pushResultService.save(pushResult);
			} else {
				// 重试推送
				pushResult.setRetryTime(pushResultDb.getRetryTime());
				pushResultService.update(pushResult, wrapper);
			}
		}
	}

	/**
	 * APP推送消息
	 *
	 * @param userMessage
	 * @param receiveAddress
	 */
	private void messagePushByApp(UserMessageEntity userMessage, String receiveAddress) {
		log.info("message pushed by app. messageId:{},receiveAddress:{},messageTitle:{}",
				userMessage.getId(), receiveAddress, userMessage.getTitle());
		// 查询推送结果
		LambdaQueryWrapper<MessagePushResultEntity> wrapper = Wrappers.lambdaQuery(MessagePushResultEntity.class)
				.eq(MessagePushResultEntity::getMessageId, userMessage.getId())
				.eq(MessagePushResultEntity::getPushType, MessagePushConst.USER_PUSH_TYPE_APP);
		MessagePushResultEntity pushResultDb = pushResultService.getOne(wrapper);
		// 预先创建推送结果
		MessagePushResultEntity pushResult = new MessagePushResultEntity();
		pushResult.setMessageId(userMessage.getId());
		pushResult.setPushType(MessagePushConst.USER_PUSH_TYPE_APP);
		try {
			// 模拟消息发送
			Thread.sleep(100L);

			// 更新推送结果
			pushResult.setPushResult(MessagePushConst.MESSAGE_SEND_SUCCESS);
			if (Objects.isNull(pushResultDb)) {
				// 初次推送
				pushResult.setRetryTime(0);
				pushResultService.save(pushResult);
			} else {
				// 重试推送
				pushResultService.update(pushResult, wrapper);
			}
			// 更新推送次数
			userMessage.setPushCount(userMessage.getPushCount() + 1);
			super.updateById(userMessage);
		} catch (Exception e) {
			log.error("app message push error", e);
			// 设置重试次数
			pushResult.setPushResult(MessagePushConst.MESSAGE_SEND_FAIL);
			if (Objects.isNull(pushResultDb)) {
				// 初次推送
				pushResult.setRetryTime(1);
				pushResultService.save(pushResult);
			} else {
				// 重试推送
				pushResult.setRetryTime(pushResultDb.getRetryTime());
				pushResultService.update(pushResult, wrapper);
			}
		}
	}

	/**
	 * 微信推送消息
	 *
	 * @param userMessage
	 * @param receiveAddress
	 */
	private void messagePushByWechat(UserMessageEntity userMessage, String receiveAddress) {
		log.info("message pushed by wechat. messageId:{},receiveAddress:{},messageTitle:{}",
				userMessage.getId(), receiveAddress, userMessage.getTitle());
		// 查询推送结果
		LambdaQueryWrapper<MessagePushResultEntity> wrapper = Wrappers.lambdaQuery(MessagePushResultEntity.class)
				.eq(MessagePushResultEntity::getMessageId, userMessage.getId())
				.eq(MessagePushResultEntity::getPushType, MessagePushConst.USER_PUSH_TYPE_WECHAT);
		MessagePushResultEntity pushResultDb = pushResultService.getOne(wrapper);
		// 预先创建推送结果
		MessagePushResultEntity pushResult = new MessagePushResultEntity();
		pushResult.setMessageId(userMessage.getId());
		pushResult.setPushType(MessagePushConst.USER_PUSH_TYPE_WECHAT);
		try {
			// 模拟消息发送
			Thread.sleep(100L);

			// 更新推送结果
			pushResult.setPushResult(MessagePushConst.MESSAGE_SEND_SUCCESS);
			if (Objects.isNull(pushResultDb)) {
				// 初次推送
				pushResult.setRetryTime(0);
				pushResultService.save(pushResult);
			} else {
				// 重试推送
				pushResultService.update(pushResult, wrapper);
			}
			// 更新推送次数
			userMessage.setPushCount(userMessage.getPushCount() + 1);
			super.updateById(userMessage);
		} catch (Exception e) {
			log.error("wechat message push error", e);
			// 设置重试次数
			pushResult.setPushResult(MessagePushConst.MESSAGE_SEND_FAIL);
			if (Objects.isNull(pushResultDb)) {
				// 初次推送
				pushResult.setRetryTime(1);
				pushResultService.save(pushResult);
			} else {
				// 重试推送
				pushResult.setRetryTime(pushResultDb.getRetryTime());
				pushResultService.update(pushResult, wrapper);
			}
		}
	}


	
}
