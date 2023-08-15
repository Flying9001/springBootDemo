package com.ljq.demo.springboot.knife4j.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljq.demo.springboot.knife4j.common.api.ApiResult;
import com.ljq.demo.springboot.knife4j.model.entity.UserPushTypeEntity;
import com.ljq.demo.springboot.knife4j.model.param.userpushtype.*;

/**
 * 用户消息推送方式业务层接口
 * 
 * @author junqiang.lu
 * @date 2023-08-15 10:32:24
 */
public interface UserPushTypeService extends IService<UserPushTypeEntity> {

	/**
     * 保存(单条)
     *
     * @param saveParam
     * @return
     */
	ApiResult save(UserPushTypeSaveParam saveParam);

	/**
     * 查询详情(单条)
     *
     * @param infoParam
     * @return
     */
	ApiResult info(UserPushTypeInfoParam infoParam);

	/**
     * 查询列表
     *
     * @param listParam
     * @return
     */
	ApiResult list(UserPushTypeListParam listParam);

	/**
     * 更新(单条)
     *
     * @param updateParam
     * @return
     */
	ApiResult update(UserPushTypeUpdateParam updateParam);

	/**
     * 删除(单条)
     *
     * @param deleteParam
     * @return
     */
	ApiResult delete(UserPushTypeDeleteParam deleteParam);


}
