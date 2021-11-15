package com.ljq.demo.springboot.mongodb.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @Description: 查询博客阅读量统计
 * @Author: junqiang.lu
 * @Date: 2021/11/15
 */
@Data
@ApiModel(value = "查询博客阅读量统计", description = "查询博客阅读量统计")
public class BlogReadCountSummaryParam implements Serializable {

    private static final long serialVersionUID = 4394523808402747491L;

    /**
     * 博客 id
     */
    @Pattern(regexp = "^[0-9a-zA-Z]{0,64}$", message = "博客 ID 不合法")
    @ApiModelProperty(value = "博客 ID", name = "id")
    private String id;
    /**
     * 作者
     */
    @Length(max = 64,message = "博客作者需要控制在 64 字符以内")
    @ApiModelProperty(value = "作者", name = "author")
    private String author;

}
