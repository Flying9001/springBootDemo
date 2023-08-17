package com.ljq.demo.springboot.knife4j.common.api;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

/**
 * @Description: 接口返回结果信息枚举类
 * @Author: junqiang.lu
 * @Date: 2020/9/3
 */
@Getter
@ToString
public enum ApiMsgEnum {

    /**
     * 基础提示信息
     */
    SUCCESS("api.response.success","成功"),
    FAIL("api.response.fail","失败"),
    TOKEN_IS_NULL("api.response.tokenIsNull","Token 为空"),
    PARAM_BIND_EXCEPTION("api.response.paramBindException", "参数绑定错误,请输入正确格式的参数"),
    PARAM_CONVERT_ERROR("api.response.paramConvertError", "参数转换错误,请输入正确格式的参数"),
    PARAM_VALIDATE_EXCEPTION("api.response.paramValidateException","参数校验异常,请输入合法的参数"),
    HTTP_METHOD_NOT_SUPPORT_ERROR("api.response.httpMethodNotSupportError","HTTP 请求方式错误"),
    HTTP_MEDIA_TYPE_NOT_SUPPORT_ERROR("api.response.httpMediaTypeNotSupportError", "网络请求参数格式错误"),
    MISSING_UPLOAD_FILE_ERROR("api.response.missingUploadFileError", "缺失上传文件"),
    MAX_UPLOAD_SIZE_ERROR("api.response.maxUploadSizeError", "上传文件过大"),
    CANNOT_CREATE_TRANSACTION_ERROR("api.response.cannotCreateTractionError", "无法创建数据库连接"),
    HTTP_NOT_FOUND("api.response.httpNotFound","请求资源不存在"),


    /**
     * 用户消息
     */
    USER_MESSAGE_NOT_EXIST("api.response.userMessage.notExist", "消息不存在"),
    USER_MESSAGE_PUSH_REPEAT("api.response.userMessage.pushRepeat", "消息重复推送"),

    /**
     * 未知异常
     */
    UNKNOWN_ERROR("api.response.unknownError", "未知异常");

    /**
     * 返回 key
     */
    private String key;
    /**
     * 默认提示信息
     */
    private String defaultMsg;

    ApiMsgEnum(String key, String defaultMsg) {
        this.key = key;
        this.defaultMsg = defaultMsg;
    }

    /**
     * 通过 key 获取返回对象
     *
     * @param key
     * @return
     */
    public static ApiMsgEnum getByKey(String key) {
        if (Objects.isNull(key) || key.trim().isEmpty()) {
            return ApiMsgEnum.UNKNOWN_ERROR;
        }
        for (ApiMsgEnum responseEnum : ApiMsgEnum.values()) {
            if (responseEnum.key.equalsIgnoreCase(key)) {
                return responseEnum;
            }
        }
        return ApiMsgEnum.UNKNOWN_ERROR;
    }




}
