package com.ljq.demo.springboot.mongodb.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @Description: 更新单条博客
 * @Author: junqiang.lu
 * @Date: 2021/11/13
 */
@Data
@ApiModel(value = "更新单条博客", description = "更新单条博客")
public class BlogUpdateParam implements Serializable {

    private static final long serialVersionUID = 5995420506930561290L;

    /**
     * 博客 id
     */
    @NotBlank(message = "博客 ID 不能为空")
    @Pattern(regexp = "^[0-9a-zA-Z]{1,64}$", message = "博客 ID 不合法")
    @ApiModelProperty(value = "博客 ID", name = "id", required = true)
    private String id;
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
     * 阅读数量
     */
    @NotNull(message = "阅读量不能为空")
    @Min(value = 0, message = "阅读量设置错误")
    @ApiModelProperty(value = "阅读数量", name = "countRead", required = true)
    private Integer countRead;
    /**
     * 点赞数量
     */
    @NotNull(message = "点赞数量不能为空")
    @Min(value = 0, message = "点赞数量设置错误")
    @ApiModelProperty(value = "点赞数量", name = "countLike", required = true)
    private Integer countLike;
    /**
     * 客户端时间戳(精确到秒)
     */
    @NotNull(message = "客户端时间戳不能为空")
    @ApiModelProperty(value = "客户端时间戳(精确到秒)", name = "clientTimestamp", required = true)
    private Integer clientTimestamp;

}
