package com.ljq.demo.springboot.mybatisplus.model.param.clazz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
@ApiModel(value = "班级修改(单条)", description = "班级修改(单条)")
public class ClassUpdateParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     **/
    @NotNull(message = "id 不能为空")
    @Min(value = 1, message = "请输入正确的班级ID")
    @ApiModelProperty(value = "id不能为空", name = "id", required = true, example = "1")
    private Long id;
    /**
     * 班主任ID
     * */
    @Min(value = 1, message = "班主任ID 至少为 1")
    @ApiModelProperty(value = "班主任ID 不能为空,至少为 1", name = "headTeacherId", required = true, example = "0")
    private Long headTeacherId;
    /**
     * 班级名称
     * */
    @Length(max = 30, message = "班级名称需要控制在 30 字符以内")
    @ApiModelProperty(value = "班级名称", name = "name")
    private String name;


}
