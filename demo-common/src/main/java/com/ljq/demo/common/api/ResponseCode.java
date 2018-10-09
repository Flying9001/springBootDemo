package com.ljq.demo.common.api;

/**
 * @Description: 返回码枚举
 * @Author yemiaoxin
 * @Date 2018/5/22
 */
public enum ResponseCode {

    SUCCESS(1000, "成功"),
    FAIL(-1, "失败"),

    // 参数
    PARAM_ERROR(1001, "参数错误"),
    PARAM_NOT_NULL(1002, "参数不能为空"),
    SIGN_ERROR(1003,"签名错误"),

    // 用户
    ACCOUNT_ERROR(2001, "账号错误"),
    PASSWORD_ERROR(2002,"密码错误"),
    ACCOUNT_NOT_EXIST(2003,"账号不存在"),
    ACCOUNT_EXIST(2004,"账号已被注册"),
    PHONE_ERROR(2005,"手机号错误"),
    EMAIL_ERROR(2006,"邮箱错误"),
    PHONE_EXIST(2007,"手机号已存在"),
    EMAIL_EXIST(2008,"邮箱已存在"),
    CHECK_CODE_ERROR(2009,"验证码错误"),
    CODE_ERROR(2010, "验证码不正确"),
    MESSAGE_SEND_ERROR(2011,"短信发送失败"),
    EMAIL_SEND_ERROR(2012,"邮件发送失败"),
    ACCOUNT_LOCK(2013,"帐号被锁定"),
    ACCOUNT_DELETED(2014,"帐号被回收"),
    ADMIN_ROLE_EXIST(2015,"员工已经拥有对应角色,请勿重复添加"),
    ROLE_PERMISSION_EXIST(2016,"角色已经拥有该权限,请勿重复添加"),
    ROLE_PERMISSION_NULL(2017,"角色没有被分配权限"),
    ROLE_EXIST(2018,"角色已经存在"),
    ROLE_PERMISSION_ERROR(2019,"角色拥有的权限信息错误"),
    ACCOUNT_ADMIN_ROLE(2020,"用户拥有超级管理员(admin)角色,不可操作"),
    ADMIN_ROLE_ERROR(2021,"员工拥有的角色信息错误"),
    ADMIN_ROLE_NULL(2022,"员工角色为空"),
    PERMISSION_EXIST(2023,"权限已经存在"),
    PERMISSION_NOT_EXIST(2024,"权限不存在"),


    UNKNOWN_ERROR(-1000,"未知异常");

    // 返回码
    private int code;

    // 返回信息
    private String msg;

    private ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
