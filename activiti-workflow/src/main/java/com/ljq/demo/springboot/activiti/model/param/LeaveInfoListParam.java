package com.ljq.demo.springboot.activiti.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 请假信息参数接收类
 *
 * @author junqiang.lu
 * @date 2020-07-09 16:25:39
 */
@Data
@ApiModel(value = "请假信息查询列表", description = "请假信息查询列表")
public class LeaveInfoListParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页,不能为空,至少为 1", name = "currentPage", example = "1")
    private Integer currentPage = 1;
    /**
     * 每页显示条数
     */
    @ApiModelProperty(value = "每页显示条数,每页至少展示 3 条结果,最多为 100 条", name = "pageSize", example = "5")
    private Integer pageSize = 5;
    /**
     * 排序依据,如依据 "id" 排序
     */
    @Pattern(regexp = "^[\\s\\S]{1,30}$", message = "排序依据需要控制在 1-30 个字符以内")
    @ApiModelProperty(value = "排序字段,如依据 'id' 排序,不能为空,字数需要控制在 1-30 个字符以内", name = "properties", example = "id")
    private String properties;
    /**
     * 排序规则,升序:asc;降序:desc
     */
    @Pattern(regexp = "^[\\s\\S]{1,10}$", message = "排序规则需要控制在 1-10 个字符以内")
    @ApiModelProperty(value = "排序规则,升序:asc;降序:desc", name = "direction", example = "desc")
    private String direction;




}
