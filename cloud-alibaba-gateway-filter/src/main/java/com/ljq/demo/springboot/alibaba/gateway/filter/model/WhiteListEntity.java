package com.ljq.demo.springboot.alibaba.gateway.filter.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 网关路由白名单
 * @Author: junqiang.lu
 * @Date: 2022/8/23
 */
@Data
@TableName(value = "gateway_white_list")
public class WhiteListEntity implements Serializable {

    private static final long serialVersionUID = -854919732121208131L;

    /**
     * id
     */
    @TableId(type = IdType.NONE)
    private Long id;

    /**
     * 路由类型
     */
    private String routeType;

    /**
     * 请求路径
     */
    private String path;

    /**
     * 说明
     */
    private String comment;

    /**
     * 创建时间
     */
    private Long createDate;

    /**
     * 更新时间
     */
    private Long updateDate;


}
