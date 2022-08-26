package com.ljq.demo.springboot.alibaba.gateway.filter.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 网关路由白名单更新单条
 * @Author: junqiang.lu
 * @Date: 2022/8/23
 */
@Data
public class WhiteListUpdateParam implements Serializable {

    private static final long serialVersionUID = -3246240002071992718L;

    /**
     * id
     */
    private Long id;

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
