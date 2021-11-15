package com.ljq.demo.springboot.mongodb.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 批量新增博客
 * @Author: junqiang.lu
 * @Date: 2021/11/15
 */
@Data
@ApiModel(value = "批量新增博客", description = "批量新增博客")
public class BlogAddBatchParam implements Serializable {

    private static final long serialVersionUID = 429035193248396179L;

    /**
     * 博客列表
     */
    @Valid
    @NotEmpty(message = "请输入需要保存的博客信息")
    @ApiModelProperty(value = "博客列表", name = "blogList", required = true)
    private List<BlogAddParam> blogList;

}
