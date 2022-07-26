package com.ljq.demo.springboot.aws.iot.server.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: iot 服务端创建物品类型请求参数
 * @Author: junqiang.lu
 * @Date: 2022/7/25
 */
@Data
public class IotServerCreateThingTypeParam implements Serializable {

    private static final long serialVersionUID = -6240469939141838343L;

    /**
     * 物品类型名称
     */
    private String thingTypeName;

}
