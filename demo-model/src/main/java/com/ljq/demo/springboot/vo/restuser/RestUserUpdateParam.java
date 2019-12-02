package com.ljq.demo.springboot.vo.restuser;

import com.ljq.demo.springboot.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * REST示例-用户表参数接收类
 *
 * @author junqiang.lu
 * @date 2019-09-19 17:19:42
 */
@Data
@ApiModel(value = "REST示例-用户表修改(单条)", description = "REST示例-用户表修改(单条)")
public class RestUserUpdateParam extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 用户 id
     * */
    @NotNull(message = "用户 id 不能为空")
    @Min(value = 1, message = "用户 id 至少为 1")
    @ApiModelProperty(value = "用户 id 不能为空,至少为 1", name = "id", required = true, example = "0")
    private Long id;
    /**
     * 用户名
     * */
    @NotNull(message = "用户名 不能为空")
    @ApiModelProperty(value = "用户名", name = "userName", required = true)
    private String userName;
    /**
     * 密码
     * */
    @NotNull(message = "密码 不能为空")
    @ApiModelProperty(value = "密码", name = "passcode", required = true)
    private String passcode;
    /**
     * 邮箱
     * */
    @NotNull(message = "邮箱 不能为空")
    @ApiModelProperty(value = "邮箱", name = "email", required = true)
    private String email;
    /**
     * 用户状态,0:所有,不区分状态,1:正常(默认),2:禁止登陆
     * */
    @NotNull(message = "用户状态,0:所有,不区分状态,1:正常(默认),2:禁止登陆 不能为空")
    @Min(value = 1, message = "用户状态,0:所有,不区分状态,1:正常(默认),2:禁止登陆 至少为 1")
    @ApiModelProperty(value = "用户状态,0:所有,不区分状态,1:正常(默认),2:禁止登陆 不能为空,至少为 1", name = "userStatus", required = true, example = "0")
    private Integer userStatus;


}
