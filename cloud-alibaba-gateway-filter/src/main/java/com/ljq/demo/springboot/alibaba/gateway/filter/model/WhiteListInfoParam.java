package com.ljq.demo.springboot.alibaba.gateway.filter.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 网关路由白名单查询单条
 * @Author: junqiang.lu
 * @Date: 2022/8/23
 */
@Data
public class WhiteListInfoParam implements Serializable {

    private static final long serialVersionUID = -5392392668785473906L;

    /**
     * id
     */
    private Long id;


}
