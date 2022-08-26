package com.ljq.demo.springboot.alibaba.gateway.filter.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 网关路由白名单新增单条
 * @Author: junqiang.lu
 * @Date: 2022/8/23
 */
@Data
public class WhiteListAddParam implements Serializable {

    private static final long serialVersionUID = -576425853342981714L;


    /**
     * 路由类型
     */
    private String routeType;

    /**
     * 请求路径
     */
    private String path;

    /**
     * 说明
     */
    private String comment;


}
