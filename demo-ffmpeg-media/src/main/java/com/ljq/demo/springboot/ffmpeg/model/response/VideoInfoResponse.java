package com.ljq.demo.springboot.ffmpeg.model.response;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Description: 视频文件信息返回对象
 * @Author: junqiang.lu
 * @Date: 2024/5/10
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class VideoInfoResponse extends MediaInfoResponse {

    private static final long serialVersionUID = -9016123624628502571L;

    /**
     * 比特率,单位: bps
     */
    private Integer bitRate;

    /**
     * 帧率，单位: FPS
     */
    private Float frameRate;

    /**
     * 宽度,单位: px
     */
    private Integer width;

    /**
     * 高度,单位: px
     */
    private Integer height;





}
