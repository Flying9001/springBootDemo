package com.ljq.demo.springboot.sharding.jdbc.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * @Description: 设备信息实体
 * @Author: junqiang.lu
 * @Date: 2022/4/1
 */
@Data
@ToString(callSuper = true)
@TableName(value = "device_info")
public class DeviceInfoEntity extends BaseEntity {

    private static final long serialVersionUID = -8417097323674970608L;

    /**
     * 设备类型,1-路由器,2-音响,3-摄像头
     */
    private Integer type;
    /**
     * 设备品牌
     */
    private String brand;
    /**
     * 设备型号
     */
    private String model;
    /**
     * 设备名称
     */
    private String name;


}
