package com.ljq.demo.springboot.activiti.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.ljq.demo.springboot.activiti.common.constant.ActivitiConst;
import com.ljq.demo.springboot.activiti.common.util.ActivitiManager;
import com.ljq.demo.springboot.activiti.dao.ActivitiDeployDao;
import com.ljq.demo.springboot.activiti.dao.LeaveInfoDao;
import com.ljq.demo.springboot.activiti.model.entity.LeaveInfoEntity;
import com.ljq.demo.springboot.activiti.model.param.*;
import com.ljq.demo.springboot.activiti.model.vo.HistoricTaskInstanceVO;
import com.ljq.demo.springboot.activiti.model.vo.TaskVO;
import com.ljq.demo.springboot.activiti.service.LeaveInfoService;
import com.ljq.demo.springboot.baseweb.api.ApiResult;
import com.ljq.demo.springboot.baseweb.api.ResponseCode;
import com.ljq.demo.springboot.common.page.PageUtil;
import com.ljq.demo.springboot.common.page.QueryUtil;
import com.ljq.demo.springboot.common.util.IDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 请假信息业务层具体实现类
 *
 * @author junqiang.lu
 * @date 2020-07-09 16:25:39
 */
@Service("leaveInfoService")
@Transactional(rollbackFor = {Exception.class})
@Slf4j
public class LeaveInfoServiceImpl implements LeaveInfoService {

	@Autowired
	private LeaveInfoDao leaveInfoDao;
	@Autowired
	private ActivitiDeployDao deployDao;


	/**
	 * 新增(单条)
	 *
	 * @param leaveInfoAddParam
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public ApiResult add(LeaveInfoAddParam leaveInfoAddParam) throws Exception {
		// 请求参数获取
		LeaveInfoEntity leaveInfoParam = new LeaveInfoEntity();
		BeanUtil.copyProperties(leaveInfoAddParam, leaveInfoParam, CopyOptions.create()
				.ignoreNullValue().ignoreError());
		String id = IDGenerator.getID();
		leaveInfoParam.setId(id);
		// 保存
		leaveInfoDao.save(leaveInfoParam);
		// 指定节点办理人
		Map<String, Object> variable = new HashMap<>(16);
		variable.put("student", leaveInfoAddParam.getStudentId());
		variable.put("teacher", leaveInfoAddParam.getTeacherId());
		variable.put("teacherName", "张三丰");
		ProcessInstance instance = ActivitiManager.startProcess(ActivitiConst.STUDENT_LEAVE_PROCESS_FILE,
				ActivitiConst.STUDENT_LEAVE_PROCESS_KEY, deployDao, id, variable);

		return ApiResult.success(id);
	}

	/**
	 * 查询详情(单条)
	 *
	 * @param leaveInfoInfoParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public ApiResult info(LeaveInfoInfoParam leaveInfoInfoParam) throws Exception {
		// 查询
		LeaveInfoEntity leaveInfoDB = leaveInfoDao.queryObject(leaveInfoInfoParam.getId());
		if (Objects.isNull(leaveInfoDB)) {
			return ApiResult.success();
		}
		List<HistoricTaskInstanceVO> historicTaskInstanceVOList = ActivitiManager
				.queryHistoryTask(ActivitiConst.STUDENT_LEAVE_PROCESS_KEY, leaveInfoDB.getId());
		Map<String, Object> extraDataMap = new HashMap<>(16);
		extraDataMap.put("history", historicTaskInstanceVOList);

		return ApiResult.success(leaveInfoDB, extraDataMap);
	}

	/**
	 * 查询待审批列表
	 *
	 * @param jobListParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public ApiResult jobList(LeaveInfoJobListParam jobListParam) throws Exception {
		List<TaskVO> taskVOList = ActivitiManager.queryTaskList(ActivitiConst.STUDENT_LEAVE_PROCESS_KEY, jobListParam.getUserId());
		return ApiResult.success(taskVOList);
	}

	/**
	 * 完成审批
	 *
	 * @param approvalParam
	 * @return
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	@Override
	public ApiResult approval(LeaveInfoApprovalParam approvalParam) throws Exception {
		List<TaskVO> taskVOList = ActivitiManager.queryTaskList(ActivitiConst.STUDENT_LEAVE_PROCESS_KEY,
				approvalParam.getUserId());
		if (taskVOList.stream().filter(task -> Objects.equals(task.getId(), approvalParam.getTaskId()))
				.findFirst().isPresent()) {
			ActivitiManager.complete(approvalParam.getTaskId(), approvalParam.getProcessInstanceId(),
					approvalParam.getComment());
		} else {
			return ApiResult.failure(ResponseCode.LEAVE_INFO_WORKFLOW_TASK_NOT_EXIST);
		}
		return ApiResult.success();
	}

	/**
	 * 查询列表
	 *
	 * @param leaveInfoListParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public ApiResult list(LeaveInfoListParam leaveInfoListParam) throws Exception {
		// 请求参数获取
		QueryUtil queryMap = new QueryUtil(BeanUtil.beanToMap(leaveInfoListParam));
		// 分页查询
		PageUtil<LeaveInfoEntity> pageUtil;
		int countTotal = leaveInfoDao.queryCount(queryMap);
		if (countTotal > 0) {
			List<LeaveInfoEntity> entityList = leaveInfoDao.queryList(queryMap);
			pageUtil = new PageUtil<>(entityList, countTotal, queryMap.getPageSize(), queryMap.getCurrentPage());
		} else {
			pageUtil = new PageUtil<>(Collections.emptyList(), countTotal, queryMap.getPageSize(), queryMap.getCurrentPage());
		}
		return ApiResult.success(pageUtil);
	}

	/**
	 * 更新(单条)
	 *
	 * @param leaveInfoUpdateParam
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public ApiResult update(LeaveInfoUpdateParam leaveInfoUpdateParam) throws Exception {
		// 请求参数获取
		LeaveInfoEntity leaveInfoParam = new LeaveInfoEntity();

		// 判断对象是否存在

		// 更新对象

		return ApiResult.success();
	}

	/**
	 * 删除(单条)
	 *
	 * @param leaveInfoDeleteParam
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public ApiResult delete(LeaveInfoDeleteParam leaveInfoDeleteParam) throws Exception {
		LeaveInfoEntity leaveInfoDB = leaveInfoDao.queryObject(leaveInfoDeleteParam.getId());
		if (Objects.isNull(leaveInfoDB) || !Objects.equals(leaveInfoDB.getStudentId(),
				leaveInfoDeleteParam.getUserId())) {
			return ApiResult.failure(ResponseCode.LEAVE_INFO_NOT_EXIST);
		}
		// 查询请假流程实例
		ProcessInstance processInstance = ActivitiManager.queryProcessInstance(ActivitiConst.STUDENT_LEAVE_PROCESS_KEY,
				leaveInfoDeleteParam.getId());
		// 删除请假流程实例
		ActivitiManager.deleteProcessInstance(processInstance.getId(), leaveInfoDeleteParam.getDeleteReason());
		// 删除请假信息
		leaveInfoDao.delete(leaveInfoDB);

		return ApiResult.success();
	}

	/**
	 * 批量删除
	 *
	 * @param leaveInfoDeleteBatchParam
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public ApiResult deleteBatch(LeaveInfoDeleteBatchParam leaveInfoDeleteBatchParam) throws Exception {
		// 请求参数获取
			LeaveInfoEntity leaveInfoParam = new LeaveInfoEntity();
		// 判断对象是否存在

		// 更新对象

		return ApiResult.success();
	}

	

	
}