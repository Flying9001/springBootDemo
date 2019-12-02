package com.ljq.demo.springboot.service;

import com.ljq.demo.springboot.common.api.ApiResult;
import com.ljq.demo.springboot.vo.restuser.*;

/**
 * REST示例-用户表业务层接口
 * 
 * @author junqiang.lu
 * @date 2019-09-19 17:19:42
 */
public interface RestUserService {

	/**
     * 保存(单条)
     *
     * @param restUserSaveParam
     * @return
     * @throws Exception
     */
	ApiResult save(RestUserSaveParam restUserSaveParam) throws Exception;

	/**
     * 查询详情(单条)
     *
     * @param restUserInfoParam
     * @return
     * @throws Exception
     */
	ApiResult info(RestUserInfoParam restUserInfoParam) throws Exception;

	/**
     * 查询列表
     *
     * @param restUserListParam
     * @return
     * @throws Exception
     */
	ApiResult list(RestUserListParam restUserListParam) throws Exception;

	/**
     * 更新(单条)
     *
     * @param restUserUpdateParam
     * @return
     * @throws Exception
     */
	ApiResult update(RestUserUpdateParam restUserUpdateParam) throws Exception;

	/**
     * 删除(单条)
     *
     * @param restUserDeleteParam
     * @return
     * @throws Exception
     */
	ApiResult delete(RestUserDeleteParam restUserDeleteParam) throws Exception;


}
