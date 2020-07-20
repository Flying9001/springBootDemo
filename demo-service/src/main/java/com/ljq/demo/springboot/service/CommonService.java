package com.ljq.demo.springboot.service;

import com.ljq.demo.springboot.baseweb.api.ApiResult;
import com.ljq.demo.springboot.vo.DownloadBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 公共业务
 * @Author: junqiang.lu
 * @Date: 2018/12/24
 */
public interface CommonService {


    /**
     * 下载
     * @param downloadBean
     * @return
     */
    ResponseEntity<?> download(DownloadBean downloadBean) throws Exception;

    /**
     * PDF 文件导出
     * @return
     */
    ResponseEntity<?> export();

    /**
     * 上传文件至阿里云 oss
     *
     * @param file
     * @param uploadKey
     * @return
     * @throws Exception
     */
    ApiResult uploadOSS(MultipartFile file, String uploadKey) throws Exception;


}
