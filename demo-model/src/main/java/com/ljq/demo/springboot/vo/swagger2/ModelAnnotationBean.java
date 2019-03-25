package com.ljq.demo.springboot.vo.swagger2;

import com.ljq.demo.springboot.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Description: Swagger2 注解示例 bean
 * @Author: junqiang.lu
 * @Date: 2019/3/23
 */
@Data
@ApiModel(value = "Swagger2 @ApiModel 注解",description = "Swagger 2 注解接收参数")
public class ModelAnnotationBean extends BaseBean {

    private static final long serialVersionUID = -3891566801822635611L;

    // 当前页
    @NotNull(message = "当前页不能为空")
    @Min(value = 1, message = "当前页至少为 1")
    @ApiModelProperty(value = "当前页,不能为空,至少为 1", name = "currPage", required = true, example = "1")
    private Integer currPage;

    // 每页显示条数
    @NotNull(message = "每页显示条数不能为空")
    @Min(value = 5, message = "每页至少展示 5 条结果")
    @ApiModelProperty(value = "每页显示条数,不能为空,至少为 5", name = "pageLimit", required = true, example = "6")
    private Integer pageLimit;

     // 排序依据,如依据 "id" 排序
    @NotNull(message = "排序依据不能为空")
    @Pattern(regexp = "^[\\s\\S]{1,30}$", message = "排序依据需要控制在 1-30 个字符以内")
    @ApiModelProperty(value = "排序依据,不能为空,1-30 个字符", name = "sidx", required = true, example = "id")
    private String sidx;

    // 排序规则,升序: asc;降序: desc
    @NotNull(message = "排序规则不能为空")
    @Pattern(regexp = "^[\\s\\S]{1,10}$", message = "排序规则需要控制在 1-10 个字符以内")
    @ApiModelProperty(value = "排序规则,升序:asc;降序:desc", name = "order",required = true, example = "desc")
    private String order;
}
