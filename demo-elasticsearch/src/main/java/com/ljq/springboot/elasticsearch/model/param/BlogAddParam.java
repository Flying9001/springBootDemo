package com.ljq.springboot.elasticsearch.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description: 新增单条博客
 * @Author: junqiang.lu
 * @Date: 2021/11/13
 */
@Data
@ApiModel(value = "新增单条博客", description = "新增单条博客")
public class BlogAddParam implements Serializable {

    private static final long serialVersionUID = -2784787111136075605L;

    /**
     * 标题
     */
    @NotBlank(message = "博客标题不能为空")
    @Length(min = 1, max = 128,message = "博客标题需要控制在 1-128 字符以内")
    @ApiModelProperty(value = "标题", name = "title", required = true)
    private String title;
    /**
     * 作者
     */
    @NotBlank(message = "博客作者不能为空")
    @Length(min = 1, max = 64,message = "博客作者需要控制在 1-64 字符以内")
    @ApiModelProperty(value = "作者", name = "author", required = true)
    private String author;
    /**
     * 内容
     */
    @NotBlank(message = "博客内容不能为空")
    @Length(min = 1, max = 50000,message = "博客内容需要控制在 1-50000 字符以内")
    @ApiModelProperty(value = "内容", name = "content", required = true)
    private String content;
    /**
     * 客户端时间戳(精确到秒)
     */
    @NotNull(message = "客户端时间戳不能为空")
    @ApiModelProperty(value = "客户端时间戳(精确到秒)", name = "clientTimestamp", required = true)
    private Integer clientTimestamp;

}
