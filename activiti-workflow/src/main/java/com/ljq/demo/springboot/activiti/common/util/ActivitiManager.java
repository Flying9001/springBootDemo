package com.ljq.demo.springboot.activiti.common.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import com.ljq.demo.springboot.activiti.dao.ActivitiDeployDao;
import com.ljq.demo.springboot.activiti.model.vo.HistoricTaskInstanceVO;
import com.ljq.demo.springboot.activiti.model.vo.TaskVO;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: Activiti 流程管理
 * @Author: junqiang.lu
 * @Date: 2020/7/9
 */
public class ActivitiManager {

    private static volatile ProcessEngine processEngine;

    /**
     * 启动流程
     *
     * @param processFile
     * @param processKey
     * @param deployDao
     * @param businessKey
     * @param variable
     * @return
     */
    public static ProcessInstance startProcess(String processFile, String processKey, ActivitiDeployDao deployDao,
                                               String businessKey,Map<String, Object> variable) {
        // 判断是否部署过
        int count = deployDao.checkDeployed(processFile);
        if (count < 1) {
            deployProcess(processFile);
        }
        // 初始化流程引擎,并且启动流程
        ProcessInstance instance = init().getRuntimeService().startProcessInstanceByKey(processKey,businessKey,variable);
        return instance;
    }

    /**
     * 查询单个流程的历史记录
     *
     * @param processKey
     * @param businessKey
     * @return
     */
    public static List<HistoricTaskInstanceVO> queryHistoryTask(String processKey, String businessKey) {
        List<HistoricTaskInstance> historicTaskInstanceList = init().getHistoryService()
                .createHistoricTaskInstanceQuery()
                .processDefinitionKey(processKey)
                .processInstanceBusinessKey(businessKey)
                .list();
        List<HistoricTaskInstanceVO> taskInstanceVOList = new ArrayList<>();
        historicTaskInstanceList.stream().forEach(taskInstance -> {
            HistoricTaskInstanceVO taskInstanceVO = new HistoricTaskInstanceVO();
            BeanUtil.copyProperties(taskInstance, taskInstanceVO, CopyOptions.create().ignoreError().ignoreNullValue());
            List<Comment> commentList = init().getTaskService().getTaskComments(taskInstance.getId());
            if (CollectionUtil.isNotEmpty(commentList)) {
                taskInstanceVO.setComment(commentList.get(0).getFullMessage());
            }
            taskInstanceVOList.add(taskInstanceVO);
        });
        return taskInstanceVOList;
    }

    /**
     * 查询流程实例
     *
     * @param processKey
     * @param businessKey
     * @return
     */
    public static ProcessInstance queryProcessInstance(String processKey,String businessKey) {
        ProcessInstance processInstance = init().getRuntimeService()
                .createProcessInstanceQuery()
                .processDefinitionKey(processKey)
                .processInstanceBusinessKey(businessKey)
                .singleResult();
        return processInstance;
    }

    /**
     * 查询当前代理人在当前流程中待办理任务(列表)
     *
     * @param processKey
     * @param assignee
     * @return
     */
    public static List<TaskVO> queryTaskList(String processKey, String assignee) {
        List<Task> taskList = init().getTaskService()
                .createTaskQuery()
                .processDefinitionKey(processKey)
                .taskAssignee(assignee)
                .list();
        List<TaskVO> taskVOList = new ArrayList<>();
        taskList.stream().forEach(task -> {
            TaskVO taskVO = new TaskVO();
            BeanUtil.copyProperties(task, taskVO);
            taskVOList.add(taskVO);
        });
        return taskVOList;
    }

    /**
     * 完成当前节点任务
     *
     * @param taskId
     * @param processInstanceId
     * @param comment
     */
    public static void complete(String taskId, String processInstanceId, String comment) {
        TaskService taskService = init().getTaskService();
        taskService.addComment(taskId, processInstanceId, comment);
        taskService.complete(taskId);
    }

    /**
     * 删除流程实例(包括流程历史)
     *
     * @param processInstanceId
     * @param deleteReason
     */
    public static void deleteProcessInstance(String processInstanceId, String deleteReason) {
        init().getRuntimeService().deleteProcessInstance(processInstanceId, deleteReason);
    }

    /**
     * 初始化
     */
    private static ProcessEngine init() {
        if (processEngine == null) {
            synchronized (ActivitiManager.class) {
                if (processEngine == null) {
                    processEngine = ProcessEngines.getDefaultProcessEngine();
                    System.out.println("初始化流程引擎");
                }
            }
        }
        return processEngine;
    }

    /**
     * 部署流程
     *
     * @param processFile
     * @return
     */
    private static Deployment deployProcess(String processFile) {
        Deployment deployment = init().getRepositoryService()
                .createDeployment()
                .addClasspathResource(processFile)
                .deploy();
        return deployment;
    }

}
