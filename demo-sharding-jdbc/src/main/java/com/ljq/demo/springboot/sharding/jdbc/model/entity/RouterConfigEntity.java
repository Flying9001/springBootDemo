package com.ljq.demo.springboot.sharding.jdbc.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * @Description: 路由器配置实体
 * @Author: junqiang.lu
 * @Date: 2022/4/1
 */
@Data
@ToString(callSuper = true)
@TableName(value = "router_config")
public class RouterConfigEntity extends BaseEntity {

    private static final long serialVersionUID = 4849091384661358788L;

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
