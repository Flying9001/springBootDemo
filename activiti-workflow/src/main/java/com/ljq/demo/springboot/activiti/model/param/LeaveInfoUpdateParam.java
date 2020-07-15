package com.ljq.demo.springboot.activiti.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 请假信息参数接收类
 *
 * @author junqiang.lu
 * @date 2020-07-09 16:25:39
 */
@Data
@ApiModel(value = "请假信息修改(单条)", description = "请假信息修改(单条)")
public class LeaveInfoUpdateParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     * */
    @NotNull(message = "编号 不能为空")
    @ApiModelProperty(value = "编号", name = "id", required = true)
    private String id;
    /**
     * 学生姓名
     * */
    @NotNull(message = "学生姓名 不能为空")
    @ApiModelProperty(value = "学生姓名", name = "studentName", required = true)
    private String studentName;
    /**
     * 学生编号
     * */
    @NotNull(message = "学生编号 不能为空")
    @ApiModelProperty(value = "学生编号", name = "studentId", required = true)
    private String studentId;
    /**
     * 请假原因
     * */
    @NotNull(message = "请假原因 不能为空")
    @ApiModelProperty(value = "请假原因", name = "leaveReason", required = true)
    private String leaveReason;
    /**
     * 请假时长(单位:天)
     * */
    @NotNull(message = "请假时长(单位:天) 不能为空")
    @Min(value = 1, message = "请假时长(单位:天) 至少为 1")
    @ApiModelProperty(value = "请假时长(单位:天) 不能为空,至少为 1", name = "leaveDuration", required = true, example = "0")
    private Integer leaveDuration;
    /**
     * 老师姓名
     * */
    @NotNull(message = "老师姓名 不能为空")
    @ApiModelProperty(value = "老师姓名", name = "teacherName", required = true)
    private String teacherName;
    /**
     * 老师编号
     * */
    @NotNull(message = "老师编号 不能为空")
    @ApiModelProperty(value = "老师编号", name = "teacherId", required = true)
    private String teacherId;
    /**
     * 教师审批意见
     * */
    @NotNull(message = "教师审批意见 不能为空")
    @ApiModelProperty(value = "教师审批意见", name = "teacherOption", required = true)
    private String teacherOption;


}
