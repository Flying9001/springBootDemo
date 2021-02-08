package com.ljq.demo.springboot.common.util;

import cn.hutool.core.io.resource.ResourceUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;

import java.io.FileNotFoundException;
import java.util.Map;

/**
 * @Description: jasper 导出工具类
 * @Author: junqiang.lu
 * @Date: 2021/2/4
 */
public class JasperPdfUtil {

    private JasperPdfUtil(){
    }

    /**
     * 导出 PDF
     *
     * @param templatePath jrxml 模板路径(base classpath)
     * @param paramMap 数据对象
     * @return
     * @throws FileNotFoundException
     * @throws JRException
     */
    public static byte[] exportPdfFromXml(String templatePath, Map<String, Object> paramMap) throws FileNotFoundException, JRException {
        JasperReport jasperReport = getJasperReportFromXml(templatePath);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramMap, new JREmptyDataSource());
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    /**
     * 导出 PDF
     *
     * @param templatePath jasper 模板路径(base classpath)
     * @param paramMap 数据对象
     * @return
     * @throws FileNotFoundException
     * @throws JRException
     */
    public static byte[] exportPdfFromJasper(String templatePath, Map<String, Object> paramMap) throws FileNotFoundException, JRException {
        JasperReport jasperReport = getJasperReportFromJasper(templatePath);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramMap, new JREmptyDataSource());
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    /**
     * 获取导出对象,从 xml 中
     *
     * @param templatePath 模板路径(base classpath)
     * @return
     * @throws FileNotFoundException
     * @throws JRException
     */
    private static JasperReport getJasperReportFromXml(String templatePath) throws FileNotFoundException, JRException {
        return JasperCompileManager.compileReport(ResourceUtil.getStream(templatePath));
    }


    /**
     * 获取导出对象,从 jasper 中
     * (jasper 为 jrxml 编译后生成的文件)
     *
     * @param templatePath 模板路径(base classpath)
     * @return
     * @throws FileNotFoundException
     * @throws JRException
     */
    private static JasperReport getJasperReportFromJasper(String templatePath) throws FileNotFoundException, JRException {
        return (JasperReport) JRLoader.loadObject(ResourceUtil.getStream(templatePath));
    }




}
