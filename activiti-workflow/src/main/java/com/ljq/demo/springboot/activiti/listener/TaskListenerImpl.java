package com.ljq.demo.springboot.activiti.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Description: 流程任务监听器
 * @Author: junqiang.lu
 * @Date: 2020/7/8
 */
public class TaskListenerImpl implements TaskListener {

    @Value("${server.port}")
    private String port;

    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("port: " + port);
        // 设置代理人
        delegateTask.setAssignee("zhangsan");
    }
}
