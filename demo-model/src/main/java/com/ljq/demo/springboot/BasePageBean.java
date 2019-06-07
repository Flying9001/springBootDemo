package com.ljq.demo.springboot;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Description: 基础分页参数接收类
 * @Author: junqiang.lu
 * @Date: 2019/6/7
 */
@Data
public class BasePageBean extends BaseBean {

    private static final long serialVersionUID = 3104595107099720875L;

    /**
     * 当前页
     */
    @NotNull(message = "当前页不能为空")
    @Min(value = 1, message = "当前页至少为 1")
    @ApiModelProperty(value = "当前页,不能为空,至少为 1", name = "currPage", required = true, example = "1")
    private Integer currPage;

    /**
     * 每页显示条数
     */
    @NotNull(message = "每页显示条数不能为空")
    @Min(value = 3, message = "每页至少展示 3 条结果")
    @Max(value = 100, message = "每页最多展示 100 条结果")
    @ApiModelProperty(value = "每页显示条数,每页至少展示 3 条结果,最多为 100 条", required = true, example = "5")
    private Integer pageLimit;

    /**
     * 排序依据,如依据 "id" 排序
     */
    @NotNull(message = "排序依据不能为空")
    @Pattern(regexp = "^[\\s\\S]{1,30}$", message = "排序依据需要控制在 1-30 个字符以内")
    @ApiModelProperty(value = "排序字段,如依据 'id' 排序,不能为空,字数需要控制在 1-30 个字符以内", required = true, example = "id")
    private String properties;

    /**
     * 排序规则,升序:asc;降序:desc
     */
    @NotNull(message = "排序规则不能为空")
    @Pattern(regexp = "^[\\s\\S]{1,10}$", message = "排序规则需要控制在 1-10 个字符以内")
    @ApiModelProperty(value = "排序规则,升序:asc;降序:desc", required = true, example = "desc")
    private String direction;


}
