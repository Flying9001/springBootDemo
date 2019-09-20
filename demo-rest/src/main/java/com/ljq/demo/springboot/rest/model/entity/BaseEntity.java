package com.ljq.demo.springboot.rest.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 基础实体类
 * @Author: junqiang.lu
 * @Date: 2019/4/29
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 4239286263579954984L;

    /**
     * 创建时间
     * */
    private String insertTime;
    /**
     * 添加人用户 id
     * */
    private Long insertUserId;
    /**
     * 添加人身份标识,0:前台用户;1:后台用户(默认)
     * */
    private Integer insertIdentity;
    /**
     * 更新时间
     * */
    private String updateTime;
    /**
     * 修改人用户 id
     * */
    private Long updateUserId;
    /**
     * 修改人身份标识;0:前台用户;1:后台用户(默认)
     * */
    private Integer updateIdentity;
    /**
     * 版本控制字段(默认1)
     * */
    private Integer versions;
    /**
     * 逻辑删除字段,0正常(默认),1删除
     * */
    private Integer delSign = 0;

}
