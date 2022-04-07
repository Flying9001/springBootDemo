package com.ljq.demo.springboot.sharding.jdbc.model.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 路由器配置新增单条
 * @Author: junqiang.lu
 * @Date: 2022/4/1
 */
@Data
public class RouterConfigAddParam implements Serializable {

    private static final long serialVersionUID = -7638687760196574080L;

    /**
     * 设备 ID
     */
    private Long deviceId;
    /**
     * wifi 名称
     */
    private String wifiName;
    /**
     * wifi 密码
     */
    private String wifiPassword;
    /**
     * 加密类型,0-不加密,1-WPA-PSK,2-WPA2-PSK,3-WPA/WPA2-PSK
     */
    private Integer encryptType;
    /**
     * 管理员密码
     */
    private String adminPassword;
    /**
     * wifi开关,0-关闭,1-开启
     */
    private Integer wifiSwitch;
    /**
     * 是否隐藏 wifi,0-不隐藏,1-隐藏
     */
    private Integer hideSwitch;


}
