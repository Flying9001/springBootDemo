package com.ljq.demo.springboot.mongodb.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * @Description: 按照作者分组查询博客数据
 * @Author: junqiang.lu
 * @Date: 2021/11/17
 */
@Data
@ApiModel(value = "按照作者分组查询博客数据", description = "按照作者分组查询博客数据")
public class BlogQueryGroupByAuthorParam implements Serializable {

    private static final long serialVersionUID = -8813245928445873097L;

    /**
     * 作者
     */
    @Length(max = 64,message = "博客作者需要控制在 64 字符以内")
    @ApiModelProperty(value = "作者", name = "author")
    private String author;

}
