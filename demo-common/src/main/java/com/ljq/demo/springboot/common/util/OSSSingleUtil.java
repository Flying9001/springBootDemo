package com.ljq.demo.springboot.common.util;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;

import java.io.InputStream;
import java.util.UUID;

/**
 * @Description: 阿里云 oss 上传工具类(低依赖版)
 * @Author: junqiang.lu
 * @Date: 2019/5/9
 */
public class OSSSingleUtil {

    private OSSSingleUtil(){

    }

    /**
     * oss 工具客户端
     */
    private volatile static OSSClient ossClient = null;

    /**
     *  上传文件至阿里云 oss
     *
     * @param ossEndpoint 阿里云 oss 站点
     * @param ossAccessKeyId 阿里云 oss 公钥
     * @param ossAccessKeySecret 阿里云 oss 私钥
     * @param bucketName 阿里云 oss bucket 名称
     * @param ossUrl 阿里云资源访问 url (不包含具体资源名称部分)
     * @param inputStream 待上传文件流
     * @param fileDir 待上传文件在阿里云 oss 中保存的目录
     * @param fileSuffix 待上传文件类型后缀名(eg: png,doc)
     * @return
     */
    public static String upload(String ossEndpoint, String ossAccessKeyId, String ossAccessKeySecret, String bucketName,
                                String ossUrl, InputStream inputStream, String fileDir, String fileSuffix) {
        initOSS(ossEndpoint, ossAccessKeyId, ossAccessKeySecret);
        try {
            StringBuilder fileUrl = new StringBuilder();
            String point = ".";
            if (!fileSuffix.startsWith(point)) {
                fileSuffix = point + fileSuffix;
            }
            if (!fileDir.endsWith("/")) {
                fileDir = fileDir.concat("/");
            }
            String ossFileName = System.currentTimeMillis() + "-" + UUID.randomUUID().toString().substring(0,18) + fileSuffix;
            fileUrl = fileUrl.append(fileDir + ossFileName);
            ossClient.putObject(bucketName, fileUrl.toString(), inputStream);
            fileUrl = fileUrl.insert(0, ossUrl);
            return fileUrl.toString();
        } catch (OSSException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 初始化 oss 客户端
     *
     * @param ossEndpoint
     * @param ossAccessKeyId
     * @param ossAccessKeySecret
     * @return
     */
    private static OSSClient initOSS(String ossEndpoint, String ossAccessKeyId, String ossAccessKeySecret) {
        if (ossClient == null ) {
            synchronized (OSSSingleUtil.class) {
                if (ossClient == null) {
                    ossClient = new OSSClient(ossEndpoint,
                            new DefaultCredentialProvider(ossAccessKeyId, ossAccessKeySecret),
                            new ClientConfiguration());
                }
            }
        }
        return ossClient;
    }

}
