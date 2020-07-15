package com.ljq.demo.springboot.activiti.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 请假信息实体类
 *
 * @author junqiang.lu
 * @date 2020-07-09 16:25:39
 */
@Data
@ApiModel(value = "请假信息", description = "请假信息")
public class LeaveInfoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
	 * 编号
	 * */
	@ApiModelProperty(value = "编号", name = "id")
	private String id;
    /**
	 * 学生姓名
	 * */
	@ApiModelProperty(value = "学生姓名", name = "studentName")
	private String studentName;
    /**
	 * 学生编号
	 * */
	@ApiModelProperty(value = "学生编号", name = "studentId")
	private String studentId;
    /**
	 * 请假原因
	 * */
	@ApiModelProperty(value = "请假原因", name = "leaveReason")
	private String leaveReason;
    /**
	 * 请假时长(单位:天)
	 * */
	@ApiModelProperty(value = "请假时长(单位:天)", name = "leaveDuration")
	private Integer leaveDuration;

}
