package com.ljq.demo.springboot.web.controller;

import com.ljq.demo.springboot.common.api.ApiResult;
import com.ljq.demo.springboot.service.CommonService;
import com.ljq.demo.springboot.vo.DownloadBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<?> export(){
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




}
