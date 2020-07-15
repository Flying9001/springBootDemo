package com.ljq.demo.springboot.activiti.common.api;

import lombok.Getter;
import lombok.ToString;

/**
 * @Description: 返回码枚举
 * @Author yemiaoxin
 * @Date 2018/5/22
 */
@Getter
@ToString
public enum ResponseCode {

    /**
     * 成功与失败
     */
    SUCCESS(200, "成功"),
    FAIL(-1, "失败"),

    /**
     * 公共参数
     */
    PARAM_ERROR(1001, "参数错误"),
    PARAM_NOT_NULL(1002, "参数不能为空"),
    SIGN_ERROR(1003,"签名错误"),
    REQUEST_METHOD_ERROR(1004, "请求方式错误"),
    MEDIA_TYPE_NOT_SUPPORT_ERROR(1005, "参数(文件)格式不支持"),
    PARAM_BIND_ERROR(1006, "参数格式错误,数据绑定失败"),
    NOT_FOUND_ERROR(1007, "请求资源(接口)不存在"),
    MISS_REQUEST_PART_ERROR(1008, "缺少请求体(未上传文件)"),
    MISS_REQUEST_PARAM_ERROR(1009, "缺少请求参数"),

    /**
     * 请假
     */
    LEAVE_INFO_NOT_EXIST(2001, "没有查询到请假信息"),
    LEAVE_INFO_WORKFLOW_TASK_NOT_EXIST(2002, "请假审批任务不存在"),


    /**
     * 其他
     */
    UNKNOWN_ERROR(-1000,"未知异常");

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回信息
     */
    private String msg;

    private ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
