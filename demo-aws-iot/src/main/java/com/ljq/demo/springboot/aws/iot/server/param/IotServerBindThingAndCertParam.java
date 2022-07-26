package com.ljq.demo.springboot.aws.iot.server.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: iot 服务端绑定物品与证书请求参数
 * @Author: junqiang.lu
 * @Date: 2022/7/25
 */
@Data
public class IotServerBindThingAndCertParam implements Serializable {

    private static final long serialVersionUID = -270856403544597211L;

    /**
     * 物品名称
     */
    private String thingName;

    /**
     * 证书 arn(唯一标识)
     */
    private String certArn;

}
