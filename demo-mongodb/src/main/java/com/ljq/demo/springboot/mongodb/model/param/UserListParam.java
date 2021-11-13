package com.ljq.demo.springboot.mongodb.model.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

/**
 * 参数接收类
 *
 * @author junqiang.lu
 * @date 2021-01-06 20:03:33
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "查询列表", description = "查询列表")
public class UserListParam extends BasePageParam {

    private static final long serialVersionUID = 1L;


}
