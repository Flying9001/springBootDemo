package com.ljq.demo.springboot.ffmpeg.controller;

import com.ljq.demo.springboot.ffmpeg.common.config.UploadConfig;
import com.ljq.demo.springboot.ffmpeg.common.util.FFmpegMediaUtil;
import com.ljq.demo.springboot.ffmpeg.model.response.AudioInfoResponse;
import com.ljq.demo.springboot.ffmpeg.model.response.VideoInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * @Description: FFmpeg 媒体文件处理控制层
 * @Author: junqiang.lu
 * @Date: 2024/5/10
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/ffmpeg/media")
public class FFmpegMediaController {

    @Resource
    private UploadConfig uploadConfig;


    /**
     * 视频上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/upload/video", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<VideoInfoResponse> uploadVideo(MultipartFile file) throws IOException {
        // 文件上传保存
        String videoFilePath = uploadConfig.getUploadPath() + File.separator + file.getOriginalFilename();
        log.info("videoFilePath: {}", videoFilePath);
        File videoFile = new File(videoFilePath);
        if (!videoFile.getParentFile().exists()) {
            videoFile.getParentFile().mkdirs();
        }
        file.transferTo(videoFile);
        // 获取视频信息
        VideoInfoResponse videoInfoResponse = FFmpegMediaUtil.getVideoInfo(videoFilePath);
        return ResponseEntity.ok(videoInfoResponse);
    }

    /**
     * 音频上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/upload/audio", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AudioInfoResponse> uploadAudio(MultipartFile file) throws IOException {
        // 文件上传保存
        String audioFilePath = uploadConfig.getUploadPath() + File.separator + file.getOriginalFilename();
        log.info("audioFilePath: {}", audioFilePath);
        File audioFile = new File(audioFilePath);
        if (!audioFile.getParentFile().exists()) {
            audioFile.getParentFile().mkdirs();
        }
        file.transferTo(audioFile);
        // 获取音频信息
        AudioInfoResponse audioInfoResponse = FFmpegMediaUtil.getAudioInfo(audioFilePath);
        return ResponseEntity.ok(audioInfoResponse);
    }




}
