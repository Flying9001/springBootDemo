package com.ljq.demo.springboot.mybatisplus.model.param.clazz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 班级参数接收类
 *
 * @author junqiang.lu
 * @date 2020-10-09 17:27:32
 */
@Data
@ApiModel(value = "班级删除(单条)", description = "班级删除(单条)")
public class ClassDeleteParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     **/
    @NotNull(message = "id 不能为空")
    @Min(value = 1, message = "请输入正确的班级ID")
    @ApiModelProperty(value = "id不能为空", name = "id", required = true, example = "1")
    private Long id;


}
