package com.ljq.demo.springboot.swagger3.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

/**
 * 管理员用户实体类
 *
 * @author junqiang.lu
 * @date 2021-01-25 19:23:35
 */
@Data
@TableName(value = "admin_user", resultMap = "adminUserMap")
@ApiModel(value = "管理员用户", description = "管理员用户")
public class AdminUserEntity implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * id
     **/
    @TableId(value = "ID", type = IdType.AUTO)
    @ApiModelProperty(value = "id", name = "id")
    private Long id;
    /**
     * 名称
     **/
    @TableField(value = "NAME")
    @ApiModelProperty(value = "名称", name = "name")
    private String name;
    /**
     * 邮箱,账号
     **/
    @TableField(value = "EMAIL")
    @ApiModelProperty(value = "邮箱,账号", name = "email")
    private String email;
    /**
     * 登录密码
     **/
    @TableField(value = "PASSCODE")
    @ApiModelProperty(value = "登录密码", name = "passcode")
    private String passcode;
    /**
     * 是否启用,0-未启用,1-启用
     **/
    @TableField(value = "ENABLED")
    @ApiModelProperty(value = "是否启用,0-未启用,1-启用", name = "enabled")
    private Integer enabled;
    /**
     * 等级
     **/
    @TableField(value = "LEVEL")
    @ApiModelProperty(value = "等级", name = "level")
    private Integer level;
    /**
     * 创建时间
     **/
    @TableField(value = "CREATE_TIME")
    @ApiModelProperty(value = "创建时间", name = "createTime")
    private String createTime;
    /**
     * 创建用户 id
     **/
    @TableField(value = "CREATE_USER_ID")
    @ApiModelProperty(value = "创建用户 id", name = "createUserId")
    private Long createUserId;
    /**
     * 更新时间
     **/
    @TableField(value = "UPDATE_TIME")
    @ApiModelProperty(value = "更新时间", name = "updateTime")
    private String updateTime;
    /**
     * 更新用户 id
     **/
    @TableField(value = "UPDATE_USER_ID")
    @ApiModelProperty(value = "更新用户 id", name = "updateUserId")
    private Long updateUserId;

}
