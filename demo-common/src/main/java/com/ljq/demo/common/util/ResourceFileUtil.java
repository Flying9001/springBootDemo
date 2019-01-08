package com.ljq.demo.common.util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @Description: 项目静态资源文件工具类
 *     仅可用于包含在web项目中的资源文件路径,资源文件必须放置于 web 模块下
 * @Author: junqiang.lu
 * @Date: 2019/1/4
 */
public class ResourceFileUtil {


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
