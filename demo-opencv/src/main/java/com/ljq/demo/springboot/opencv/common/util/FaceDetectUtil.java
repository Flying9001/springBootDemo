package com.ljq.demo.springboot.opencv.common.util;


import lombok.extern.slf4j.Slf4j;
import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.Objects;

/**
 * @Description: 人脸检测工具
 * @Author: junqiang.lu
 * @Date: 2024/3/15
 */
@Slf4j
public class FaceDetectUtil {

    private static  CascadeClassifier faceDetector;


    /**
     * 初始化 opencv
     *
     * @param isDebug
     * @return
     */
    public static CascadeClassifier init(boolean isDebug) {
        if (Objects.isNull(faceDetector) || faceDetector.empty()) {
            synchronized (FaceDetectUtil.class) {
                if (Objects.isNull(faceDetector) || faceDetector.empty()) {
                    try {
                        // 加载 openCV 函数库
                        OpenCV.loadShared();
                        log.info("opencv 函数库加载成功");
                        // 初始化人脸探测器
                        faceDetector = new CascadeClassifier();
                        String xmlFilePath =  "opencv" + File.separator + "haarcascade_frontalface_alt.xml";
                        boolean detectorInitResult = false;
                        if (isDebug) {
                            File xmlFile = ResourceUtils.getFile("classpath:" + xmlFilePath);
                            detectorInitResult = faceDetector.load(xmlFile.getAbsolutePath());
                        } else {
                            detectorInitResult = faceDetector.load(System.getProperty("user.dir") + File.separator + xmlFilePath);
                        }
                        log.info("opencv 人脸检测工具加载结果: {}", detectorInitResult);
                    } catch (Exception e) {
                        log.error("opencv face check init error", e);
                    }
                }
            }
        }
        return faceDetector;
    }


    /**
     * 检测本地照片中是否包含人脸
     *
     * @param imgPath
     * @param isDebug
     * @return
     */
    public static boolean detectFace(String imgPath, boolean isDebug) {
        init(isDebug);
        // 读取图片
        Mat image = Imgcodecs.imread(imgPath);
        return detectFace(image, isDebug);
    }

    /**
     * 检测照片中是否包含人脸
     *
     * @param byteArray
     * @param isDebug
     * @return
     */
    public static boolean detectFace(byte[] byteArray, boolean isDebug) {
        init(isDebug);
        // 读取图片
        Mat image = Imgcodecs.imdecode(new MatOfByte(byteArray), Imgcodecs.IMREAD_UNCHANGED);
        return detectFace(image, isDebug);
    }

    /**
     * 检测照片中是否包含人脸
     *
     * @param image
     * @param isDebug
     * @return
     */
    public static boolean detectFace(Mat image, boolean isDebug) {
        // 读取图片
        if (image.empty()) {
            return false;
        }
        // 人脸特征值匹配
        MatOfRect face = new MatOfRect();
        init(isDebug).detectMultiScale(image, face);
        // 特征匹配
        Rect[] rects = face.toArray();
        if (rects.length > 0) {
            return true;
        }
        return false;
    }




}
