package com.ljq.demo.springboot.ffmpeg.model.response;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Description: 音频文件信息返回对象
 * @Author: junqiang.lu
 * @Date: 2024/5/10
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class AudioInfoResponse extends MediaInfoResponse {

    private static final long serialVersionUID = 3573655613715240188L;


    /**
     * 采样率
     */
    private Integer samplingRate;

    /**
     * 音频通道数量,1-单声道,2-立体声
     */
    private Integer channels;

    /**
     * 比特率,单位: bps
     */
    private Integer bitRate;

    /**
     * 位深度
     */
    private String bitDepth;



}
