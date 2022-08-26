package com.ljq.demo.springboot.alibaba.gateway.filter.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 网关路由白名单分页查询
 * @Author: junqiang.lu
 * @Date: 2022/8/23
 */
@Data
public class WhiteListPageParam implements Serializable {

    private static final long serialVersionUID = 6388243385822831297L;

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

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 每页显示条数
     */
    private Integer pageSize;

}
