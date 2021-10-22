package com.ljq.demo.springboot.alibaba.gateway.filter.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 路由实体类
 * @Author: junqiang.lu
 * @Date: 2021/10/20
 */
@Data
@TableName(value = "gateway_route")
public class RouteEntity implements Serializable {

    private static final long serialVersionUID = -7838086812254411423L;

    /**
     * 数据库 ID
     */
    @TableId(type = IdType.NONE)
    private Long id;
    /**
     * 路由 id
     */
    private String routeId;
    /**
     * 路由转发地址
     */
    private String uri;
    /**
     * 断言
     */
    private String predicates;
    /**
     * 过滤器
     */
    private String filters;
    /**
     * 其他参数
     */
    private String metadata;
    /**
     * 过滤器等级,执行顺序,数值越小优先级越高
     */
    private Integer filterOrder;
    /**
     * 创建时间
     */
    private Long createDate;
    /**
     * 更新时间
     */
    private Long updateDate;

}
