package com.ljq.demo.springboot.opencv.common.util;

import org.junit.jupiter.api.Test;

class FaceDetectUtilTest {

    String imagePath = "E:\\workDoc\\bigFile\\myface\\105_zhanglonglong105.jpg";

    @Test
    void checkFace() {
        boolean result = FaceDetectUtil.detectFace(imagePath, true);
        System.out.println(result);
    }
}