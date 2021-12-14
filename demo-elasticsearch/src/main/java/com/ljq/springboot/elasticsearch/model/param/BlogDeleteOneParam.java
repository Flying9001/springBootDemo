package com.ljq.springboot.elasticsearch.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @Description: 删除单条博客
 * @Author: junqiang.lu
 * @Date: 2021/11/13
 */
@Data
@ApiModel(value = "删除单条博客", description = "删除单条博客")
public class BlogDeleteOneParam implements Serializable {

    private static final long serialVersionUID = -60183690958358555L;

    /**
     * 博客 id
     */
    @NotBlank(message = "博客 ID 不能为空")
    @Pattern(regexp = "^[0-9a-zA-Z_-]{1,64}$", message = "博客 ID 不合法")
    @ApiModelProperty(value = "博客 ID", name = "id", required = true)
    private String id;


}
