package com.ljq.demo.springboot.mongodb.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 批量更新博客
 * @Author: junqiang.lu
 * @Date: 2021/11/15
 */
@Data
@ApiModel(value = "批量更新博客", description = "批量更新博客")
public class BlogUpdateBatchParam implements Serializable {

    private static final long serialVersionUID = -6474920545338340799L;

    /**
     * 博客列表
     */
    @Valid
    @NotEmpty(message = "请输入需要更新的博客信息")
    @ApiModelProperty(value = "博客列表", name = "blogList", required = true)
    private List<BlogUpdateParam> blogList;

}
