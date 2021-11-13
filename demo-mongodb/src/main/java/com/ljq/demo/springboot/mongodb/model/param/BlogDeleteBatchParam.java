package com.ljq.demo.springboot.mongodb.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 批量删除博客
 * @Author: junqiang.lu
 * @Date: 2021/11/13
 */
@Data
@ApiModel(value = "批量删除博客", description = "批量删除博客")
public class BlogDeleteBatchParam implements Serializable {

    private static final long serialVersionUID = 8157724967346348530L;

    /**
     * 博客 id 列表
     */
    @Valid
    @NotEmpty(message = "请选择需要删除的对象")
    @Size(max = 100, message = "一次最多删除 100 条记录")
    @ApiModelProperty(value = "博客 id 列表", name = "idList", required = true)
    private List<BlogDeleteOneParam> idList;


}
