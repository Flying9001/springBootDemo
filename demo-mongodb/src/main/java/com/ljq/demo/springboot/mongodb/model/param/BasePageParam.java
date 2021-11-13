package com.ljq.demo.springboot.mongodb.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description: 分页查询基础参数
 * @Author: junqiang.lu
 * @Date: 2021/11/13
 */
@Data
@ApiModel(value = "分页查询基础参数", description = "分页查询基础参数")
public class BasePageParam implements Serializable {

    private static final long serialVersionUID = -1676313147351509759L;

    /**
     * 当前页
     */
    @NotNull(message = "当前页不能为空")
    @Min(value = 1, message = "当前页至少为 1")
    @ApiModelProperty(value = "当前页,不能为空,至少为 1", name = "currentPage", required = true, example = "1")
    private Integer currentPage;
    /**
     * 每页显示条数
     */
    @NotNull(message = "每页显示条数不能为空")
    @Min(value = 3, message = "每页至少展示 3 条结果")
    @Max(value = 100, message = "每页最多展示 100 条结果")
    @ApiModelProperty(value = "每页显示条数,每页至少展示 3 条结果,最多为 100 条", name = "pageSize", required = true,
            example = "5")
    private Integer pageSize;


}
