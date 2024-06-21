package com.ljq.demo.springboot.easyexcel.service;

import com.ljq.demo.springboot.easyexcel.model.request.StudentExportRequest;
import com.ljq.demo.springboot.easyexcel.model.response.CommonImportResponse;
import com.ljq.demo.springboot.easyexcel.model.vo.StudentExportVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description: 学生业务接口
 * @Author: junqiang.lu
 * @Date: 2024/6/20
 */
public interface StudentService {

    /**
     * 导入学生信息
     *
     * @param file
     * @return
     */
    CommonImportResponse importStudent(MultipartFile file);

    /**
     * 导出学生信息
     *
     * @param request
     * @return
     */
    List<StudentExportVo> exportStudent(StudentExportRequest request);


}
