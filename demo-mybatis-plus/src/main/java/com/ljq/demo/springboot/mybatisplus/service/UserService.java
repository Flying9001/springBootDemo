package com.ljq.demo.springboot.mybatisplus.service;

import com.ljq.demo.springboot.mybatisplus.common.api.ApiResult;
import com.ljq.demo.springboot.mybatisplus.model.param.user.*;

/**
 * 用户表业务层接口
 * 
 * @author junqiang.lu
 * @date 2020-08-31 14:09:53
 */
public interface UserService {

	/**
     * 保存(单条)
     *
     * @param userSaveParam
     * @return
     * @throws Exception
     */
	ApiResult save(UserSaveParam userSaveParam) throws Exception;

	/**
     * 查询详情(单条)
     *
     * @param userInfoParam
     * @return
     * @throws Exception
     */
	ApiResult info(UserInfoParam userInfoParam) throws Exception;

	/**
     * 查询列表
     *
     * @param userListParam
     * @return
     * @throws Exception
     */
	ApiResult list(UserListParam userListParam) throws Exception;

	/**
     * 更新(单条)
     *
     * @param userUpdateParam
     * @return
     * @throws Exception
     */
	ApiResult update(UserUpdateParam userUpdateParam) throws Exception;

	/**
     * 删除(单条)
     *
     * @param userDeleteParam
     * @return
     * @throws Exception
     */
	ApiResult delete(UserDeleteParam userDeleteParam) throws Exception;


}
