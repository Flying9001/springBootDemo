package com.ljq.demo.springboot.mongodb.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 参数接收类
 *
 * @author junqiang.lu
 * @date 2021-01-06 20:03:33
 */
@Data
@ApiModel(value = "保存(单条)", description = "保存(单条)")
public class UserSaveParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     * */
    @NotBlank(message = "姓名 不能为空")
    @Length(max = 64, message = "姓名需要控制在 64 字符以内")
    @ApiModelProperty(value = "姓名", name = "name", required = true)
    private String name;
    /**
     * 年龄
     * */
    @NotNull(message = "年龄 不能为空")
    @Min(value = 0, message = "年龄设置不合理")
    @Max(value = 200, message = "年龄设置不合理")
    @ApiModelProperty(value = "年龄 不能为空,范围区间为:0-200", name = "age", required = true, example = "0")
    private Integer age;
    /**
     * 简介
     * */
    @NotBlank(message = "简介 不能为空")
    @Length(max = 255, message = "简介需要控制在 255 字符以内")
    @ApiModelProperty(value = "简介不能为空,查询需要控制在 255 字符以内", name = "introduction", required = true)
    private String introduction;


}
