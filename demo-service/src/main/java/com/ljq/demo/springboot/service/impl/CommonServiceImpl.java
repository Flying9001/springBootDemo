package com.ljq.demo.springboot.service.impl;

import com.ljq.demo.springboot.common.config.PDFExportConfig;
import com.ljq.demo.springboot.common.util.FileUtil;
import com.ljq.demo.springboot.common.util.PDFUtil;
import com.ljq.demo.springboot.service.CommonService;
import com.ljq.demo.springboot.vo.DownloadBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 公共业务具体实现类
 * @Author: junqiang.lu
 * @Date: 2018/12/24
 */
@Service("commonService")
public class CommonServiceImpl implements CommonService {

    @Autowired
    private PDFExportConfig pdfExportConfig;

    /**
     * 下载
     * @param downloadBean
     * @return
     */
    @Override
    public ResponseEntity<?> download(DownloadBean downloadBean) throws Exception {
        HttpHeaders headers = new HttpHeaders();

        /**
         * 文件名为 null,返回字符串
         */
        if (downloadBean.getFileName() == null || "".equalsIgnoreCase(downloadBean.getFileName())) {
            headers.setContentType(MediaType.TEXT_HTML);
            return new ResponseEntity<String>("File name is null", headers, HttpStatus.NOT_FOUND);
        }

        /**
         * 根据文件名下载对应的文件
         * fileName:
         *     url: 下载网络文件
         *     resource: 下载项目中的文件
         *     local: 下载(服务器)本地文件
         */
        switch (downloadBean.getFileName()) {
            case "url":
                downloadBean.setFileName("https://upload-images.jianshu.io/upload_images/4412479-6895144ef53d208c.jpg?" +
                        "imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");
                byte[] fileBytes = FileUtil.getBytesFromURLFile(downloadBean.getFileName());
                if (fileBytes != null && fileBytes.length > 0) {
                    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                    headers.setContentDispositionFormData("attachment", "DEMO" + System.currentTimeMillis() + ".jpg");
                    return new ResponseEntity<byte[]>(fileBytes, headers, HttpStatus.OK);
                }
                break;
            case "resource":
                downloadBean.setFileName("static/README.pdf");
                byte[] resourceBytes = FileUtil.getBytesFromResource(downloadBean.getFileName());
                if (resourceBytes != null && resourceBytes.length > 0) {
                    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                    headers.setContentDispositionFormData("attachment", "DEMO" + System.currentTimeMillis() + ".pdf");
                    return new ResponseEntity<byte[]>(resourceBytes, headers, HttpStatus.OK);
                }
                break;
            case "local" :
                downloadBean.setFileName("E:\\software\\tools\\AdbeRdr11_zh_CN11.exe");
                byte[] fileByteArray = FileUtil.getByteFromFileWithNIO(downloadBean.getFileName());
                if (fileByteArray != null &&fileByteArray.length > 0) {
                    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                    headers.setContentDispositionFormData("attachment", "DEMO" + System.currentTimeMillis() + ".exe");
                    return new ResponseEntity<byte[]>(fileByteArray, headers, HttpStatus.OK);
                }
                break;
            default:
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return new ResponseEntity<String>("{ \"code\" : \"404\", \"message\" : \"not found\" }",
                        headers, HttpStatus.NOT_FOUND);
        }

        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<String>("{ \"code\" : \"404\", \"message\" : \"not found\" }",
                headers, HttpStatus.NOT_FOUND);
    }

    /**
     * PDF 文件导出
     *
     * @return
     */
    @Override
    public ResponseEntity<?> export() {
        HttpHeaders headers = new HttpHeaders();

        /**
         * 数据导出(PDF 格式)
         */
        Map<String, Object> dataMap = new HashMap<>(16);
        dataMap.put("statisticalTime",new Date().toString());

        String htmlStr = PDFUtil.freemarkerRender(dataMap, pdfExportConfig.getEmployeeKpiFtl());
        byte[] pdfBytes = PDFUtil.createPDF(htmlStr, pdfExportConfig.getFontSimsun());
        if (pdfBytes != null && pdfBytes.length > 0) {
            String fileName = System.currentTimeMillis() + (int) (Math.random() * 90000 + 10000) + ".pdf";
            headers.setContentDispositionFormData("attachment", fileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<byte[]>(pdfBytes, headers, HttpStatus.OK);
        }

        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<String>("{ \"code\" : \"404\", \"message\" : \"not found\" }",
                headers, HttpStatus.NOT_FOUND);
    }
}
