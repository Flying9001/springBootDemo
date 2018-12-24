package com.ljq.demo.springboot.service.impl;

import com.ljq.demo.common.util.FileUtil;
import com.ljq.demo.springboot.service.CommonService;
import com.ljq.demo.springboot.vo.DownloadBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @Description: 公共业务具体实现类
 * @Author: junqiang.lu
 * @Date: 2018/12/24
 */
@Service("commonService")
public class CommonServiceImpl implements CommonService {


    /**
     * 下载
     * @param downloadBean
     * @return
     */
    @Override
    public ResponseEntity<?> download(DownloadBean downloadBean) throws Exception {
        HttpHeaders headers = new HttpHeaders();

        /**
         * 文件名为 null,返回 404 页面
         */
        if (downloadBean.getFileName() == null || "".equalsIgnoreCase(downloadBean.getFileName())) {
            headers.setContentType(MediaType.TEXT_HTML);
            return new ResponseEntity<String>("File name is null", headers, HttpStatus.NOT_FOUND);
        }

        /**
         * 文件名不是 "demo",返回 json 格式字符串
         */
        if (!"demo".equalsIgnoreCase(downloadBean.getFileName())) {
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            return new ResponseEntity<String>("{ \"code\" : \"404\", \"message\" : \"not found\" }",
                    headers, HttpStatus.NOT_FOUND);
        }

        /**
         * 文件名为 "demo",返回文件
         */
        if ("demo".equalsIgnoreCase(downloadBean.getFileName())) {
            downloadBean.setFileName("https://upload-images.jianshu.io/upload_images/4412479-6895144ef53d208c.jpg?" +
                    "imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");
        }

        byte[] fileBytes = FileUtil.toBytesFromURLFile(downloadBean.getFileName());

        if (fileBytes == null || fileBytes.length == 0) {
            headers.setContentType(MediaType.TEXT_HTML);
            return new ResponseEntity<String>("File doesn't exist", headers, HttpStatus.NOT_FOUND);
        }

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "DEMO" + System.currentTimeMillis() + ".jpg");
        return new ResponseEntity<byte[]>(fileBytes, headers, HttpStatus.OK);
    }
}
