package com.ljq.demo.springboot.alibaba.consumer.sentinel.model.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 用户实体类
 * @Author: junqiang.lu
 * @Date: 2020/12/2
 */
@Data
public class HelloParam implements Serializable {

    /**
     * 用户名
     */
    private String name;
}
