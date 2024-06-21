package com.ljq.demo.springboot.easyexcel.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.ljq.demo.springboot.easyexcel.common.util.EasyExcelUtil;
import com.ljq.demo.springboot.easyexcel.model.request.StudentExportRequest;
import com.ljq.demo.springboot.easyexcel.model.response.CommonImportResponse;
import com.ljq.demo.springboot.easyexcel.model.vo.StudentExportVo;
import com.ljq.demo.springboot.easyexcel.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Description: 学生控制层
 * @Author: junqiang.lu
 * @Date: 2024/6/20
 */
@RestController
@RequestMapping(value = "/api/easyexcel/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    /**
     * 导入学生信息
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/import")
    public ResponseEntity<CommonImportResponse> importStudent(MultipartFile file){
        return ResponseEntity.ok(studentService.importStudent(file));
    }

    /**
     * 导出学生信息
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping(value = "/export")
    public void exportStudent(StudentExportRequest request, HttpServletResponse response) throws IOException {
        List<StudentExportVo> studentList = studentService.exportStudent(request);
        EasyExcelUtil.writeExcel(response, DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN)
                + "学生信息.xlsx", "学生信息", StudentExportVo.class, studentList);
    }


}
