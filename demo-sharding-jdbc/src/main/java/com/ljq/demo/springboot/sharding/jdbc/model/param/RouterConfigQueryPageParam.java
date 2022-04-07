package com.ljq.demo.springboot.sharding.jdbc.model.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 路由器配置分页查询
 * @Author: junqiang.lu
 * @Date: 2022/4/1
 */
@Data
public class RouterConfigQueryPageParam implements Serializable {

    private static final long serialVersionUID = 5643591862391911518L;

    /**
     * 设备 id
     */
    private Long deviceId;
    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 每页显示条数
     */
    private Integer pageSize;

}
