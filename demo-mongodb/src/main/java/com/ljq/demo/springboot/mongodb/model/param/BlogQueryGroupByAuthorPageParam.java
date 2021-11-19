package com.ljq.demo.springboot.mongodb.model.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Description: 按照作者分组分页查询博客数据
 * @Author: junqiang.lu
 * @Date: 2021/11/17
 */
@Data
@ApiModel(value = "按照作者分组分页查询博客数据", description = "按照作者分组分页查询博客数据")
public class BlogQueryGroupByAuthorPageParam extends BasePageParam {

    private static final long serialVersionUID = -2631557772669845272L;

}
