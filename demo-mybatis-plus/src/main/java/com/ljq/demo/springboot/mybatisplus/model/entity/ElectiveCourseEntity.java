package com.ljq.demo.springboot.mybatisplus.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 选修课
 * @Author: junqiang.lu
 * @Date: 2021/8/17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectiveCourseEntity implements Serializable {

    private static final long serialVersionUID = -9214802138019634356L;

    /**
     * 学生 id
     */
    private Long stuId;
    /**
     * 课程名称
     */
    private List<String> courseNames;

}
