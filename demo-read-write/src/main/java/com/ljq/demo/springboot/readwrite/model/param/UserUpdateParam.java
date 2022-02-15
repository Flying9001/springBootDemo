package com.ljq.demo.springboot.readwrite.model.param;

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
public class UserUpdateParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id 主键
     * */
    @NotNull(message = "id 主键 不能为空")
    @Min(value = 1, message = "id 主键 至少为 1")
    private Long id;
    /**
     * 用户名
     * */
    @Length(max = 40, message = "用户名需要控制在 40 字符以内")
    private String userName;
    /**
     * 登陆密码
     * */
    @Length(max = 40, message = "密码长度需要控制在 40 字符以内")
    private String userPasscode;
    /**
     * 邮箱
     * */
    @Email(message = "邮箱格式错误")
    private String userEmail;


}
