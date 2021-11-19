package com.ljq.demo.springboot.mongodb.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description: 按照创建时间自定义区间分组查询博客数据
 * @Author: junqiang.lu
 * @Date: 2021/11/18
 */
@Data
@ApiModel(value = "按照创建时间自定义区间分组查询博客数据", description = "按照创建时间自定义区间分组查询博客数据")
public class BlogQueryGroupByCreateTimeDiyParam implements Serializable {

    private static final long serialVersionUID = -8200304840074072884L;

    /**
     * 最小创建时间
     */
    @NotNull(message = "最小创建时间不能为空")
    @ApiModelProperty(value = "最小创建时间", name = "minCreateTime", required = true, example = "0")
    private Long minCreateTime;
    /**
     * 最大创建时间
     */
    @NotNull(message = "最大创建时间不能为空")
    @ApiModelProperty(value = "最大创建时间", name = "maxCreateTime", required = true, example = "0")
    private Long maxCreateTime;

}
