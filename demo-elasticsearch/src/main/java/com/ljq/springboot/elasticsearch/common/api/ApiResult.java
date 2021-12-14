package com.ljq.springboot.elasticsearch.common.api;

import lombok.Data;
import org.slf4j.MDC;

import java.util.Map;

/**
 * @Description: 接口返回结果
 * @Author: junqiang.lu
 * @Date: 2020/9/3
 */
@Data
//@ApiModel(value = "接口返回结果",description = "接口返回结果")
public class ApiResult<T> {

    /**
     * 默认成功结果 key
     */
    public static final String DEFAULT_SUCCESS_KEY = "api.response.success";
    /**
     * 默认成功返回提示信息
     */
    public static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    /**
     * 默认错误结果 key
     */
    public static final String DEFAULT_ERROR_KEY = "api.response.error";
    /**
     * 默认错误返回提示信息
     */
    public static final String DEFAULT_ERROR_MESSAGE = "ERROR";
    /**
     * 请求 id
     */
    public static final String REQUEST_ID = "requestId";

    /**
     * 返回结果 key
     */
//    @ApiModelProperty(value = "返回结果 key", name = "key")
    private String key;
    /**
     * 返回提示信息
     */
//    @ApiModelProperty(value = "返回提示信息", name = "message")
    private String message;
    /**
     * 返回数据
     */
//    @ApiModelProperty(value = "返回数据", name = "data")
    private T data;
    /**
     * 额外返回数据
     */
//    @ApiModelProperty(value = "额外返回数据", name = "extraDataMap")
    private Map<String, Object> extraDataMap;
    /**
     * 系统当前时间(精确到毫秒)
     */
//    @ApiModelProperty(value = "系统当前时间(精确到毫秒)", name = "系统当前时间(精确到毫秒)")
    private Long timestamp = System.currentTimeMillis();
    /**
     * 请求 id
     */
//    @ApiModelProperty(value = "请求 id", name = "requestId")
    private String requestId = MDC.get(REQUEST_ID);

    protected ApiResult(){
    }

    /**
     * 构造方法
     *
     * @param key
     * @param message
     * @param data
     * @param extraDataMap
     */
    protected ApiResult(String key, String message, T data, Map<String, Object> extraDataMap) {
        this.key = key;
        this.message = message;
        this.data = data;
        this.extraDataMap = extraDataMap;
    }

    /**
     * 成功
     *
     * @return
     */
    public static <T> ApiResult<T> success() {
        return success(null,null);
    }

    /**
     * 成功
     *
     * @param data 返回数据
     * @return
     */
    public static <T> ApiResult<T> success(T data) {
        return success(data, null);
    }

    /**
     * 成功
     *
     * @param data 返回数据
     * @param extraDataMap 额外返回数据
     * @return
     */
    public static <T> ApiResult<T> success(T data, Map<String, Object> extraDataMap) {
        return new ApiResult<>(DEFAULT_SUCCESS_KEY, DEFAULT_SUCCESS_MESSAGE, data, extraDataMap);
    }

    /**
     * 失败
     *
     * @return
     */
    public static <T> ApiResult<T> fail() {
        return fail(DEFAULT_ERROR_KEY, DEFAULT_ERROR_MESSAGE, null, null);
    }

    /**
     * 失败
     *
     * @param message 返回提示信息
     * @return
     */
    public static <T> ApiResult<T> fail(String message) {
        return fail(DEFAULT_ERROR_KEY, message, null, null);
    }

    /**
     * 失败
     *
     * @param apiMsgEnum 返回消息枚举类
     * @return
     */
    public static <T> ApiResult <T> fail(ApiMsgEnum apiMsgEnum) {
        return fail(apiMsgEnum.getKey(), apiMsgEnum.getDefaultMsg());
    }
    /**
     * 失败
     *
     * @param responseKey 返回结果 key
     * @param message 返回提示信息
     * @return
     */
    public static <T> ApiResult<T> fail(String responseKey, String message) {
        return fail(responseKey, message, null, null);
    }

    /**
     * 失败
     *
     * @param responseKey 返回结果 Key
     * @param message 返回提示信息
     * @param data 返回数据
     * @param extraDataMap 额外返回数据
     * @return
     */
    public static <T> ApiResult<T> fail(String responseKey, String message, T data, Map<String, Object> extraDataMap) {
        return new ApiResult<>(responseKey, message, data, extraDataMap);
    }


}
