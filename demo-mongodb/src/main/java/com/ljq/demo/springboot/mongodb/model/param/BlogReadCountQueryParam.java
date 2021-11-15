package com.ljq.demo.springboot.mongodb.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @Description: 查询博客阅读量
 * @Author: junqiang.lu
 * @Date: 2021/11/15
 */
@Data
@ApiModel(value = "查询博客阅读量", description = "查询博客阅读量")
public class BlogReadCountQueryParam implements Serializable {

    private static final long serialVersionUID = -6794509416424960266L;

    /**
     * 博客 id
     */
    @NotBlank(message = "博客 ID 不能为空")
    @Pattern(regexp = "^[0-9a-zA-Z]{1,64}$", message = "博客 ID 不合法")
    @ApiModelProperty(value = "博客 ID", name = "id", required = true)
    private String id;

}
