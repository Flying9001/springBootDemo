package com.ljq.demo.springboot.activiti.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description: 审批
 * @Author: junqiang.lu
 * @Date: 2020/7/10
 */
@Data
@ApiModel(value = "审批", description = "审批")
public class LeaveInfoApprovalParam implements Serializable {

    private static final long serialVersionUID = 5799819560666442798L;

    /**
     * 任务 id
     * */
    @NotBlank(message = "请选择需要审批的任务")
    @ApiModelProperty(value = "任务 id", name = "taskId", required = true)
    private String taskId;
    /**
     * 流程实例 id
     */
    @NotBlank(message = "请选择需要审批的流程")
    @ApiModelProperty(value = "流程实例 id", name = "processInstanceId", required = true)
    private String processInstanceId;
    /**
     * 用户 id
     */
    @NotBlank(message = "请输入用户信息")
    @ApiModelProperty(value = "用户 id", name = "userId", required = true)
    private String userId;
    /**
     * 审批意见
     */
    @Length(max = 50, message = "审批意见需要控制在 50 字符以内")
    @ApiModelProperty(value = "审批意见", name = "comment")
    private String comment;

}
