package com.ljq.demo.springboot.easyexcel.common.exception;

import lombok.Data;

/**
 * @Description: 业务异常类
 * @Author: junqiang.lu
 * @Date: 2024/6/20
 */
@Data
public class ServiceException extends RuntimeException {

    /**
     * 错误码
     */
    private Integer code = 500;

    /**
     * 错误信息
     */
    private String message;

    public ServiceException(String message) {
        this.message = message;
    }

    public ServiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }



}
