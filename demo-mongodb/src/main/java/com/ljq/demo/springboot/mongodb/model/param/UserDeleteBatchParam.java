package com.ljq.demo.springboot.mongodb.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Description: Mongo-用户批量删除
 * @Author: junqiang.lu
 * @Date: 2021/1/20
 */
@Data
@ApiModel(value = "Mongo-用户批量删除", description = "Mongo-用户批量删除")
public class UserDeleteBatchParam implements Serializable {

    private static final long serialVersionUID = -2899668339947463921L;

    /**
     * id 数组
     */
    @NotEmpty(message = "请选择需要删除的条目")
    @Size(max = 100, message = "每次删除的条目不超过 100 条")
    @ApiModelProperty(value = "id 数组", name = "ids")
    private String[] ids;
}
