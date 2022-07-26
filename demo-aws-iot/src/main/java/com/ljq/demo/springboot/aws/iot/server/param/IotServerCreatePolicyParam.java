package com.ljq.demo.springboot.aws.iot.server.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: iot 服务端创建策略请求参数
 * @Author: junqiang.lu
 * @Date: 2022/7/25
 */
@Data
public class IotServerCreatePolicyParam implements Serializable {

    private static final long serialVersionUID = -7191846060745202611L;

    /**
     * 策略名称
     */
    private String policyName;

    /**
     * 策略内容
     */
    private String policyContent;


}
