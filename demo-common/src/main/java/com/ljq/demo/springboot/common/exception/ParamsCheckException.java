package com.ljq.demo.springboot.common.exception;

import com.ljq.demo.springboot.common.api.ResponseCodeI18n;
import lombok.Data;

/**
 * @Description: 自定义参数校验异常
 * @Author: junqiang.lu
 * @Date: 2019/1/24
 */
@Data
public class ParamsCheckException extends Exception{

    private static final long serialVersionUID = 2684099760669375847L;

    /**
     * 异常编码
     */
    private int code;

    /**
     * 异常信息
     */
    private String message;

    public ParamsCheckException(){
        super();
    }

    public ParamsCheckException(int code, String message){
        this.code = code;
        this.message = message;
    }

    public ParamsCheckException(String message){
        this.message = message;
    }

    public ParamsCheckException(ResponseCodeI18n responseCodeI18n){
        this.code = responseCodeI18n.getCode();
        this.message = responseCodeI18n.getMsg();
    }






}
