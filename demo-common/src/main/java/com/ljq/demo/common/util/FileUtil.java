package com.ljq.demo.common.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * @Description: 文件处理工具
 * @Author: junqiang.lu
 * @Date: 2018/12/24
 */
public class FileUtil {

    private FileUtil(){}


    /**
     * 从 URL 中读取文件到字节数组,java 读取网络文件
     *
     * @param urlText
     * @return
     * @throws Exception
     */
    public static byte[] toBytesFromURLFile(String urlText) throws Exception {
        URL url = new URL(urlText);
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try (InputStream inputStream = url.openStream()) {
            int n = 0;
            byte [] buffer = new byte[ 1024 ];
            while (-1 != (n = inputStream.read(buffer))) {
                output.write(buffer, 0, n);
            }
        }
        return output.toByteArray();
    }

}
