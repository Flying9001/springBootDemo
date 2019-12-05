package com.ljq.demo.springboot.common.constant;

/**
 * @Description: Token 相关常量
 * @Author: junqiang.lu
 * @Date: 2019/12/3
 */
public class TokenConst {


    /**
     * Token headers 字段
     */
    public static final String TOKEN_HEADERS_FIELD = "Authorization";
    /**
     * token key
     */
    public static final String TOKEN_KEY = "tokenPhone";
    /**
     * Token 刷新时间(单位: 毫秒)
     */
    public static final long TOKEN_REFRESH_TIME_MILLIS = 1000 * 60 * 60 * 2L;
    /**
     * token 有效期(单位: 毫秒)
     */
    public static final long TOKEN_EXPIRE_TIME_MILLIS = 1000 * 60 * 60 * 24 * 30L;





}
