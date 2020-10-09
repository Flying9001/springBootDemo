package com.ljq.demo.springboot.common.util;

import com.jcraft.jsch.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * @Description: sftp 工具类
 * @Author: junqiang.lu
 * @Date: 2020/9/23
 */
public class SftpUtil {

    private SftpUtil() {
    }

    /**
     * 每个目录下最大子文件(夹)数量
     */
    private static final int MAX_CHILD_FILE_NUMBER = 1000;
    private static volatile ChannelSftp sftp = null;
    private static volatile Session sshSession = null;

    /**
     * 附件上传
     *
     * @param sftpConfig sftp 配置信息
     * @param fileName 文件名
     * @param inputStream 文件流
     * @return
     * @throws SftpException
     * @throws JSchException
     */
    public static String upload(SftpConfig sftpConfig, String fileName, InputStream inputStream) throws SftpException, JSchException {
        return upload(sftpConfig,null, fileName, inputStream);
    }

    /**
     * 文件上传
     *
     * @param sftpConfig sftp 配置信息
     * @param relativePath 文件保存的相对路径(最后一级目录)
     * @param fileName 文件名
     * @param inputStream 文件流
     * @return
     * @throws JSchException
     * @throws SftpException
     */
    public static String upload(SftpConfig sftpConfig, String relativePath, String fileName, InputStream inputStream)
            throws JSchException, SftpException {
        init(sftpConfig);
        createFolder(sftpConfig.getPath());
        String filePath = sftpConfig.getPath();
        if (relativePath != null && !relativePath.trim().isEmpty()) {
            filePath = sftpConfig.getPath() + "/" + relativePath;
        }
        filePath = generateValidPath(filePath);
        filePath = filePath + "/" + fileName;
        sftp.put(inputStream, filePath);
        return filePath.substring(sftpConfig.getPath().length() + 1);
    }

    /**
     * 文件下载
     *
     * @param sftpConfig sftp 配置信息
     * @param fileName
     * @return
     * @throws JSchException
     * @throws SftpException
     */
    public static byte[] download(SftpConfig sftpConfig, String fileName) throws JSchException, SftpException,
            IOException {
        init(sftpConfig);
        InputStream inputStream = sftp.get(sftpConfig.getPath() + "/" + fileName);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int n;
        byte[] data = new byte[1024];
        while ((n = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, n);
        }
        buffer.flush();
        return buffer.toByteArray();
    }

    /**
     * 判断是否关闭
     *
     * @return
     */
    public static boolean isClosed() {
        if (Objects.isNull(sshSession) || Objects.isNull(sftp)) {
            return false;
        }
        if (Objects.nonNull(sshSession) && sshSession.isConnected() && Objects.nonNull(sftp) && sftp.isConnected()) {
            return true;
        }
        return false;
    }

    /**
     * 关闭
     */
    public static void close(){
        if (sshSession != null && sshSession.isConnected()) {
            sshSession.disconnect();
        }
        if (sftp != null && sftp.isConnected()) {
            sftp.disconnect();
        }
    }

    /**
     * 初始化
     *
     * @throws JSchException
     */
    private static void init(SftpConfig sftpConfig) throws JSchException {
        if (!isClosed()) {
            synchronized (SftpUtil.class) {
                if (!isClosed()) {
                    JSch jsch = new JSch();
                    jsch.getSession(sftpConfig.getUsername(), sftpConfig.getHost(), sftpConfig.getPort());
                    sshSession = jsch.getSession(sftpConfig.getUsername(), sftpConfig.getHost(), sftpConfig.getPort());
                    sshSession.setPassword(sftpConfig.getPassword());
                    Properties sshConfig = new Properties();
                    sshConfig.put("StrictHostKeyChecking", "no");
                    sshSession.setConfig(sshConfig);
                    sshSession.connect();
                    Channel channel = sshSession.openChannel("sftp");
                    channel.connect();
                    sftp = (ChannelSftp) channel;
                }
            }
        }
    }

    /**
     * 创建目录
     *
     * @param path 目录路径
     * @return
     */
    private static boolean createFolder(String path) {
        try {
            sftp.mkdir(path);
        } catch (SftpException e) {
            return false;
        }
        return true;
    }

    /**
     * 统计目录下文件(夹)数量
     *
     * @param path
     * @return
     */
    private static int countFiles(String path) throws SftpException {
        sftp.cd(path);
        return sftp.ls(path).size();
    }

    /**
     * 校验路径是否可用
     *
     * @param path
     * @return
     */
    private static boolean validatePathValid(String path) {
        int countFiles = 0;
        try {
            countFiles = countFiles(path);
        } catch (SftpException e) {
            createFolder(path);
        }
        if (countFiles <= MAX_CHILD_FILE_NUMBER) {
            return true;
        }
        return false;
    }

    /**
     * 生成有效路径
     *
     * @param path
     * @return
     */
    private static String generateValidPath(String path) {
        if (validatePathValid(path)) {
            return path;
        } else {
            String newPath = path + String.valueOf(System.currentTimeMillis()).substring(9);
            createFolder(newPath);
            return newPath;
        }
    }

    /**
     * sftp 配置信息
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class SftpConfig {
        /**
         * 主机地址
         */
        private String host;
        /**
         * sftp 连接端口
         */
        private int port;
        /**
         * 用户名
         */
        private String username;
        /**
         * 密码
         */
        private String password;
        /**
         * 文件保存根路径
         */
        private String path;

    }

    public static SftpConfig sftpConfig() {
        return new SftpUtil().new SftpConfig();
    }

    public static SftpConfig sftpConfig(String host, int port, String username, String password, String path) {
        return new SftpUtil().new SftpConfig(host, port, username, password, path);
    }



}
