package com.ljq.demo.springboot.mybatisplus.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户表实体类
 *
 * @author junqiang.lu
 * @date 2020-09-01 13:24:08
 */
@Data
@TableName(value = "user", resultMap = "userMap")
@ApiModel(value = "用户表", description = "用户表")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;

     /**
      * id 主键
      **/
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "id 主键", name = "id")
    private Long id;
    /**
     * 用户名
     **/
    @TableField(value = "user_name")
    @ApiModelProperty(value = "用户名", name = "userName")
    private String userName;
    /**
     * 登陆密码
     **/
    @TableField(value = "user_passcode")
    @ApiModelProperty(value = "登陆密码", name = "userPasscode")
    private String userPasscode;
    /**
     * 邮箱
     **/
    @TableField(value = "user_email")
    @ApiModelProperty(value = "邮箱", name = "userEmail")
    private String userEmail;
    /**
     * 用户注册时间
     **/
    @TableField(value = "user_insert_time")
    @ApiModelProperty(value = "用户注册时间", name = "userInsertTime")
    private String userInsertTime;
    /**
     * 用户更新时间
     **/
    @TableField(value = "user_update_time")
    @ApiModelProperty(value = "用户更新时间", name = "userUpdateTime")
    private String userUpdateTime;
    /**
     * 用户账号状态,1正常(默认),2禁止登陆
     **/
    @TableField(value = "user_status")
    @ApiModelProperty(value = "用户账号状态,1正常(默认),2禁止登陆", name = "userStatus")
    private Integer userStatus;
    /**
     * 版本控制字段(默认1)
     **/
    @TableField(value = "user_version")
    @ApiModelProperty(value = "版本控制字段(默认1)", name = "userVersion")
    private Integer userVersion;
    /**
     * 逻辑删除字段,0正常(默认),1删除
     **/
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "user_del")
    @ApiModelProperty(value = "逻辑删除字段,0正常(默认),1删除", name = "userDel")
    private Integer userDel;


}
