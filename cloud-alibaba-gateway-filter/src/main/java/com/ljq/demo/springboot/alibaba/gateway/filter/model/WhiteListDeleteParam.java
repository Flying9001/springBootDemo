package com.ljq.demo.springboot.alibaba.gateway.filter.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 网关路由白名单删除单条
 * @Author: junqiang.lu
 * @Date: 2022/8/23
 */
@Data
public class WhiteListDeleteParam implements Serializable {

    private static final long serialVersionUID = 7390107925356306269L;

    /**
     * id
     */
    private Long id;

}
