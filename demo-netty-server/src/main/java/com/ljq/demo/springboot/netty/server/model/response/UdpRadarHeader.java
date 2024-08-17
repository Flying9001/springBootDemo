package com.ljq.demo.springboot.netty.server.model.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: UDP 雷达请求头参数
 * @Author: Mr.lu
 * @Date: 2024/8/15
 */
@Data
public class UdpRadarHeader implements Serializable {

    private static final long serialVersionUID = 4765355155562316019L;


    /**
     * 固定包头标识(4byte)
     */
    private String flag = "9527";

    /**
     * 命令(1byte)
     */
    private Integer cmd;

    /**
     * 操作，11-发送请求，需要回复；12-发送请求，不需要回复；21-回复操作成功，22-回复操作失败(1byte)
     */
    private Integer operate;

    /**
     * 数据长度(从包头固定标识开始计算，总字节数)(2byte)
     */
    private Integer length;

    /**
     * 消息编号(1-255)(1byte)
     */
    private Integer msgNo;



}
