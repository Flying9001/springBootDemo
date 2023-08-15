package com.ljq.demo.springboot.knife4j.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljq.demo.springboot.knife4j.common.api.ApiResult;
import com.ljq.demo.springboot.knife4j.model.entity.UserMessageEntity;
import com.ljq.demo.springboot.knife4j.model.param.usermessage.*;

/**
 * 用户消息业务层接口
 * 
 * @author junqiang.lu
 * @date 2023-08-15 10:41:29
 */
public interface UserMessageService extends IService<UserMessageEntity> {

	/**
     * 保存(单条)
     *
     * @param saveParam
     * @return
     */
	ApiResult save(UserMessageSaveParam saveParam);

	/**
     * 查询详情(单条)
     *
     * @param infoParam
     * @return
     */
	ApiResult info(UserMessageInfoParam infoParam);

	/**
     * 查询列表
     *
     * @param listParam
     * @return
     */
	ApiResult list(UserMessageListParam listParam);

	/**
     * 更新(单条)
     *
     * @param updateParam
     * @return
     */
	ApiResult update(UserMessageUpdateParam updateParam);

	/**
     * 删除(单条)
     *
     * @param deleteParam
     * @return
     */
	ApiResult delete(UserMessageDeleteParam deleteParam);


}
