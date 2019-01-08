package com.ljq.demo.springboot.service;

import com.ljq.demo.springboot.vo.DownloadBean;
import org.springframework.http.ResponseEntity;

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


}
