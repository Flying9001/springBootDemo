package com.ljq.demo.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.ljq.demo.springboot.baseweb.api.ApiResult;
import com.ljq.demo.springboot.baseweb.api.ResponseCode;
import com.ljq.demo.springboot.baseweb.config.OSSConfig;
import com.ljq.demo.springboot.baseweb.config.PDFExportConfig;
import com.ljq.demo.springboot.baseweb.config.SftpUploadConfig;
import com.ljq.demo.springboot.baseweb.exception.ParamsCheckException;
import com.ljq.demo.springboot.baseweb.util.OSSBootUtil;
import com.ljq.demo.springboot.baseweb.util.OSSSingleUtil;
import com.ljq.demo.springboot.baseweb.util.PDFUtil;
import com.ljq.demo.springboot.baseweb.util.ResourceFileUtil;
import com.ljq.demo.springboot.common.util.SftpUtil;
import com.ljq.demo.springboot.service.CommonService;
import com.ljq.demo.springboot.vo.DownloadBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description: 公共业务具体实现类
 * @Author: junqiang.lu
 * @Date: 2018/12/24
 */
@Slf4j
@Service("commonService")
public class CommonServiceImpl implements CommonService {

    @Autowired
    private PDFExportConfig pdfExportConfig;
    @Autowired
    private OSSConfig ossConfig;
    @Autowired
    private SftpUploadConfig uploadConfig;

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
                byte[] fileBytes = ResourceFileUtil.getBytesFromURLFile(downloadBean.getFileName());
                if (fileBytes != null && fileBytes.length > 0) {
                    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                    headers.setContentDispositionFormData("attachment", "DEMO" + System.currentTimeMillis() + ".jpg");
                    return new ResponseEntity<byte[]>(fileBytes, headers, HttpStatus.OK);
                }
                break;
            case "resource":
                downloadBean.setFileName("static/README.pdf");
                byte[] resourceBytes = ResourceFileUtil.getBytesFromResource(downloadBean.getFileName());
                if (resourceBytes != null && resourceBytes.length > 0) {
                    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                    headers.setContentDispositionFormData("attachment", "DEMO" + System.currentTimeMillis() + ".pdf");
                    return new ResponseEntity<byte[]>(resourceBytes, headers, HttpStatus.OK);
                }
                break;
            case "local" :
                downloadBean.setFileName("E:\\software\\tools\\AdbeRdr11_zh_CN11.exe");
                byte[] fileByteArray = ResourceFileUtil.getByteFromFileWithNIO(downloadBean.getFileName());
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

    /**
     * 上传文件至阿里云 oss
     *
     * @param file
     * @param uploadKey
     * @return
     * @throws Exception
     */
    @Override
    public ApiResult uploadOSS(MultipartFile file, String uploadKey) throws Exception {

        // 低依赖版本 oss 上传工具
        String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));;
        String ossFileUrlSingle = null;
        ossFileUrlSingle = OSSSingleUtil.upload(ossConfig.getEndpoint(), ossConfig.getAccessKeyId(),
                ossConfig.getAccessKeySecret(), ossConfig.getBucketName(), ossConfig.getUrl(), file.getInputStream(),
                "upload/demo", fileSuffix);

        // 高依赖版本 oss 上传工具
        String ossFileUrlBoot = null;
        ossFileUrlBoot = OSSBootUtil.upload(ossConfig, file, "upload/demo");

        Map<String, Object> resultMap = new HashMap<>(16);
        resultMap.put("ossFileUrlSingle", ossFileUrlSingle);
        resultMap.put("ossFileUrlBoot", ossFileUrlBoot);

        return ApiResult.success(resultMap);
    }

    /**
     * 通过 sftp 上传附件至固定服务器
     *
     * @param file 文件
     * @param dir 文件夹名称
     * @return
     * @throws ParamsCheckException
     */
    @Override
    public ApiResult uploadSftp(MultipartFile file, String dir) throws ParamsCheckException {
        if (Objects.isNull(file) || file.isEmpty()) {
            throw new ParamsCheckException(ResponseCode.MISS_REQUEST_PART_ERROR);
        }
        log.debug("原始文件名:{},文件大小: {} Mb", file.getOriginalFilename(), (file.getSize()/1048576));
        // 储存文件
        SftpUtil.SftpConfig sftpConfig = SftpUtil.sftpConfig();
        BeanUtil.copyProperties(uploadConfig, sftpConfig, CopyOptions.create().ignoreNullValue().ignoreError());
        String filePath;
        if (StrUtil.isNotBlank(dir)) {
            dir = dir.replaceAll("[^a-zA-Z0-9]", "");
        }
        try {
            filePath = SftpUtil.upload(sftpConfig,dir, System.currentTimeMillis() +
                    file.getOriginalFilename(), file.getInputStream());
        } catch (Exception e) {
            log.warn("文件上传失败,{}", e.getMessage());
            throw new ParamsCheckException(ResponseCode.FAIL);
        }
        return ApiResult.success(filePath);
    }

    /**
     * 通过 sftp 下载文件
     *
     * @param filePath 文件路径
     * @return
     * @throws JSchException
     * @throws SftpException
     * @throws IOException
     */
    @Override
    public ResponseEntity<byte[]> downloadSftp(String filePath) throws JSchException, SftpException, IOException {
        SftpUtil.SftpConfig sftpConfig = SftpUtil.sftpConfig();
        BeanUtil.copyProperties(uploadConfig, sftpConfig, CopyOptions.create().ignoreNullValue().ignoreError());
        byte[] bytes = SftpUtil.download(sftpConfig, filePath);
        HttpHeaders headers = new HttpHeaders();
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("UTF-8"),"ISO-8859-1"));
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
}
