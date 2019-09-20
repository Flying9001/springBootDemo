package com.ljq.demo.springboot.rest.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 基础数据展示对象
 * @Author: junqiang.lu
 * @Date: 2019/9/19
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseVO implements Serializable {

    private static final long serialVersionUID = 4958470486945542796L;

    /**
     * 创建时间
     * */
    @ApiModelProperty(value = "创建时间", name = "insertTime")
    private String insertTime;
    /**
     * 添加人用户 id
     * */
    @JsonIgnore
    @ApiModelProperty(value = "添加人用户 id", name = "insertUserId")
    private Long insertUserId;
    /**
     * 添加人身份标识,0:前台用户;1:后台用户(默认)
     * */
    @JsonIgnore
    @ApiModelProperty(value = "添加人身份标识,0:前台用户;1:后台用户(默认)", name = "insertIdentity")
    private Integer insertIdentity;
    /**
     * 更新时间
     * */
    @ApiModelProperty(value = "更新时间", name = "updateTime")
    private String updateTime;
    /**
     * 修改人用户 id
     * */
    @JsonIgnore
    @ApiModelProperty(value = "修改人用户 id", name = "updateUserId")
    private Long updateUserId;
    /**
     * 修改人身份标识;0:前台用户;1:后台用户(默认)
     * */
    @JsonIgnore
    @ApiModelProperty(value = "修改人身份标识;0:前台用户;1:后台用户(默认)", name = "updateIdentity")
    private Integer updateIdentity;
    /**
     * 版本控制字段(默认1)
     * */
    @JsonIgnore
    @ApiModelProperty(value = "版本控制字段(默认1)", name = "versions")
    private Integer versions;
    /**
     * 逻辑删除字段,0正常(默认),1删除
     * */
    @JsonIgnore
    @ApiModelProperty(value = "逻辑删除字段,0正常(默认),1删除", name = "delSign")
    private Integer delSign;

}
