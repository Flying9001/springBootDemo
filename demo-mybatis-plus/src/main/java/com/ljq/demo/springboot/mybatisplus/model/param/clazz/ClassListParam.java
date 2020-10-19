package com.ljq.demo.springboot.mybatisplus.model.param.clazz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 班级参数接收类
 *
 * @author junqiang.lu
 * @date 2020-10-09 17:27:32
 */
@Data
@ApiModel(value = "班级查询列表", description = "班级查询列表")
public class ClassListParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 班级名称
     * */
    @Length(max = 30, message = "班级名称需要控制在 30 字符以内")
    @ApiModelProperty(value = "班级名称", name = "name")
    private String name;
    /**
     * 当前页
     */
    @NotNull(message = "当前页不能为空")
    @Min(value = 1, message = "当前页至少为 1")
    @ApiModelProperty(value = "当前页,不能为空,至少为 1", name = "currPage", required = true, example = "1")
    private Integer currentPage;
    /**
     * 每页显示条数
     */
    @NotNull(message = "每页显示条数不能为空")
    @Min(value = 3, message = "每页至少展示 3 条结果")
    @Max(value = 100, message = "每页最多展示 100 条结果")
    @ApiModelProperty(value = "每页显示条数,每页至少展示 3 条结果,最多为 100 条", required = true, example = "5")
    private Integer pageSize;
    /**
     * 排序依据,如依据 "id" 排序
     */
    @NotNull(message = "排序依据不能为空")
    @Pattern(regexp = "^[A-Za-z0-9_]{1,30}$", message = "排序依据需要控制在 1-30 个字符以内")
    @ApiModelProperty(value = "排序字段,如依据 'id' 排序,不能为空,字数需要控制在 1-30 个字符以内", required = true, example = "id")
    private String properties;
    /**
     * 排序规则,升序:asc;降序:desc
     */
    @NotNull(message = "排序规则不能为空")
    @Pattern(regexp = "^[a-zA-Z]{1,5}$", message = "排序规则选择错误,升序:asc;降序:desc")
    @ApiModelProperty(value = "排序规则,升序:asc;降序:desc", required = true, example = "desc")
    private String direction;



}
