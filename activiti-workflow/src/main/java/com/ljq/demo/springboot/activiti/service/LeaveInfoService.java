package com.ljq.demo.springboot.activiti.service;

import com.ljq.demo.springboot.activiti.model.param.*;
import com.ljq.demo.springboot.baseweb.api.ApiResult;

/**
 * 请假信息业务层接口
 * 
 * @author junqiang.lu
 * @date 2020-07-09 16:25:39
 */
public interface LeaveInfoService {

	/**
     * 新增(单条)
     *
     * @param leaveInfoAddParam
     * @return
     * @throws Exception
     */
	ApiResult add(LeaveInfoAddParam leaveInfoAddParam) throws Exception;

	/**
     * 查询详情(单条)
     *
     * @param leaveInfoInfoParam
     * @return
     * @throws Exception
     */
	ApiResult info(LeaveInfoInfoParam leaveInfoInfoParam) throws Exception;


	/**
	 * 教查询待审批列表
	 *
	 * @param jobListParam
	 * @return
	 * @throws Exception
	 */
	ApiResult jobList(LeaveInfoJobListParam jobListParam) throws Exception;

	/**
	 * 完成审批
	 *
	 * @param approvalParam
	 * @return
	 * @throws Exception
	 */
	ApiResult approval(LeaveInfoApprovalParam approvalParam) throws Exception;

	/**
     * 查询列表
     *
     * @param leaveInfoListParam
     * @return
     * @throws Exception
     */
	ApiResult list(LeaveInfoListParam leaveInfoListParam) throws Exception;

	/**
     * 更新(单条)
     *
     * @param leaveInfoUpdateParam
     * @return
     * @throws Exception
     */
	ApiResult update(LeaveInfoUpdateParam leaveInfoUpdateParam) throws Exception;

	/**
     * 删除(单条)
     *
     * @param leaveInfoDeleteParam
     * @return
     * @throws Exception
     */
	ApiResult delete(LeaveInfoDeleteParam leaveInfoDeleteParam) throws Exception;

	/**
	 * 批量删除
	 *
	 * @param leaveInfoDeleteBatchParam
	 * @return
	 * @throws Exception
	 */
	ApiResult deleteBatch(LeaveInfoDeleteBatchParam leaveInfoDeleteBatchParam) throws Exception;


}
