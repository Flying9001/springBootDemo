package com.ljq.demo.springboot.common.api;

import lombok.Getter;
import lombok.ToString;

/**
 * @Description: 多语言,国际化接口返回码枚举
 * @Author: junqiang.lu
 * @Date: 2019/1/24
 */
@Getter
@ToString
public enum  ResponseCodeI18n {

    /**
     * 成功与失败
     */
    SUCCESS(1000, "api.response.code.success"),
    FAIL(-1, "api.response.code.fail"),

    /**
     * 公共参数
     */
    PARAM_ERROR(1001, "api.response.code.paramError"),
    LANGUAGE_TYPE_ERROR(1002, "api.response.code.languageTypeError"),

    // 用户模块
    // 帐号
    ACCOUNT_NULL_ERROR(2001,"api.response.code.user.accountNullError"),
    ACCOUNT_FORMAT_ERROR(2002, "api.response.code.user.accountFormatError"),
    ACCOUNT_NOT_EXIST(2003,"api.response.code.user.accountNotExist"),
    ACCOUNT_EXIST(2004,"api.response.code.user.accountExist"),
    // 密码
    PASSWORD_NULL_ERROR(2101, "api.response.code.user.passwordNullError"),
    PASSWORD_FORMAT_ERROR(2102, "api.response.code.user.passwordFormatError"),
    PASSWORD_ERROR(2103,"api.response.code.user.passwordError"),


    UNKNOWN_ERROR(-1000,"api.response.code.unknownError");

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回信息
     */
    private String msg;

    private ResponseCodeI18n(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
