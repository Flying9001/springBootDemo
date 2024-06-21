package com.ljq.demo.springboot.easyexcel.common.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Description: excel 导入导出工具类
 * @Author: junqiang.lu
 * @Date: 2024/6/20
 */
public class EasyExcelUtil {

    private EasyExcelUtil() {
    }

    /**
     * 读取 excel
     *
     * @param inputStream 文件流
     * @param clazz excel 解析参数对象类
     * @param readListener Excel 读取监听器
     * @return
     */
    public static <T> List<T> readExcel(InputStream inputStream, Class<T> clazz, ReadListener<T> readListener) {
        return EasyExcel.read(inputStream, clazz, readListener)
                .sheet()
                .doReadSync();
    }

    /**
     * 导出 excel
     *
     * @param response
     * @param filename
     * @param sheetName
     * @param head
     * @param data
     * @param <T>
     * @throws IOException
     */
    public static <T> void writeExcel(HttpServletResponse response, String filename, String sheetName,
                                 Class<T> head, List<T> data) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        filename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + filename);
        // 输出 Excel
        EasyExcel.write(response.getOutputStream(), head)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .sheet(sheetName).doWrite(data);
    }


}
