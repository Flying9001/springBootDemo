package com.ljq.demo.springboot.alibaba.gateway.filter.common.api;

import lombok.Data;

import java.util.Map;

/**
 * @Description: 接口返回结果
 * @Author: junqiang.lu
 * @Date: 2020/9/3
 */
@Data
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
     * 返回结果 key
     */
    private String key;
    /**
     * 返回提示信息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 额外返回数据
     */
    private Map<String, Object> extraDataMap;
    /**
     * 系统当前时间(精确到毫秒)
     */
    private Long timestamp = System.currentTimeMillis();

    protected ApiResult(){
        // 保护无参构造方法,尽量避免手动创建对象
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
