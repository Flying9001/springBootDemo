package com.ljq.demo.springboot.mongodb.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description: 按照客户端时间分组查询博客数据
 * @Author: junqiang.lu
 * @Date: 2021/11/17
 */
@Data
@ApiModel(value = "按照客户端时间分组查询博客数据", description = "按照客户端时间分组查询博客数据")
public class BlogQueryGroupByClientTimestampParam implements Serializable {

    private static final long serialVersionUID = -8791531554910496488L;

    /**
     * 客户端时间戳最小值(精确到秒)
     */
    @NotNull(message = "客户端时间戳最小值不能为空")
    @ApiModelProperty(value = "客户端时间戳最小值(精确到秒)", name = "minClientTimestamp", required = true, example = "0")
    private Integer minClientTimestamp;
    /**
     * 客户端时间戳最大值(精确到秒)
     */
    @NotNull(message = "客户端时间戳最大值不能为空")
    @ApiModelProperty(value = "客户端时间戳最大值(精确到秒)", name = "maxClientTimestamp", required = true, example = "0")
    private Integer maxClientTimestamp;
    /**
     * 日期类型,1-按日分组,2-按月分组
     */
    @NotNull(message = "请选择需要统计的日期类型")
    @Min(value = 1, message = "日期类型设置错误")
    @Max(value = 2, message = "日期类型设置错误")
    @ApiModelProperty(value = "日期类型,1-按日分组,2-按月分组", name = "dateType", required = true, example = "0")
    private Integer dateType;


}
