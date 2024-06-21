package com.ljq.demo.springboot.easyexcel.model.vo;

import cn.hutool.core.date.DatePattern;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description: 学生导入对象
 * @Author: junqiang.lu
 * @Date: 2024/6/20
 */
@Data
@ExcelIgnoreUnannotated
public class StudentImportVo extends BaseImportVo {

    private static final long serialVersionUID = -1342688939679766826L;

    /**
     * 学生姓名
     */
    @ExcelProperty(index = 0)
    @NotBlank(message = "学生姓名不能为空")
    private String studentName;

    /**
     * 学号
     */
    @ExcelProperty(index = 1)
    @NotBlank(message = "学号不能为空")
    private String studentNo;

    /**
     * 生日
     */
    @ExcelProperty(index = 2)
    @DateTimeFormat(value = DatePattern.NORM_DATE_PATTERN)
    @NotNull(message = "生日不能为空")
    private Date birthday;

    /**
     * 年级
     */
    @ExcelProperty(index = 3)
    @NotNull(message = "年级不能为空")
    @Min(value = 1, message = "年级不能小于1")
    @Max(value = 12, message = "年级不能大于12")
    private Integer grade;

    /**
     * 性别
     */
    @ExcelProperty(index = 4)
    @NotBlank(message = "性别不能为空")
    private String sex;

    /**
     * 是否住校,true:住校,false:非住校
     */
    @ExcelProperty(index = 5)
    private Boolean inSchoolFlag;

    /**
     * 备注
     */
    private String remark;


}
