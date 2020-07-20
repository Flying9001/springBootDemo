package com.ljq.demo.springboot.baseweb.interceptor;

import com.ljq.demo.springboot.baseweb.api.ApiResult;
import com.ljq.demo.springboot.baseweb.api.ResponseCode;
import com.ljq.demo.springboot.baseweb.exception.ParamsCheckException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @Description: 全局异常处理
 * @Author: junqiang.lu
 * @Date: 2019/12/2
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 全局异常处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = {ParamsCheckException.class, HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class, BindException.class, NoHandlerFoundException.class,
            MissingServletRequestPartException.class, MissingServletRequestParameterException.class,
            Exception.class})
    public ResponseEntity exceptionHandler(Exception e) {
        log.warn("class: {}, message: {}",e.getClass().getName(), e.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        // 自定义异常
        if (ParamsCheckException.class.isAssignableFrom(e.getClass())) {
            return new ResponseEntity(ApiResult.failure(((ParamsCheckException) e).getCode(),e.getMessage(), null),headers, HttpStatus.BAD_REQUEST);
        }
        // 请求方式错误异常
        if (HttpRequestMethodNotSupportedException.class.isAssignableFrom(e.getClass())) {
            return new ResponseEntity(ApiResult.failure(ResponseCode.REQUEST_METHOD_ERROR), headers, HttpStatus.BAD_REQUEST);
        }
        // 参数格式不支持
        if (HttpMediaTypeNotSupportedException.class.isAssignableFrom(e.getClass())) {
            return new ResponseEntity(ApiResult.failure(ResponseCode.MEDIA_TYPE_NOT_SUPPORT_ERROR), headers, HttpStatus.BAD_REQUEST);
        }
        // 参数格式错误,数据绑定失败
        if (BindException.class.isAssignableFrom(e.getClass())) {
            return new ResponseEntity(ApiResult.failure(ResponseCode.PARAM_BIND_ERROR), headers, HttpStatus.BAD_REQUEST);
        }
        // 404
        if (NoHandlerFoundException.class.isAssignableFrom(e.getClass())) {
            return new ResponseEntity(ApiResult.failure(ResponseCode.NOT_FOUND_ERROR), headers, HttpStatus.BAD_REQUEST);
        }
        // 缺少请求体(未上传文件)
        if (MissingServletRequestPartException.class.isAssignableFrom(e.getClass())) {
            return new ResponseEntity(ApiResult.failure(ResponseCode.MISS_REQUEST_PART_ERROR), headers, HttpStatus.BAD_REQUEST);
        }
        // 缺少请求参数
        if (MissingServletRequestParameterException.class.isAssignableFrom(e.getClass())) {
            return new ResponseEntity(ApiResult.failure(ResponseCode.MISS_REQUEST_PARAM_ERROR), headers, HttpStatus.BAD_REQUEST);
        }

        /**
         * 根据情况添加异常类型(如IO，线程，DB 相关等)
         */

        // 其他
        return new ResponseEntity(ApiResult.failure(ResponseCode.UNKNOWN_ERROR), headers, HttpStatus.BAD_REQUEST);
    }

}
