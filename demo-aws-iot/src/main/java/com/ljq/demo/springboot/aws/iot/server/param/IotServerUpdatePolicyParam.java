package com.ljq.demo.springboot.aws.iot.server.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: iot 服务端更新策略请求参数
 * @Author: junqiang.lu
 * @Date: 2022/7/25
 */
@Data
public class IotServerUpdatePolicyParam implements Serializable {

    private static final long serialVersionUID = -4731571720896946773L;

    /**
     * 策略名称(已创建)
     */
    private String policyName;

    /**
     * (新)策略内容
     */
    private String policyContent;

}
