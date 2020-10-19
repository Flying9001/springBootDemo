package com.ljq.demo.springboot.mybatisplus.model.param.clazz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @Description: 批量删除班级
 * @Author: junqiang.lu
 * @Date: 2020/10/14
 */
@Data
@ApiModel(value = "批量删除班级", description = "批量删除班级")
public class ClassDeleteBatchParam implements Serializable {

    private static final long serialVersionUID = 5500730507929512059L;
    /**
     * 班级 id 数组
     */
    @NotEmpty(message = "至少选择一个班级")
    @ApiModelProperty(value = "班级 id 数组", name = "ids", required = true)
    private Long[] ids;
}
