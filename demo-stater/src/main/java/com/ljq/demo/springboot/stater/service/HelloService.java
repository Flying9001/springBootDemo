package com.ljq.demo.springboot.stater.service;

/**
 * @Description: spring boot 自定义 stater 示例业务
 * @Author: junqiang.lu
 * @Date: 2020/10/26
 */
public class HelloService {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String hello() {
        return "hello," + this.name;
    }

}
