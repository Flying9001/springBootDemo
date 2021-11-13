package com.ljq.demo.springboot.mongodb.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @Description: 博客信息
 * @Author: junqiang.lu
 * @Date: 2021/11/13
 */
@Data
@ToString(callSuper = true)
@Document(value = "blog")
@ApiModel(value = "博客信息", description = "博客信息")
public class BlogEntity extends BaseEntity {

    private static final long serialVersionUID = -2124422309475024490L;

    /**
     * 标题
     */
    @Field
    @ApiModelProperty(value = "标题", name = "title")
    private String title;
    /**
     * 作者
     */
    @Field
    @ApiModelProperty(value = "作者", name = "author")
    private String author;
    /**
     * 内容
     */
    @Field
    @ApiModelProperty(value = "内容", name = "content")
    private String content;
    /**
     * 阅读数量
     */
    @Field
    @ApiModelProperty(value = "阅读数量", name = "countRead")
    private Integer countRead;
    /**
     * 点赞数量
     */
    @Field
    @ApiModelProperty(value = "点赞数量", name = "countLike")
    private Integer countLike;
    /**
     * 客户端时间戳(精确到秒)
     */
    @Field
    @ApiModelProperty(value = "客户端时间戳(精确到秒)", name = "clientTimestamp")
    private Integer clientTimestamp;



}
