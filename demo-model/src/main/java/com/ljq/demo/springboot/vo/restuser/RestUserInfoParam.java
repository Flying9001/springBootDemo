package com.ljq.demo.springboot.vo.restuser;

import com.ljq.demo.springboot.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * REST示例-用户表参数接收类
 *
 * @author junqiang.lu
 * @date 2019-09-19 17:19:42
 */
@Data
@ApiModel(value = "REST示例-用户表查询详情(单条)", description = "REST示例-用户表查询详情(单条)")
public class RestUserInfoParam extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 用户 id
     * */
    @NotNull(message = "用户 id 不能为空")
    @Min(value = 1, message = "用户 id 至少为 1")
    @ApiModelProperty(value = "用户 id 不能为空,至少为 1", name = "id", required = true, example = "0")
    private Long id;

}
