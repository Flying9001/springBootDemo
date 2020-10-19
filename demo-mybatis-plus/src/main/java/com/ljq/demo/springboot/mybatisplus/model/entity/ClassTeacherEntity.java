package com.ljq.demo.springboot.mybatisplus.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 班级教师实体类
 *
 * @author junqiang.lu
 * @date 2020-10-14 17:03:57
 */
@Data
@TableName(value = "class_teacher", resultMap = "classTeacherMap")
@ApiModel(value = "班级教师", description = "班级教师")
public class ClassTeacherEntity implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * id
     **/
    @TableId(value = "ID", type = IdType.AUTO)
    @ApiModelProperty(value = "id", name = "id")
    private Long id;
    /**
     * 班级 id
     **/
    @TableField(value = "CLASS_ID")
    @ApiModelProperty(value = "班级 id", name = "classId")
    private Long classId;
    /**
     * 教师 id
     **/
    @TableField(value = "TEACHER_ID")
    @ApiModelProperty(value = "教师 id", name = "teacherId")
    private Long teacherId;

}
