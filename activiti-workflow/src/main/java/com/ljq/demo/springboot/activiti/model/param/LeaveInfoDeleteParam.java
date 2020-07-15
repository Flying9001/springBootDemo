package com.ljq.demo.springboot.activiti.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 请假信息参数接收类
 *
 * @author junqiang.lu
 * @date 2020-07-09 16:25:39
 */
@Data
@ApiModel(value = "请假信息删除(单条)", description = "请假信息删除(单条)")
public class LeaveInfoDeleteParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     * */
    @NotNull(message = "编号 不能为空")
    @ApiModelProperty(value = "编号", name = "id", required = true)
    private String id;
    /**
     * 用户 id
     */
    @NotBlank(message = "请输入用户信息")
    @ApiModelProperty(value = "用户 id", name = "userId", required = true)
    private String userId;
    /**
     * 删除原因
     */
    @Length(max = 50, message = "删除原因需要控制在 50 字符以内")
    @ApiModelProperty(value = "删除原因", name = "deleteReason")
    private String deleteReason;


}
