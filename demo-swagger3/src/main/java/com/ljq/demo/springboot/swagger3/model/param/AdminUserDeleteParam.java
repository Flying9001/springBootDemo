package com.ljq.demo.springboot.swagger3.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 管理员用户删除(单条)
 *
 * @author junqiang.lu
 * @date 2021-01-25 19:23:35
 */
@Data
@ApiModel(value = "管理员用户删除(单条)", description = "管理员用户删除(单条)")
public class AdminUserDeleteParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     **/
    @NotNull(message = "id 不能为空")
    @ApiModelProperty(value = "id不能为空", name = "id")
    private Long id;


}
