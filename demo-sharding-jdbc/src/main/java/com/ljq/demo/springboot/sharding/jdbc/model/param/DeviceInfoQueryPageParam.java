package com.ljq.demo.springboot.sharding.jdbc.model.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 设备信息分页查询
 * @Author: junqiang.lu
 * @Date: 2022/4/1
 */
@Data
public class DeviceInfoQueryPageParam implements Serializable {

    /**
     * 设备类型,1-路由器,2-音响,3-摄像头
     */
    private Integer type;
    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 每页显示条数
     */
    private Integer pageSize;

}
