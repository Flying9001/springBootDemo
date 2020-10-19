package com.ljq.demo.springboot.mybatisplus.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 班级实体类
 *
 * @author junqiang.lu
 * @date 2020-10-09 17:27:32
 */
@Data
@TableName(value = "class", resultMap = "classMap")
@ApiModel(value = "班级", description = "班级")
public class ClassEntity implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * id
     **/
    @TableId(value = "ID", type = IdType.AUTO)
    @ApiModelProperty(value = "id", name = "id")
    private Long id;
    /**
     * 班主任ID
     **/
    @TableField(value = "HEAD_TEACHER_ID")
    @ApiModelProperty(value = "班主任ID", name = "headTeacherId")
    private Long headTeacherId;
    /**
     * 班主任信息
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "班主任信息", name = "headTeacher")
    private TeacherEntity headTeacher;
    /**
     * 班级名称
     **/
    @TableField(value = "NAME")
    @ApiModelProperty(value = "班级名称", name = "name")
    private String name;
    /**
     * 班级教室列表
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "班级教室列表", name = "teacherList")
    private List<TeacherEntity> teacherList;


}
