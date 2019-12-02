package com.ljq.demo.springboot.vo.restuser;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ljq.demo.springboot.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * REST示例-用户表前端展示对象
 *
 * @author junqiang.lu
 * @date 2019-09-19 17:19:42
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "REST示例-用户表信息", description = "REST示例-用户表信息")
public class RestUserVO extends BaseVO {

	private static final long serialVersionUID = -198667727558896279L;

	/**
	 * 用户 id
	 * */
	@ApiModelProperty(value = "用户 id", name = "id")
	private Long id;
    /**
	 * 用户名
	 * */
	@ApiModelProperty(value = "用户名", name = "userName")
	private String userName;
    /**
	 * 密码
	 * */
	@ApiModelProperty(value = "密码", name = "passcode")
	private String passcode;
    /**
	 * 邮箱
	 * */
	@ApiModelProperty(value = "邮箱", name = "email")
	private String email;
    /**
	 * 用户状态,0:所有,不区分状态,1:正常(默认),2:禁止登陆
	 * */
	@ApiModelProperty(value = "用户状态,0:所有,不区分状态,1:正常(默认),2:禁止登陆", name = "userStatus")
	private Integer userStatus;

}
