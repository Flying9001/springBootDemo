package com.ljq.demo.springboot.entity;

import java.io.Serializable;

/**
 * @Description: 基础实体类
 * @Author: junqiang.lu
 * @Date: 2019/6/16
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -4866155729098134362L;

    /**
     * 创建时间
     * */
    private String insertTime;
    /**
     * 添加人用户 id
     * */
    private Long insertOperatorId;
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
    private Long updateOperatorId;
    /**
     * 修改人身份标识;0:前台用户;1:后台用户(默认)
     * */
    private Integer updateIdentity;
    /**
     * 版本号(默认1);用于更新时对比操作
     * */
    private Integer versions;
    /**
     * 是否逻辑删除;0:不删除(默认);1:逻辑删除;所有查询sql都要带上del=0这个条件
     * */
    private Integer delSign = 0;
}
