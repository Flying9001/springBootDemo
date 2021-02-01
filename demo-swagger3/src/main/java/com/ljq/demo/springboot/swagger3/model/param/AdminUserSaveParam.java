package com.ljq.demo.springboot.swagger3.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * 管理员用户新增(单条)
 *
 * @author junqiang.lu
 * @date 2021-01-25 19:23:35
 */
@Data
@ApiModel(value = "管理员用户新增(单条)", description = "管理员用户新增(单条)")
public class AdminUserSaveParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     * */
    @NotBlank(message = "名称 不能为空")
    @ApiModelProperty(value = "名称", name = "name", required = true)
    private String name;
    /**
     * 邮箱,账号
     * */
    @NotBlank(message = "邮箱,账号 不能为空")
    @Email(message = "邮箱格式错误")
    @ApiModelProperty(value = "邮箱,账号", name = "email", required = true)
    private String email;
    /**
     * 登录密码
     * */
    @NotBlank(message = "登录密码 不能为空")
    @ApiModelProperty(value = "登录密码", name = "passcode", required = true)
    private String passcode;
    /**
     * 是否启用,0-未启用,1-启用
     * */
    @NotNull(message = "请设置是否启用")
    @Min(value = 0, message = "是否启用设置错误")
    @Max(value = 1, message = "是否启用设置错误")
    @ApiModelProperty(value = "是否启用,0-未启用,1-启用 不能为空", name = "enabled", required = true, example = "0")
    private Integer enabled;
    /**
     * 等级
     * */
    @NotNull(message = "等级 不能为空")
    @Min(value = 1, message = "等级 至少为 1")
    @ApiModelProperty(value = "等级 不能为空,至少为 1", name = "level", required = true, example = "0")
    private Integer level;

}
