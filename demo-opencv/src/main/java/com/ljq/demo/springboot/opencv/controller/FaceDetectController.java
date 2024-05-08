package com.ljq.demo.springboot.opencv.controller;

import com.ljq.demo.springboot.opencv.common.config.OpencvConfig;
import com.ljq.demo.springboot.opencv.common.util.FaceDetectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Description: 人脸检测控制层
 * @Author: junqiang.lu
 * @Date: 2024/3/18
 */
@Slf4j
@RequestMapping(value = "/api/opencv")
@RestController
public class FaceDetectController {

    @Resource
    private OpencvConfig opencvConfig;

    /**
     * 人脸检测
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/face/detect", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> detectFace(MultipartFile file){
        boolean flag = false;
        try {
            flag = FaceDetectUtil.detectFace(file.getBytes(), opencvConfig.getOpencvDebug());
            log.info("图片人脸检测结果: {}", flag);
        } catch (IOException e) {
            log.error("图片读取错误", e);
        }
        return ResponseEntity.ok(flag);
    }


}
