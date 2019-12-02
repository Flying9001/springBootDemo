package com.ljq.demo.springboot.vo.restuser;

import com.ljq.demo.springboot.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * REST示例-用户表参数接收类
 *
 * @author junqiang.lu
 * @date 2019-09-19 17:19:42
 */
@Data
@ApiModel(value = "REST示例-用户表保存(单条)", description = "REST示例-用户表保存(单条)")
public class RestUserSaveParam extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     * */
    @NotNull(message = "用户名 不能为空")
    @ApiModelProperty(value = "用户名", name = "userName", required = true)
    private String userName;
    /**
     * 密码
     * */
    @NotNull(message = "密码 不能为空")
    @ApiModelProperty(value = "密码", name = "passcode", required = true)
    private String passcode;

}
