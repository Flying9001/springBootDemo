package com.ljq.demo.springboot.mongodb.service;

import com.ljq.demo.springboot.mongodb.model.entity.UserEntity;
import com.ljq.demo.springboot.mongodb.model.param.*;
import org.springframework.data.domain.Page;

/**
 * 业务层接口
 * 
 * @author junqiang.lu
 * @date 2021-01-06 20:03:33
 */
public interface UserService {

	/**
     * 保存(单条)
     *
     * @param userSaveParam
     * @return
     */
	UserEntity save(UserSaveParam userSaveParam);

	/**
     * 查询详情(单条)
     *
     * @param userInfoParam
     * @return
     */
	UserEntity info(UserInfoParam userInfoParam);

	/**
     * 查询列表
     *
     * @param userListParam
     * @return
     */
	Page<UserEntity> list(UserListParam userListParam);

	/**
     * 更新(单条)
     *
     * @param userUpdateParam
     * @return
     */
	UserEntity update(UserUpdateParam userUpdateParam);

	/**
     * 删除(单条)
     *
     * @param userDeleteParam
     * @return
     */
	void delete(UserDeleteParam userDeleteParam);

	/**
	 * 批量删除
	 *
	 * @param deleteBatchParam
	 */
	void deleteBatch(UserDeleteBatchParam deleteBatchParam);


}
