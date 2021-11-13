package com.ljq.demo.springboot.mongodb.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

/**
 * @Description: 分页查询博客
 * @Author: junqiang.lu
 * @Date: 2021/11/13
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "分页查询博客", description = "分页插叙博客")
public class BlogQueryPageParam extends BasePageParam {

    private static final long serialVersionUID = 362651641919164224L;

    /**
     * 博客 id
     */
    @Pattern(regexp = "^[0-9a-zA-Z]{0,64}$", message = "博客 ID 不合法")
    @ApiModelProperty(value = "博客 ID", name = "id")
    private String id;
    /**
     * 标题
     */
    @Length(max = 128,message = "博客标题需要控制在 128 字符以内")
    @ApiModelProperty(value = "标题", name = "title")
    private String title;
    /**
     * 作者
     */
    @Length(max = 64,message = "博客作者需要控制在 64 字符以内")
    @ApiModelProperty(value = "作者", name = "author")
    private String author;
    /**
     * 内容
     */
    @Length(max = 500,message = "博客内容需要控制在 500 字符以内")
    @ApiModelProperty(value = "内容", name = "content")
    private String content;


}
