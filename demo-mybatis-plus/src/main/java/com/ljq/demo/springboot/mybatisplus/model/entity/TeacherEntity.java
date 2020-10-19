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
 * 教师实体类
 *
 * @author junqiang.lu
 * @date 2020-10-09 17:27:32
 */
@Data
@TableName(value = "teacher", resultMap = "teacherMap")
@ApiModel(value = "教师", description = "教师")
public class TeacherEntity implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * id
     **/
    @TableId(value = "ID", type = IdType.AUTO)
    @ApiModelProperty(value = "id", name = "id")
    private Long id;
    /**
     * 姓名
     **/
    @TableField(value = "NAME")
    @ApiModelProperty(value = "姓名", name = "name")
    private String name;

}
