package com.ljq.demo.springboot.swagger3.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljq.demo.springboot.swagger3.model.entity.AdminUserEntity;
import com.ljq.demo.springboot.swagger3.model.param.*;

/**
 * 管理员用户业务层接口
 * 
 * @author junqiang.lu
 * @date 2021-01-25 19:33:17
 */
public interface AdminUserService {

	/**
     * 新增(单条)
     *
     * @param saveParam
     * @return
     */
	AdminUserEntity save(AdminUserSaveParam saveParam);

	/**
     * 查询详情(单条)
     *
     * @param infoParam
     * @return
     */
	AdminUserEntity info(AdminUserInfoParam infoParam);

	/**
     * 分页查询
     *
     * @param listParam
     * @return
     */
	IPage<AdminUserEntity> page(AdminUserListParam listParam);

	/**
     * 更新(单条)
     *
     * @param updateParam
     * @return
     */
	void update(AdminUserUpdateParam updateParam);

	/**
     * 删除(单条)
     *
     * @param deleteParam
     * @return
     */
	void delete(AdminUserDeleteParam deleteParam);

	/**
     * 批量删除
     *
     * @param deleteBatchParam
     * @return
     */
	void deleteBatch(AdminUserDeleteBatchParam deleteBatchParam);



}
