package com.ljq.demo.springboot.easyexcel.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.ljq.demo.springboot.easyexcel.common.exception.ServiceException;
import com.ljq.demo.springboot.easyexcel.common.listener.CommonImportListener;
import com.ljq.demo.springboot.easyexcel.common.util.EasyExcelUtil;
import com.ljq.demo.springboot.easyexcel.common.util.ValidateUtil;
import com.ljq.demo.springboot.easyexcel.model.request.StudentExportRequest;
import com.ljq.demo.springboot.easyexcel.model.response.CommonImportResponse;
import com.ljq.demo.springboot.easyexcel.model.vo.StudentExportVo;
import com.ljq.demo.springboot.easyexcel.model.vo.StudentImportVo;
import com.ljq.demo.springboot.easyexcel.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @Description: 学生业务接口实现类
 * @Author: junqiang.lu
 * @Date: 2024/6/20
 */
@Slf4j
@Service
public class StudentServiceImpl implements StudentService {


    /**
     * 导入学生信息
     *
     * @param file
     * @return
     */
    @Override
    public CommonImportResponse importStudent(MultipartFile file) {
        CommonImportResponse response = new CommonImportResponse();
        List<String> failReasonList = new ArrayList<>();
        try {
            // 解析 Excel
            List<StudentImportVo> studentList = EasyExcelUtil.readExcel(file.getInputStream(), StudentImportVo.class,
                    new CommonImportListener<>());
            if (CollUtil.isEmpty(studentList)) {
                response.setFailReasonList(Collections.singletonList("导入失败,Excel 文档数据为空"));
                return response;
            }
            // 基本参数校验
            studentList.forEach(student -> failReasonList.addAll(ValidateUtil.validExcel(student)));
            if (CollUtil.isNotEmpty(failReasonList)) {
                response.setFailReasonList(failReasonList);
                return response;
            }
            // TODO 业务参数校验
            studentList.forEach(student -> {
                log.info("业务参数校验学生信息: {}", student);

            });

            // 校验通过的对象数
            response.setSuccessCount(studentList.size());

            // TODO 保存到数据库


        } catch (Exception e) {
            log.error("Excel 解析失败",e);
            if (e instanceof ServiceException) {
                response.setFailReasonList(Collections.singletonList(e.getMessage()));
                return response;
            }
            response.setFailReasonList(Collections.singletonList("导入失败,无法解析 Excel 文档"));
        }
        return response;
    }

    /**
     * 导出学生信息
     *
     * @param request
     * @return
     */
    @Override
    public List<StudentExportVo> exportStudent(StudentExportRequest request) {
        log.info("导出学生信息: {}", request);

        // TODO 数据库查询

        List<StudentExportVo> studentList = new ArrayList<>();
        if (request.getCount() > 0) {
            for (int i = 0; i < request.getCount(); i++) {
                StudentExportVo student = new StudentExportVo();
                student.setStudentName("学生" + i);
                student.setStudentNo("S" + i);
                student.setBirthday(new Date());
                student.setGrade(RandomUtil.randomInt(1,13));
                student.setSex(RandomUtil.randomEle(Arrays.asList("男", "女")));
                student.setInSchoolFlag(RandomUtil.randomBoolean());
                student.setRemark("备注" + i);
                studentList.add(student);
            }
        }
        return studentList;
    }


}
