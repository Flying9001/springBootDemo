package com.ljq.demo.springboot.mybatisplus.model.param.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户表参数接收类
 *
 * @author junqiang.lu
 * @date 2020-08-31 14:09:53
 */
@Data
@ApiModel(value = "用户表修改(单条)", description = "用户表修改(单条)")
public class UserUpdateParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id 主键
     * */
    @NotNull(message = "id 主键 不能为空")
    @Min(value = 1, message = "id 主键 至少为 1")
    @ApiModelProperty(value = "id 主键 不能为空,至少为 1", name = "id", required = true, example = "0")
    private Long id;
    /**
     * 用户名
     * */
    @Length(max = 40, message = "用户名需要控制在 40 字符以内")
    @ApiModelProperty(value = "用户名", name = "userName", required = true)
    private String userName;
    /**
     * 登陆密码
     * */
    @Length(max = 40, message = "密码长度需要控制在 40 字符以内")
    @ApiModelProperty(value = "登陆密码", name = "userPasscode", required = true)
    private String userPasscode;
    /**
     * 邮箱
     * */
    @Email(message = "邮箱格式错误")
    @ApiModelProperty(value = "邮箱", name = "userEmail", required = true)
    private String userEmail;


}
