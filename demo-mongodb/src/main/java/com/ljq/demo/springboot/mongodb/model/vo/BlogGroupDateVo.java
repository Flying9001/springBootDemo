package com.ljq.demo.springboot.mongodb.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 博客日期分组信息
 * @Author: junqiang.lu
 * @Date: 2021/11/19
 */
@Data
@ApiModel(value = "博客日期分组信息", description = "博客日期分组信息")
public class BlogGroupDateVo implements Serializable {

    private static final long serialVersionUID = -4828325421504898453L;

    /**
     * 日期分组字段
     */
    @ApiModelProperty(value = "日期分组字段", name = "dateGroupField")
    private String dateGroupField;
    /**
     * 日期格式
     */
    @ApiModelProperty(value = "日期格式", name = "dateFormat")
    private String dateFormat;



}
