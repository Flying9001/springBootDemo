package com.ljq.demo.springboot.ffmpeg.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description: 多媒体信息
 * @Author: junqiang.lu
 * @Date: 2024/5/10
 */
@Data
@Accessors(chain = true)
public class MediaInfoResponse implements Serializable {

    private static final long serialVersionUID = -326368230008457941L;

    /**
     * 文件格式
     */
    private String format;

    /**
     * 时长，单位：秒
     */
    private Long duration;

    /**
     * 文件大小，单位：字节数
     */
    private Long size;

    /**
     * 文件md5值
     */
    private String md5;



}
