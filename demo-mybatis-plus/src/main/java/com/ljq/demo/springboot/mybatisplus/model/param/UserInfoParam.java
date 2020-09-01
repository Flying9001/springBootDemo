package com.ljq.demo.springboot.mybatisplus.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户表参数接收类
 *
 * @author junqiang.lu
 * @date 2020-08-31 14:09:53
 */
@Data
@ApiModel(value = "用户表查询详情(单条)", description = "用户表查询详情(单条)")
public class UserInfoParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id 主键
     * */
    @NotNull(message = "id 主键 不能为空")
    @Min(value = 1, message = "id 主键 至少为 1")
    @ApiModelProperty(value = "id 主键 不能为空,至少为 1", name = "id", required = true, example = "0")
    private Long id;


}
