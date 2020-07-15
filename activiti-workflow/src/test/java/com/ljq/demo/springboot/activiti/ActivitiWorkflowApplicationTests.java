package com.ljq.demo.springboot.activiti;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

//@SpringBootTest
class ActivitiWorkflowApplicationTests {


    /**
     * 创建流程引擎(通过代码的方式)
     */
    @Test
    public void createActivitiEngineByCode() {
        ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration();
        engineConfiguration.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        engineConfiguration.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/demo_activiti?useUnicode=true&characterEncoding" +
                "=utf8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8&useSSL" +
                "=true&allowMultiQueries=true&autoReconnect=true&nullCatalogMeansCurrent=true");
        engineConfiguration.setJdbcUsername("root");
        engineConfiguration.setJdbcPassword("root");

        /**
         * false: 不会自动创建表，没有表，则抛异常
         * true: 假如没有表，则自动创建
         * create-drop: 流程开始的时候创建表,流程结束之后删除表
         */
        engineConfiguration.setDatabaseSchemaUpdate("true");
        ProcessEngine processEngine = engineConfiguration.buildProcessEngine();
        System.out.println("通过代码创建流程引擎成功!!!");
    }

    /**
     * 创建流程引擎(通过配置文件的方式)
     */
    @Test
    public void createActivitiEngineByCfg() {
        ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine = engineConfiguration.buildProcessEngine();
        System.out.println("使用 activiti.cfg.xml 创建流程引擎 !!!");
    }

    /**
     * 创建流程引擎(读取 application.yml 中 activiti 配置)
     */
    @Test
    public void createProcessEngine() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println("通过 ProcessEngines 获取流程引擎");
    }

    /**
     * 部署工作流
     */
    @Test
    public void deploy() {
        // resource 目录下 bpmn 文件
        String bpmnResource = "processes/student_leave.bpmn";
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment()
                .addClasspathResource(bpmnResource)
                .name("studentLeave")
                .category("demoCategory")
                .deploy();
        System.out.println("部署 id: " + deployment.getId());
        System.out.println("部署名称: " + deployment.getName());
        System.out.println("部署策略: " + deployment.getCategory());

    }

    /**
     * 启动工作流
     */
    @Test
    public void startProcess() {
        // 进程 key:bpmn 文件中工作流 id
        String processKey = "student_leave";
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 获取流程实例
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(processKey);
        System.out.println("流程实例 id: " + instance.getId());
        System.out.println("流程定义 id: " + instance.getProcessDefinitionId());
    }

    /**
     * 查询办理人当前流程信息
     */
    @Test
    public void queryTask() {
        // 办理人
        String assignee = "张三";
        // 进程 key:bpmn 文件中工作流 id
        String processKey = "student_leave";
        // 获取任务服务
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        // 创建一个查询任务对象
        TaskQuery taskQuery = taskService.createTaskQuery();
        // 办理人列表
        List<Task> taskList = taskQuery.taskAssignee(assignee).processDefinitionKey(processKey).list();
        // 遍历列表
        taskList.stream().forEach(task -> {
            System.out.println("任务的办理人: " + task.getAssignee());
            System.out.println("任务的 id: " + task.getId());
            System.out.println("任务的名称: " + task.getName());
            System.out.println("任务的所有者: " + task.getOwner());
            System.out.println("任务的策略: " + task.getCategory());
            System.out.println("任务的创建时间: " + task.getCreateTime());
            System.out.println("任务的执行 id: " + task.getExecutionId());
            System.out.println("任务的 kay: " + task.getTaskDefinitionKey());
            System.out.println("任务的流程 id: " + task.getProcessDefinitionId());
            System.out.println("------------------------------");
        });
    }

    /**
     * 完成当前节点流程任务
     */
    @Test
    public void completeTask(){
        String taskId = "2505";
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 完成任务
        processEngine.getTaskService().complete(taskId);
        System.out.println("当前任务执行完毕");

    }

/*  ---------------------- 测试 2 UEL 指定代理人--------------------------*/

    /**
     * 部署工作流
     */
    @Test
    public void deploy2() {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        // 工作流配置文件 resources/processes 下的 bpmn 文件
        String processFile = "processes/student_leave_2.bpmn";
        Deployment deployment = engine.getRepositoryService()
                .createDeployment()
                .addClasspathResource(processFile)
                .name("studentLeave2")
                .category("demoCategory")
                .deploy();
        System.out.println("部署 id: " + deployment.getId());
        System.out.println("部署名称: " + deployment.getName());
        System.out.println("部署策略: " + deployment.getCategory());

    }

    /**
     * 启动工作流
     */
    @Test
    public void startProcess2() {
        // 进程 key: bpmn 文件中工作流 id
        String processKey = "student_leave_2";
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = engine.getRuntimeService();
        // 指定节点办理人
        Map<String, Object> variable = new HashMap<>();
        variable.put("student", "zhangsan");
        variable.put("teacher", "lisi");
        // 获取流程示例
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(processKey, variable);
        System.out.println("流程实例 id: " + instance.getId());
        System.out.println("流程定义名称: " + instance.getProcessDefinitionName());
        System.out.println("流程定义 id: " + instance.getProcessDefinitionId());
        System.out.println("流程业务 key: " + instance.getBusinessKey());
        System.out.println("流程发起人用户 id: " + instance.getStartUserId());

    }

    /**
     * 查询代理人当前流程信息
     */
    @Test
    public void queryTask2() {
        // 办理人
        String assignee = "zhangsan";
        // 进程 key:bpmn 文件中工作流 id
        String processKey = "student_leave_2";
        // 获取任务服务
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = engine.getTaskService();
        // 创建一个查询任务
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> taskList = taskQuery.processDefinitionKey(processKey).taskAssignee(assignee).list();
        if (taskList == null || taskList.isEmpty()) {
            System.out.println("当前代理人[" + assignee + "]没人待执行任务");
        } else {
            // 遍历列表
            taskList.stream().forEach(task -> {
                System.out.println("任务的办理人: " + task.getAssignee());
                System.out.println("任务的 id: " + task.getId());
                System.out.println("任务的名称: " + task.getName());
                System.out.println("任务的所有者: " + task.getOwner());
                System.out.println("任务的策略: " + task.getCategory());
                System.out.println("任务的创建时间: " + task.getCreateTime());
                System.out.println("任务的执行 id: " + task.getExecutionId());
                System.out.println("任务的 kay: " + task.getTaskDefinitionKey());
                System.out.println("任务的流程 id: " + task.getProcessDefinitionId());
                System.out.println("------------------------------");
            });
        }
    }

    /**
     * 完成当前任务
     */
    @Test
    public void completeTask2() {
        // 任务 id
        String taskId = "10002";
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        engine.getTaskService().complete(taskId);
        System.out.println("当前任务[" + taskId +"]执行完毕");
    }


/*--------------------------- 测试 3 监听器指定代理人 ---------------------------*/

    /**
     * 部署流程
     */
    @Test
    public void deploy3() {
        // 流程文件: resource/processes 目录下 bpmn 文件
        String processFile = "processes/student_leave_3.bpmn";
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        Deployment deployment = engine.getRepositoryService()
                .createDeployment()
                .addClasspathResource(processFile)
                .name("studentLeave3")
                .category("demoCategory2")
                .deploy();
        System.out.println("流程部署 id: " + deployment.getId());
        System.out.println("流程部署名称: " + deployment.getName());
        System.out.println("流程部署策略: " + deployment.getCategory());

    }

    /**
     * 启动流程
     */
    @Test
    public void startProcess3() {
        // 流程 key: bpmn 文件中定义的 id
        String processKey = "student_leave_3";
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = engine.getRuntimeService();
        // 获取流程实例
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(processKey);
        System.out.println("流程实例 id: " + instance.getId());
        System.out.println("流程定义名称: " + instance.getProcessDefinitionName());
        System.out.println("流程定义 id: " + instance.getProcessDefinitionId());
        System.out.println("流程定义 key: " + instance.getProcessDefinitionKey());
        System.out.println("流程业务 key: " + instance.getBusinessKey());

    }

    /**
     * 查询代理人当前执行的任务节点
     */
    @Test
    public void taskQuery3() {
        // 代理人
        String assignee = "zhangsan";
        // 流程 key: bpmn 文件中定义的 id
        String processKey = "student_leave_3";
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = engine.getTaskService();
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey(processKey)
                .taskAssignee(assignee)
                .list();
        if (taskList == null || taskList.isEmpty()) {
            System.out.println("当前代理人[" + assignee + "]没有待执行任务");
        } else {
            // 遍历列表
            taskList.stream().forEach(task -> {
                System.out.println("任务的办理人: " + task.getAssignee());
                System.out.println("任务的 id: " + task.getId());
                System.out.println("流程实例 id: " + task.getProcessInstanceId());
                System.out.println("任务的名称: " + task.getName());
                System.out.println("任务的所有者: " + task.getOwner());
                System.out.println("任务的策略: " + task.getCategory());
                System.out.println("任务的创建时间: " + task.getCreateTime());
                System.out.println("任务的执行 id: " + task.getExecutionId());
                System.out.println("任务的 kay: " + task.getTaskDefinitionKey());
                System.out.println("任务的流程 id: " + task.getProcessDefinitionId());
                System.out.println("------------------------------");
            });
        }
    }

    /**
     * 查询流程实例列表
     */
    @Test
    public void processInstanceQuery3() {
        String processKey = "student_leave_2";
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        List<ProcessInstance> processInstanceList = engine.getRuntimeService()
                .createProcessInstanceQuery()
                .processDefinitionKey(processKey)
                .list();
        processInstanceList.stream().forEach(processInstance -> {
            System.out.println("流程定义id:" + processInstance.getProcessDefinitionId());
            System.out.println("流程定义名称: " + processInstance.getProcessDefinitionName());
            System.out.println("流程实例 id: " + processInstance.getId());
            System.out.println("流程实例关联的业务 id: " + processInstance.getBusinessKey());
            System.out.println("流程开始时间: " + processInstance.getStartTime());
            System.out.println("流程发起人用户 id: " + processInstance.getStartUserId());
        });
    }

    /**
     * 完成当前任务
     */
    @Test
    public void completeTask3() {
        // 任务 id
        String taskId = "25005";
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        engine.getTaskService().complete(taskId);
        System.out.println("当前任务[" + taskId + "]执行完成完成");
    }

    /**
     * 查询历史记录
     */
    @Test
    public void queryHistory3() {
        // 流程 id
        String processId = "student_leave_3:1:7504";
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        List<HistoricTaskInstance> historyList = engine.getHistoryService()
                .createHistoricTaskInstanceQuery()
                .processDefinitionId(processId)
                .list();
        historyList.stream().forEach(history -> {
            System.out.println("任务的办理人: " + history.getAssignee());
            System.out.println("任务的 id: " + history.getId());
            System.out.println("流程实例 id: " + history.getProcessInstanceId());
            System.out.println("任务的名称: " + history.getName());
            System.out.println("任务的所有者: " + history.getOwner());
            System.out.println("任务的策略: " + history.getCategory());
            System.out.println("任务的创建时间: " + history.getCreateTime());
            System.out.println("任务的执行 id: " + history.getExecutionId());
            System.out.println("任务的 kay: " + history.getTaskDefinitionKey());
            System.out.println("任务的流程 id: " + history.getProcessDefinitionId());
            System.out.println("------------------------------");

        });
    }

    @Test
    public void deleteTask3() {
        // 流程任务 id
        String taskId = "12505";
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        engine.getTaskService().deleteTask(taskId);
    }

    /**
     * 删除流程实例(包括历史记录)
     */
    @Test
    public void deleteProcessInstance3() {
        // 流程实例 id
        String processInstanceId = "10001";
        // 删除原因
        String deleteReason = "需要删除";
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        ProcessInstance processInstance = engine.getRuntimeService().createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        if (Objects.isNull(processInstance)) {
            engine.getHistoryService().deleteHistoricProcessInstance(processInstanceId);
        } else {
            engine.getRuntimeService().deleteProcessInstance(processInstanceId, deleteReason);
            engine.getHistoryService().deleteHistoricProcessInstance(processInstanceId);
        }

    }

    /**
     * 删除已经部署的所有流程(包括所有的关联信息,基本相当于清空数据库)
     */
    @Test
    public void deleteDeploy3() {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        List<Deployment> deploymentList = engine.getRepositoryService().createDeploymentQuery().list();
        deploymentList.stream().forEach(deployment -> {
            engine.getRepositoryService().deleteDeployment(deployment.getId(), true);
        });
    }





}
