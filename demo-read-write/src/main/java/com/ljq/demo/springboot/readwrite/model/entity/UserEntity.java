package com.ljq.demo.springboot.readwrite.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户表实体类
 *
 * @author junqiang.lu
 * @date 2020-09-01 13:24:08
 */
@Data
@TableName(value = "user")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;

     /**
      * id 主键
      **/
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户名
     **/
    @TableField(value = "user_name")
    private String userName;
    /**
     * 登陆密码
     **/
    @TableField(value = "user_passcode")
    private String userPasscode;
    /**
     * 邮箱
     **/
    @TableField(value = "user_email")
    private String userEmail;
    /**
     * 用户注册时间
     **/
    @TableField(value = "user_insert_time")
    private String userInsertTime;
    /**
     * 用户更新时间
     **/
    @TableField(value = "user_update_time")
    private String userUpdateTime;
    /**
     * 用户账号状态,1正常(默认),2禁止登陆
     **/
    @TableField(value = "user_status")
    private Integer userStatus;
    /**
     * 版本控制字段(默认1)
     **/
    @TableField(value = "user_version")
    private Integer userVersion;
    /**
     * 逻辑删除字段,0正常(默认),1删除
     **/
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "user_del")
    private Integer userDel;


}
