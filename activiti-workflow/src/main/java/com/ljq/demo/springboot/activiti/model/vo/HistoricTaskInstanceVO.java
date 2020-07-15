package com.ljq.demo.springboot.activiti.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 流程历史记录
 * @Author: junqiang.lu
 * @Date: 2020/7/13
 */
@Data
@ApiModel(value = "流程历史记录", description = "流程历史记录")
public class HistoricTaskInstanceVO implements Serializable {

    private static final long serialVersionUID = 1832078627883965643L;

    /**
     * 任务编号
     */
    @ApiModelProperty(value = "任务编号", name = "id")
    private String id;
    /**
     * 父级任务编号
     */
    @ApiModelProperty(value = "父级任务编号", name = "parentTaskId")
    private String parentTaskId;
    /**
     * 流程实例编号
     */
    private String processInstanceId;
    /**
     * 流程定义 id
     */
    private String processDefinitionId;
    /**
     * 任务名称
     */
    private String name;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 代理人
     */
    @ApiModelProperty(value = "任务代理人", name = "assignee")
    private String assignee;
    /**
     * 所有者
     */
    @ApiModelProperty(value = "所有者", name = "owner")
    private String owner;
    /**
     * 策略
     */
    @ApiModelProperty(value = "策略", name = "category")
    private String category;
    /**
     * 审批意见
     */
    private String comment;


}
