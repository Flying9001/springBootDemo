package com.ljq.demo.springboot.mongodb.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 博客自定义时间区间统计结果
 * @Author: junqiang.lu
 * @Date: 2021/11/18
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "博客自定义时间区间统计结果", description = "博客自定义时间区间统计结果")
public class BlogSummaryDiyVo extends BlogSummaryVo {

    private static final long serialVersionUID = 6688850910361408341L;

    /**
     * 年
     */
    @ApiModelProperty(value = "年", name = "year")
    private String year;
    /**
     * 月
     */
    @ApiModelProperty(value = "月", name = "month")
    private String month;
    /**
     * 小时
     */
    @ApiModelProperty(value = "小时", name = "day")
    private String hour;
    /**
     * 分钟
     */
    @ApiModelProperty(value = "分钟", name = "minute")
    private String minute;

}
