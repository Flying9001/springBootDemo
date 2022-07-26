package com.ljq.demo.springboot.aws.iot.server.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: iot 服务端绑定证书与策略请求参数
 * @Author: junqiang.lu
 * @Date: 2022/7/25
 */
@Data
public class IotServerBindCertAndPolicyParam implements Serializable {

    private static final long serialVersionUID = -2428432463357940096L;

    /**
     * 证书 arn
     */
    private String certArn;

    /**
     * 策略名称
     */
    private String policyName;

}
