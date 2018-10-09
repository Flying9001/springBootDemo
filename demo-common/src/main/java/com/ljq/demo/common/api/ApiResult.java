package com.ljq.demo.common.api;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 接口请求返回结果
 * @Author: junqiang.lu
 * @Date: 2018/10/9
 */
@Data
public class ApiResult implements Serializable {

    private static final long serialVersionUID = 980175259613867139L;

    /**
     * 返回码，1000 正常
     */
    private int code = 1000;

    /**
     * 返回信息
     */
    private String msg = "成功";

    /**
     * 返回数据
     */
    private Object data;

    public ApiResult() {
        super();
    }

    public ApiResult(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public ApiResult(ResponseCode code) {
        super();
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    /**
     * 获取成功状态结果
     * @return
     */
    public static ApiResult success() {
        return success(null);
    }

    /**
     * 获取成功状态结果
     * @param data 返回数据
     * @return
     */
    public static ApiResult success(Object data) {
        ApiResult result = new ApiResult();
        result.setCode(ResponseCode.SUCCESS.getCode());
        result.setMsg(ResponseCode.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }

    /**
     * 获取失败状态结果
     * @return
     */
    public static ApiResult failure() {
        return failure(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getMsg(),null);
    }

    /**
     * 获取失败状态结果
     * @param msg 错误信息
     * @return
     */
    public static ApiResult failure(String msg) {
        return failure(ResponseCode.FAIL.getCode(), msg, null);
    }

    /**
     * 获取失败状态结果
     * @return
     * @param code
     * @param msg
     */
    public static ApiResult failure(int code, String msg) {
        return failure(code, msg, null);
    }

    /**
     * 获取失败状态结果
     * @param code 错误码
     * @param msg 错误信息
     * @param data 返回结果
     * @return
     **/
    public static ApiResult failure(int code, String msg, Object data) {
        ApiResult result = new ApiResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);

        if (data instanceof String) {
            String m = (String) data;
            if (!m.matches("^.*error$")) {
                m += "error";
            }
        }

        return result;
    }


}
