package com.ljq.demo.springboot.activiti.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 任务对象
 * @Author: junqiang.lu
 * @Date: 2020/7/10
 */
@Data
@ApiModel(value = "任务对象", description = "任务对象")
public class TaskVO implements Serializable {

    private static final long serialVersionUID = 4609219444447438575L;
    /**
     * 任务编号
     */
    @ApiModelProperty(value = "任务编号", name = "id")
    private String id;
    /**
     * 流程实例编号
     */
    @ApiModelProperty(value = "流程实例编号", name = "processInstanceId")
    private String processInstanceId;
    /**
     * 任务名称
     */
    @ApiModelProperty(value = "任务名称", name = "name")
    private String name;
    /**
     * 代理人
     */
    @ApiModelProperty(value = "任务代理人", name = "assignee")
    private String assignee;
    /**
     * 父级任务编号
     */
    @ApiModelProperty(value = "父级任务编号", name = "parentTaskId")
    private String parentTaskId;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createTime")
    private Date createTime;
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





}
