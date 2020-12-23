package com.ljq.demo.springboot.mybatisplus.model.param.clazz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Description: 查询班级集合
 * @Author: junqiang.lu
 * @Date: 2020/12/22
 */
@Data
@ApiModel(value = "查询班级集合", description = "查询班级集合")
public class ClassCollectionParam implements Serializable {

    private static final long serialVersionUID = 8935635748929032911L;

    /**
     * id 数组
     */
    @NotEmpty(message = "请选择需要查询的班级信息")
    @Size(max = 100, message = "一次最多查询 100 条记录")
    @ApiModelProperty(value = "班级 ID 集合", name = "ids", required = true, example = "[1,2,3]")
    private Long[] ids;

}
