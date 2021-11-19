package com.ljq.demo.springboot.mongodb.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 博客统计结果
 * @Author: junqiang.lu
 * @Date: 2021/11/15
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "博客统计结果", description = "博客统计结果")
public class BlogSummaryVo implements Serializable {

    private static final long serialVersionUID = 6350918498981106620L;

    /**
     * 作者
     */
    @ApiModelProperty(value = "作者", name = "author")
    private String author;
    /**
     * 总博客数量
     */
    @ApiModelProperty(value = "总博客数量", name = "totalBlog")
    private Long totalBlog;
    /**
     * 总阅读量
     */
    @ApiModelProperty(value = "总阅读量", name = "totalRead")
    private Long totalRead;
    /**
     * 平均阅读量
     */
    @ApiModelProperty(value = "平均阅读量", name = "aveRead")
    private Long aveRead;
    /**
     * 总点赞数量
     */
    @ApiModelProperty(value = "总点赞数量", name = "totalLike")
    private Long totalLike;
    /**
     * 平均点赞数量
     */
    @ApiModelProperty(value = "平均点赞数量", name = "aveLike")
    private Long aveLike;
    /**
     * 日期
     */
    @ApiModelProperty(value = "日期", name = "date")
    private String date;


}
