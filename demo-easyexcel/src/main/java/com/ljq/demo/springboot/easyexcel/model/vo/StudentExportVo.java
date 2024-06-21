package com.ljq.demo.springboot.easyexcel.model.vo;

import cn.hutool.core.date.DatePattern;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 学生导出对象
 * @Author: junqiang.lu
 * @Date: 2024/6/21
 */
@Data
@ExcelIgnoreUnannotated
public class StudentExportVo implements Serializable {

    private static final long serialVersionUID = 414773537047578760L;

    /**
     * 学生姓名
     */
    @ExcelProperty(index = 0, value = "学生姓名")
    private String studentName;

    /**
     * 学号
     */
    @ExcelProperty(index = 1, value = "学号")
    private String studentNo;

    /**
     * 生日
     */
    @ExcelProperty(index = 2, value = "生日")
    @DateTimeFormat(value = DatePattern.NORM_DATE_PATTERN)
    private Date birthday;

    /**
     * 年级
     */
    @ExcelProperty(index = 3, value = "年级")
    private Integer grade;

    /**
     * 性别
     */
    @ExcelProperty(index = 4, value = "性别")
    private String sex;

    /**
     * 是否住校,true:住校,false:非住校
     */
    @ExcelProperty(index = 5, value = "是否住校")
    private Boolean inSchoolFlag;

    /**
     * 备注
     */
    private String remark;


}
