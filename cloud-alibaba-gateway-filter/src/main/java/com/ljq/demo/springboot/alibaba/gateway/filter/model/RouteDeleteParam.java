package com.ljq.demo.springboot.alibaba.gateway.filter.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description: 删除路由
 * @Author: junqiang.lu
 * @Date: 2021/10/22
 */
@Data
public class RouteDeleteParam implements Serializable {

    private static final long serialVersionUID = -2214111091023615696L;

    /**
     * 路由 ID
     */
    @NotBlank(message = "路由ID不能为空")
    private String routeId;

}
