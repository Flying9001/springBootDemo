package com.ljq.demo.springboot.service;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.ljq.demo.springboot.baseweb.api.ApiResult;
import com.ljq.demo.springboot.baseweb.exception.ParamsCheckException;
import com.ljq.demo.springboot.vo.DownloadBean;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

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
     * PDF 文件导出 2
     *
     * @return
     */
    byte[] exportPdf2() throws FileNotFoundException, JRException;

    /**
     * 上传文件至阿里云 oss
     *
     * @param file
     * @param uploadKey
     * @return
     * @throws Exception
     */
    ApiResult uploadOSS(MultipartFile file, String uploadKey) throws Exception;

    /**
     * 通过 sftp 上传附件至固定服务器
     *
     * @param file 文件
     * @param dir 文件夹名称
     * @return
     * @throws ParamsCheckException
     */
    ApiResult uploadSftp(MultipartFile file, String dir) throws ParamsCheckException;

    /**
     *
     * 通过 sftp 下载文件
     *
     * @param filePath 文件路径
     * @return
     * @throws JSchException
     * @throws SftpException
     * @throws IOException
     */
    ResponseEntity<byte[]> downloadSftp(String filePath) throws JSchException, SftpException, IOException;


}
