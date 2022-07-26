package com.ljq.demo.springboot.aws.iot.server.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 证书对象
 * @Author: junqiang.lu
 * @Date: 2022/5/5
 */
@Data
public class CertificateVo implements Serializable {

    private static final long serialVersionUID = -1339023758966458459L;

    /**
     * 证书资源唯一标识
     */
    private String certificateArn;

    /**
     * 证书 id
     */
    private String certificateId;

    /**
     * 证书数据
     */
    private String certificatePem;

    /**
     * 公钥
     */
    private String publicKey;

    /**
     * 私钥
     */
    private String privateKey;



}
