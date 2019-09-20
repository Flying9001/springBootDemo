package com.ljq.demo.springboot.rest.model.entity.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ljq.demo.springboot.rest.model.entity.BaseEntity;
import lombok.Data;

/**
 * REST示例-用户表实体类
 *
 * @author junqiang.lu
 * @date 2019-09-19 17:19:42
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestUserEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

    /**
	 * 用户 id
	 * */
	private Long id;
    /**
	 * 用户名
	 * */
	private String userName;
    /**
	 * 密码
	 * */
	private String passcode;
    /**
	 * 邮箱
	 * */
	private String email;
    /**
	 * 用户状态,0:所有,不区分状态,1:正常(默认),2:禁止登陆
	 * */
	private Integer userStatus;

}
