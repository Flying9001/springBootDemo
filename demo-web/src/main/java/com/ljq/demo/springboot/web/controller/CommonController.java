package com.ljq.demo.springboot.web.controller;

import cn.hutool.core.date.DateUtil;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.ljq.demo.springboot.baseweb.api.ApiResult;
import com.ljq.demo.springboot.baseweb.exception.ParamsCheckException;
import com.ljq.demo.springboot.common.annotation.ParamsCheck;
import com.ljq.demo.springboot.service.CommonService;
import com.ljq.demo.springboot.vo.DownloadBean;
import io.swagger.annotations.ApiOperation;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

/**
 * @Description: 公共模块控制中心
 * @Author: junqiang.lu
 * @Date: 2018/12/24
 */
@RestController
@RequestMapping("api/demo/common")
public class CommonController {

    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private CommonService commonService;

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "upload")
    public ApiResult upload(@RequestParam(value = "file") MultipartFile file, String uploadKey){

        // TODO resolve uploaded file


        return ApiResult.success();
    }

    /**
     * 文件下载
     *
     * @param downloadBean
     * @return
     */
    @RequestMapping(value = "download", method = {RequestMethod.POST, RequestMethod.GET},
            produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> download(DownloadBean downloadBean){

        try {
            ResponseEntity<?> responseEntity = commonService.download(downloadBean);
            logger.debug("{}",responseEntity.getHeaders());
            return responseEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<String>("{ \"code\" : \"404\", \"message\" : \"not found\" }",
                headers, HttpStatus.NOT_FOUND);
    }


    /**
     * PDF 文件导出
     * 使用 a 链接即可下载;如果为 ajax/vue,则需要转换为 form 表单格式
     * eg: http://${apiAddress}/api/demo/common/export?key1=${key}&key2=${key2}
     */
    @RequestMapping(value = "/export", method = {RequestMethod.POST, RequestMethod.GET},
            produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> exportPdf(){
        try {
            ResponseEntity<?> responseEntity = commonService.export();
            return responseEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<String>("{ \"code\" : \"404\", \"message\" : \"not found\" }",
                headers, HttpStatus.NOT_FOUND);
    }

    /**
     * PDF 导出 2
     * 基于 Jasper iReport
     *
     * @return
     * @throws FileNotFoundException
     * @throws JRException
     */
    @GetMapping(value = "/export/pdf/2", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "PDF 导出2(基于Jasper iReport)", notes = "PDF 导出2(基于Jasper iReport)")
    public ResponseEntity<byte[]> exportPdf2() throws FileNotFoundException, JRException {
        String fileName = "contract" + DateUtil.format(new Date(), "yyyy-MM-dd") + ".pdf";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(commonService.exportPdf2(), headers, HttpStatus.OK);
    }

    /**
     * 上传文件至阿里云 oss
     *
     * @param file
     * @param uploadKey
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/upload/oss", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> uploadOSS(@RequestParam(value = "file") MultipartFile file, String uploadKey) throws Exception {
        ApiResult apiResult = commonService.uploadOSS(file, uploadKey);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.CREATED);
    }

    /**
     * 通过 sftp 上传附件至固定服务器
     *
     * @param file 文件
     * @param dir 文件夹名称
     * @return
     * @throws ParamsCheckException
     */
    @PostMapping(value = "/upload/sftp", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "文件上传", notes = "文件上传")
    public ResponseEntity<ApiResult> uploadSftp(@RequestParam("file") MultipartFile file,
                                            String dir) throws ParamsCheckException {
        ApiResult apiResult = commonService.uploadSftp(file, dir);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
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
    @ParamsCheck(ignore = true)
    @GetMapping(value = "/download/sftp", produces = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<byte[]> downloadSftp(String filePath) throws JSchException, SftpException, IOException {
        ResponseEntity<byte[]> responseEntity = commonService.downloadSftp(filePath);
        return responseEntity;
    }




}
