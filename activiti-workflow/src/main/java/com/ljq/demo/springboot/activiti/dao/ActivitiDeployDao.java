package com.ljq.demo.springboot.activiti.dao;

import org.springframework.stereotype.Repository;

/**
 * @Description: Activiti 工作流部署信息DAO
 * @Author: junqiang.lu
 * @Date: 2020/7/9
 */
@Repository
public interface ActivitiDeployDao {

    /**
     * 校验工作流程是否部署
     *
     * @param processFile
     * @return
     */
    int checkDeployed(String processFile);
}
