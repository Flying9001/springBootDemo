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
@ApiModel(value = "REST示例-用户表删除(单条)", description = "REST示例-用户表删除(单条)")
public class RestUserDeleteParam extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 用户 id
     * */
    @NotNull(message = "用户 id 不能为空")
    @Min(value = 1, message = "用户 id 至少为 1")
    @ApiModelProperty(value = "用户 id 不能为空,至少为 1", name = "id", required = true, example = "0")
    private Long id;
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
    /**
     * 邮箱
     * */
    @NotNull(message = "邮箱 不能为空")
    @ApiModelProperty(value = "邮箱", name = "email", required = true)
    private String email;
    /**
     * 用户状态,0:所有,不区分状态,1:正常(默认),2:禁止登陆
     * */
    @NotNull(message = "用户状态,0:所有,不区分状态,1:正常(默认),2:禁止登陆 不能为空")
    @Min(value = 1, message = "用户状态,0:所有,不区分状态,1:正常(默认),2:禁止登陆 至少为 1")
    @ApiModelProperty(value = "用户状态,0:所有,不区分状态,1:正常(默认),2:禁止登陆 不能为空,至少为 1", name = "userStatus", required = true, example = "0")
    private Integer userStatus;
    /**
     * 创建时间
     * */
    @NotNull(message = "创建时间 不能为空")
    @ApiModelProperty(value = "创建时间", name = "insertTime", required = true)
    private String insertTime;
    /**
     * 添加人用户 id
     * */
    @NotNull(message = "添加人用户 id 不能为空")
    @Min(value = 1, message = "添加人用户 id 至少为 1")
    @ApiModelProperty(value = "添加人用户 id 不能为空,至少为 1", name = "insertUserId", required = true, example = "0")
    private Long insertUserId;
    /**
     * 添加人身份标识,0:前台用户;1:后台用户(默认)
     * */
    @NotNull(message = "添加人身份标识,0:前台用户;1:后台用户(默认) 不能为空")
    @Min(value = 1, message = "添加人身份标识,0:前台用户;1:后台用户(默认) 至少为 1")
    @ApiModelProperty(value = "添加人身份标识,0:前台用户;1:后台用户(默认) 不能为空,至少为 1", name = "insertIdentity", required = true, example = "0")
    private Integer insertIdentity;
    /**
     * 更新时间
     * */
    @NotNull(message = "更新时间 不能为空")
    @ApiModelProperty(value = "更新时间", name = "updateTime", required = true)
    private String updateTime;
    /**
     * 修改人用户 id
     * */
    @NotNull(message = "修改人用户 id 不能为空")
    @Min(value = 1, message = "修改人用户 id 至少为 1")
    @ApiModelProperty(value = "修改人用户 id 不能为空,至少为 1", name = "updateUserId", required = true, example = "0")
    private Long updateUserId;
    /**
     * 修改人身份标识;0:前台用户;1:后台用户(默认)
     * */
    @NotNull(message = "修改人身份标识;0:前台用户;1:后台用户(默认) 不能为空")
    @Min(value = 1, message = "修改人身份标识;0:前台用户;1:后台用户(默认) 至少为 1")
    @ApiModelProperty(value = "修改人身份标识;0:前台用户;1:后台用户(默认) 不能为空,至少为 1", name = "updateIdentity", required = true, example = "0")
    private Integer updateIdentity;
    /**
     * 版本控制字段(默认1)
     * */
    @NotNull(message = "版本控制字段(默认1) 不能为空")
    @Min(value = 1, message = "版本控制字段(默认1) 至少为 1")
    @ApiModelProperty(value = "版本控制字段(默认1) 不能为空,至少为 1", name = "versions", required = true, example = "0")
    private Integer versions;
    /**
     * 逻辑删除字段,0正常(默认),1删除
     * */
    @NotNull(message = "逻辑删除字段,0正常(默认),1删除 不能为空")
    @Min(value = 1, message = "逻辑删除字段,0正常(默认),1删除 至少为 1")
    @ApiModelProperty(value = "逻辑删除字段,0正常(默认),1删除 不能为空,至少为 1", name = "delSign", required = true, example = "0")
    private Integer delSign;


}
