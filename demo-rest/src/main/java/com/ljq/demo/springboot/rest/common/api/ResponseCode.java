package com.ljq.demo.springboot.rest.common.api;

import lombok.Getter;
import lombok.ToString;

/**
 * @Description: 接口返回码枚举类
 * @Author: junqiang.lu
 * @Date: 2019/3/26
 */
@Getter
@ToString
public enum  ResponseCode {

    /**
     * 成功
     */
    SUCCESS(200, "成功"),
    /**
     * 失败
     */
    FAIL(-1, "失败"),

    /**
     * 参数与公共模块
     */
    PARAM_ERROR(1001, "参数(格式)错误"),
    PARAM_NOT_NULL(1002, "参数不能为空"),
    OBJECT_NOT_EXIST(1003, "查询对象不存在"),
    METHOD_NOT_VALID(1004, "请求方式错误"),
    API_DEPRECATED(1005, "接口已遗弃"),
    PARAM_MISSING_ERROR(1006, "缺少请求参数"),

    /**
     * 用户信息
     */
    USER_PASSWORD_ERROR(3001, "密码错误"),
    USER_BAN(6003,"账号已停用"),
    USER_NOT_EXIST(6008,"用户不存在"),
    USER_ALREADY_EXIST(6009,"手机号已存在"),

    /**
     * 未知异常
     */
    UNKNOWN_ERROR(-1000,"系统异常");

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回信息
     */
    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
