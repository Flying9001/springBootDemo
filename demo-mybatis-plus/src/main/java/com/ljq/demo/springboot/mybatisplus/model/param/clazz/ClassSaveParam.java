package com.ljq.demo.springboot.mybatisplus.model.param.clazz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 班级参数接收类
 *
 * @author junqiang.lu
 * @date 2020-10-09 17:27:32
 */
@Data
@ApiModel(value = "班级保存(单条)", description = "班级保存(单条)")
public class ClassSaveParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 班主任ID
     * */
    @NotNull(message = "班主任ID 不能为空")
    @Min(value = 1, message = "班主任ID 至少为 1")
    @ApiModelProperty(value = "班主任ID 不能为空,至少为 1", name = "headTeacherId", required = true, example = "0")
    private Long headTeacherId;
    /**
     * 班级名称
     * */
    @NotBlank(message = "班级名称 不能为空")
    @Length(max = 30, message = "班级名称需要控制在 30 字符以内")
    @ApiModelProperty(value = "班级名称", name = "name", required = true)
    private String name;


}
