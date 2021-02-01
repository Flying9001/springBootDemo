package com.ljq.demo.springboot.swagger3.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * 管理员用户批量删除
 *
 * @author junqiang.lu
 * @date 2021-01-25 19:23:35
 */
@Data
@ApiModel(value = "管理员用户批量删除", description = "管理员用户删除批量")
public class AdminUserDeleteBatchParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id 列表
     **/
    @NotEmpty(message = "id 不能为空")
    @ApiModelProperty(value = "id不能为空", name = "idList")
    private List<Long> idList;


}
