package com.ljq.demo.springboot.activiti.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 请假信息参数接收类
 *
 * @author junqiang.lu
 * @date 2020-07-09 16:25:39
 */
@Data
@ApiModel(value = "请假信息查询详情(单条)", description = "请假信息查询详情(单条)")
public class LeaveInfoInfoParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     * */
    @NotNull(message = "编号 不能为空")
    @ApiModelProperty(value = "编号", name = "id", required = true)
    private String id;

}
