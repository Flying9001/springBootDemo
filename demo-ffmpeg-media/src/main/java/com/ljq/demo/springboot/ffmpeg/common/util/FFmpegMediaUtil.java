package com.ljq.demo.springboot.ffmpeg.common.util;

import com.ljq.demo.springboot.ffmpeg.model.response.AudioInfoResponse;
import com.ljq.demo.springboot.ffmpeg.model.response.VideoInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.info.AudioInfo;
import ws.schild.jave.info.MultimediaInfo;
import ws.schild.jave.info.VideoInfo;

import java.io.File;
import java.nio.file.Files;
import java.util.Objects;

/**
 * @Description: FFmpeg 音视频工具类
 * @Author: junqiang.lu
 * @Date: 2024/5/10
 */
@Slf4j
public class FFmpegMediaUtil {


    /**
     * 获取视频信息
     *
     * @param videoPath 视频路径
     * @return 视频信息
     */
    public static VideoInfoResponse getVideoInfo(String videoPath) {
        VideoInfoResponse response = null;
        try {
            // 解析文件
            File videoFile = new File(videoPath);
            MultimediaObject multimediaObject = new MultimediaObject(videoFile);
            MultimediaInfo multimediaInfo = multimediaObject.getInfo();
            VideoInfo videoInfo = multimediaInfo.getVideo();
            // 判断是否为视频
            if (Objects.isNull(videoInfo) || videoInfo.getBitRate() < 0) {
                return null;
            }
            response = new VideoInfoResponse();
            response.setFormat(multimediaInfo.getFormat())
                    .setDuration(multimediaInfo.getDuration() / 1000)
                    .setSize(videoFile.length())
                    .setMd5(DigestUtils.md5DigestAsHex(Files.readAllBytes(videoFile.toPath())));
            response.setBitRate(videoInfo.getBitRate())
                    .setFrameRate(videoInfo.getFrameRate())
                    .setWidth(videoInfo.getSize().getWidth())
                    .setHeight(videoInfo.getSize().getHeight());
            return response;
        } catch (Exception e) {
            log.warn("Error processing video file", e);
        }
        return response;
    }

    /**
     * 获取音频信息
     *
     * @param audioPath 音频路径
     * @return 音频信息
     */
    public static AudioInfoResponse getAudioInfo(String audioPath) {
        AudioInfoResponse response = null;
        try {
            // 解析文件
            File videoFile = new File(audioPath);
            MultimediaObject multimediaObject = new MultimediaObject(videoFile);
            MultimediaInfo multimediaInfo = multimediaObject.getInfo();
            AudioInfo audioInfo = multimediaInfo.getAudio();
            // 判断是否为音频
            if (Objects.isNull(audioInfo) || Objects.nonNull(multimediaInfo.getVideo())) {
                return null;
            }
            response = new AudioInfoResponse();
            response.setFormat(multimediaInfo.getFormat())
                    .setDuration(multimediaInfo.getDuration() / 1000)
                    .setSize(videoFile.length())
                    .setMd5(DigestUtils.md5DigestAsHex(Files.readAllBytes(videoFile.toPath())));
            response.setSamplingRate(audioInfo.getSamplingRate())
                    .setBitRate(audioInfo.getBitRate())
                    .setChannels(audioInfo.getChannels())
                    .setBitDepth(audioInfo.getBitDepth());
            return response;
        } catch (Exception e) {
            log.warn("Error processing audio file", e);
        }
        return response;
    }


}
