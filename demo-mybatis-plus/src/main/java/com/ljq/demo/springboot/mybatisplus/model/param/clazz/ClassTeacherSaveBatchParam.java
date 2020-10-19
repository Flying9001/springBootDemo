package com.ljq.demo.springboot.mybatisplus.model.param.clazz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Description: 班级教师批量保存
 * @Author: junqiang.lu
 * @Date: 2020/10/9
 */
@Data
@ApiModel(value = "班级教师批量保存", description = "班级教师批量保存")
public class ClassTeacherSaveBatchParam implements Serializable {

    private static final long serialVersionUID = 6663273757799942447L;

    /**
     * 班级 id
     **/
    @NotNull(message = "请选择班级")
    @ApiModelProperty(value = "班级 id", name = "classId", required = true, example = "1")
    private Long classId;
    /**
     * 教师 id 数组
     */
    @NotEmpty(message = "至少选择一名教师")
    @Size(max = 10, message = "一次最多选择 10 名教师")
    @ApiModelProperty(value = "教师 id 数组", name = "teacherIds", required = true)
    private Long[] teacherIds;

}
