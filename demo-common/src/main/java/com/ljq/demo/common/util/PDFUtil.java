package com.ljq.demo.common.util;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.Map;

/**
 * @Description: pdf 导出工具类
 * @Author: junqiang.lu
 * @Date: 2018/12/25
 */
public class PDFUtil {

    private PDFUtil(){}

    private volatile static Configuration configuration;

    static {
        if (configuration == null) {
            synchronized (PDFUtil.class) {
                if (configuration == null) {
                    configuration = new Configuration(Configuration.VERSION_2_3_28);
                }
            }
        }
    }

    /**
     * freemarker 引擎渲染 html
     *
     * @param dataMap 传入 html 模板的 Map 数据
     * @param ftlFilePath html 模板文件相对路径(相对于 resources路径,路径 + 文件名)
     *                    eg: "templates/pdf_export_demo.ftl"
     * @return
     */
    public static String freemarkerRender(Map<String, Object> dataMap, String ftlFilePath) {
        Writer out = new StringWriter();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        try {
            configuration.setDirectoryForTemplateLoading(new File(ResourceFileUtil.getParent(ftlFilePath)));
            configuration.setLogTemplateExceptions(false);
            configuration.setWrapUncheckedExceptions(true);
            Template template = configuration.getTemplate(ResourceFileUtil.getFileName(ftlFilePath));
            template.process(dataMap, out);
            out.flush();
            return out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 使用 iText 生成 PDF 文档
     *
     * @param htmlTmpStr html 模板文件字符串
     * @param fontFile 所需字体文件(相对路径+文件名)
     * */
    public static byte[] createPDF(String htmlTmpStr, String fontFile) {
        ByteArrayOutputStream outputStream = null;
        byte[] result = null;
        try {
            outputStream = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlTmpStr);
            ITextFontResolver fontResolver = renderer.getFontResolver();
            // 解决中文支持问题,需要所需字体(ttc)文件
            fontResolver.addFont(ResourceFileUtil.getAbsolutePath(fontFile),BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            renderer.layout();
            renderer.createPDF(outputStream);
            result=outputStream.toByteArray();
            if(outputStream != null) {
                outputStream.flush();
                outputStream.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }









}
