package com.ljq.demo.springboot.opencv.common.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: opencv 配置
 * @Author: junqiang.lu
 * @Date: 2024/3/18
 */
@ToString
@Getter
@Configuration
public class OpencvConfig {

    /**
     * 是否开启 opencv 调试模式
     * true: 从项目resource文件中加载人脸特征xml,实际部署,以 jar 启动无法获取到
     * false: 从本地文件加载人脸特征xml
     */
    @Value("${opencv.debug:false}")
    private Boolean opencvDebug;


}
