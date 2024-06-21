package com.ljq.demo.springboot.easyexcel.common.util;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.ljq.demo.springboot.easyexcel.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;

/**
 * @Description: Excel 表头校验工具类
 * @Author: junqiang.lu
 * @Date: 2024/6/20
 */
@Slf4j
public class ExcelHeaderValidateUtil {

    /**
     * 导入学生表头长度
     */
    private static final int IMPORT_STUDENT_HEADER_SIZE = 6;
    /**
     * 校验导入教师表头长度
     */
    private static final int IMPORT_TEACHER_HEADER_SIZE = 5;

    private ExcelHeaderValidateUtil() {
    }

    /**
     * 校验导入学生表头
     *
     * @param headMap
     */
    public static void validateImportStudent(Map<Integer, ReadCellData<?>> headMap) {
        if (CollUtil.isEmpty(headMap) || headMap.size() < IMPORT_STUDENT_HEADER_SIZE) {
            throw new ServiceException("导入文件与模板不符");
        }
        // 校验表头
        if (Objects.equals("学生姓名", headMap.get(0).getStringValue())
                && Objects.equals("学号",headMap.get(1).getStringValue())
                && Objects.equals("生日",headMap.get(2).getStringValue())
                && Objects.equals("年级",headMap.get(3).getStringValue())
                && Objects.equals("性别",headMap.get(4).getStringValue())
                && Objects.equals("是否住校",headMap.get(5).getStringValue())) {
            return;
        }
        throw new ServiceException("导入文件与模板不符");
    }

    /**
     * 校验导入教师表头
     *
     * @param headMap
     */
    public static void validateImportTeacher(Map<Integer, ReadCellData<?>> headMap) {
        if (CollUtil.isEmpty(headMap) || headMap.size() < IMPORT_TEACHER_HEADER_SIZE) {
            throw new ServiceException("导入文件与模板不符");
        }
        // TODO 校验表头



    }



}
