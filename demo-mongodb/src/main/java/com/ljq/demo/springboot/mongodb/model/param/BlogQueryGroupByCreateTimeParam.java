package com.ljq.demo.springboot.mongodb.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description: 按照创建时间分组查询博客数据
 * @Author: junqiang.lu
 * @Date: 2021/11/17
 */
@Data
@ApiModel(value = "按照创建时间分组查询博客数据", description = "按照创建时间分组查询博客数据")
public class BlogQueryGroupByCreateTimeParam implements Serializable {

    private static final long serialVersionUID = 452328718263785261L;

    /**
     * 最小创建时间(时间戳,精确到毫秒)
     */
    @NotNull(message = "最小创建时间不能为空")
    @ApiModelProperty(value = "最小创建时间(时间戳,精确到毫秒)", name = "minCreateTime", required = true, example = "0")
    private Long minCreateTime;
    /**
     * 最大创建时间(时间戳,精确到毫秒)
     */
    @NotNull(message = "最大创建时间不能为空")
    @ApiModelProperty(value = "最大创建时间(时间戳,精确到毫秒)", name = "maxCreateTime", required = true, example = "0")
    private Long maxCreateTime;
    /**
     * 日期类型,1-按日分组,2-按月分组
     */
    @NotNull(message = "请选择需要统计的日期类型")
    @Min(value = 1, message = "日期类型设置错误")
    @Max(value = 2, message = "日期类型设置错误")
    @ApiModelProperty(value = "日期类型,1-按日分组,2-按月分组", name = "dateType", required = true, example = "0")
    private Integer dateType;

}
