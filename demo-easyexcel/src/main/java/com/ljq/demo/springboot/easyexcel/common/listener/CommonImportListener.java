package com.ljq.demo.springboot.easyexcel.common.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.read.listener.ReadListener;
import com.ljq.demo.springboot.easyexcel.common.constant.ImportApiEnum;
import com.ljq.demo.springboot.easyexcel.common.exception.ServiceException;
import com.ljq.demo.springboot.easyexcel.common.util.ExcelHeaderValidateUtil;
import com.ljq.demo.springboot.easyexcel.model.vo.BaseImportVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * @Description: Excel 导入公共监听类
 * @Author: junqiang.lu
 * @Date: 2024/6/20
 */
@Slf4j
public class CommonImportListener<T> implements ReadListener<T> {


    /**
     * 解析表头
     *
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
        log.debug("解析Excel表头");
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(attr)) {
            return;
        }
        HttpServletRequest request = attr.getRequest();
        String uri = request.getRequestURI();
        switch (ImportApiEnum.getByApiAddress(uri)) {
            case IMPORT_STUDENT_API:
                log.debug("解析学生信息表头");
                // 校验表头
                ExcelHeaderValidateUtil.validateImportStudent(headMap);
                break;
            case IMPORT_TEACHER_API:
                log.debug("解析教师信息表头");
                // 校验表头信息
                ExcelHeaderValidateUtil.validateImportTeacher(headMap);
                break;
            default:
                log.info("未知导入信息");
                throw new ServiceException("未知导入信息");
        }
    }

    /**
     * 解析数据
     *
     * @param t
     * @param analysisContext
     */
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        // 设置行号
        if (t instanceof BaseImportVo) {
            ((BaseImportVo) t).setRowNo(analysisContext.readRowHolder().getRowIndex() + 1);
        }
    }

    /**
     * 全部解析完成后的操作
     *
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.debug("Excel 解析完成");

    }
}
