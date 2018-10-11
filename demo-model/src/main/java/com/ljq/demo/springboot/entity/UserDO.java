package com.ljq.demo.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 用户 DO
 * @Author: junqiang.lu
 * @Date: 2018/9/29
 */
@Data
public class UserDO implements Serializable {

    private static final long serialVersionUID = 4945646798330083519L;

    /**
     * 用户 id
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 登陆密码
     */
    private String userPasscode;

    /**
     * 邮箱
     */
    private String userEmail;

    /**
     * 用户注册时间
     */
    private String userInsertTime;

    /**
     * 用户信息更新时间
     */
    private String userUpdateTime;

    /**
     * 用户账号状态,1正常,2禁止登陆
     */
    private Integer userStatus;

    /**
     * 版本控制字段
     */
    @JsonIgnore
    private Integer userVersion;

    /**
     * 逻辑删除字段,0正常(默认),1删除
     */
    @JsonIgnore
    private Integer userDel;

}
