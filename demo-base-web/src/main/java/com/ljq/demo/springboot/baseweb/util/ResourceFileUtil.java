package com.ljq.demo.springboot.baseweb.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description: 文件处理工具
 * @Author: junqiang.lu
 * @Date: 2018/12/24
 */
public class ResourceFileUtil {

    private ResourceFileUtil(){}


    /**
     * 从 URL 中读取文件到字节数组,java 读取网络文件
     *
     * @param urlText url 字符串
     * @return
     * @throws Exception
     */
    public static byte[] getBytesFromURLFile(String urlText) throws Exception {
        URL url = new URL(urlText);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputStream inputStream = url.openStream();
        int n = 0;
        byte[] buffer = new byte[1024];
        while (-1 != (n = inputStream.read(buffer))) {
            output.write(buffer, 0, n);
        }

        return output.toByteArray();
    }

    /**
     * 读取项目中的静态资源文件
     *
     * @param fileName 文件相对路径,根路径为 src/main/resources
     * @return
     * @throws IOException
     */
    public static byte[] getBytesFromResource(String fileName) throws IOException {
        ClassPathResource resource =  new ClassPathResource(fileName);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        InputStream inputStream = resource.getInputStream();
        int n = 0;
        byte[] buffer = new byte[1024];
        while (-1 != (n = inputStream.read(buffer))) {
            outputStream.write(buffer, 0, n);
        }

        return outputStream.toByteArray();
    }

    /**
     * NIO 方式读取本地文件
     * 
     * @param filename 文件绝对路径
     * @return
     * @throws IOException
     */
    public static byte[] getByteFromFileWithNIO(String filename)throws IOException{

        File f = new File(filename);
        if(!f.exists()){
            throw new FileNotFoundException(filename);
        }
        FileChannel channel = null;
        FileInputStream fs = null;
        try{
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int)channel.size());
            channel.read(byteBuffer);
            return byteBuffer.array();
        }catch (IOException e) {
            e.printStackTrace();
            throw e;
        }finally{
            try{
                channel.close();
                fs.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取资源文件
     *
     * @param relativePath 资源文件相对路径(相对于 resources路径,路径 + 文件名)
     *                     eg: "templates/pdf_export_demo.ftl"
     * @return
     * @throws FileNotFoundException
     */
    public static File getFile(String relativePath) throws FileNotFoundException {
        if (relativePath == null || relativePath.length() == 0) {
            return null;
        }
        if (relativePath.startsWith("/")) {
            relativePath = relativePath.substring(1);
        }
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX
                + relativePath);

        return file;
    }

    /**
     * 获取资源绝对路径
     *
     * @param relativePath 资源文件相对路径(相对于 resources路径,路径 + 文件名)
     *                     eg: "templates/pdf_export_demo.ftl"
     * @return
     * @throws FileNotFoundException
     */
    public static String getAbsolutePath(String relativePath) throws FileNotFoundException {
        return getFile(relativePath).getAbsolutePath();
    }

    /**
     * 获取资源父级目录
     *
     * @param relativePath 资源文件相对路径(相对于 resources路径,路径 + 文件名)
     *                     eg: "templates/pdf_export_demo.ftl"
     * @return
     * @throws FileNotFoundException
     */
    public static String getParent(String relativePath) throws FileNotFoundException {
        return getFile(relativePath).getParent();
    }

    /**
     * 获取资源文件名
     *
     * @param relativePath 资源文件相对路径(相对于 resources路径,路径 + 文件名)
     *                     eg: "templates/pdf_export_demo.ftl"
     * @return
     * @throws FileNotFoundException
     */
    public static String getFileName(String relativePath) throws FileNotFoundException {
        return getFile(relativePath).getName();
    }



}
