package com.ljq.demo.springboot.easyexcel.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 导出学生信息请求参数
 * @Author: junqiang.lu
 * @Date: 2024/6/21
 */
@Data
public class StudentExportRequest implements Serializable {

    private static final long serialVersionUID = -3577684832129705636L;

    /**
     * 导出学生数量
     */
    private Long count;


}
