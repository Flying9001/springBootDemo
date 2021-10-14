package com.ljq.demo.springboot.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @Description: 创建延时订单
 * @Author: junqiang.lu
 * @Date: 2021/10/14
 */
@Data
@ApiModel(value = "创建延时订单", description = "创建延时订单")
public class OrderDelayCreateParam implements Serializable {

    private static final long serialVersionUID = 3692411340443934479L;

    /**
     * 订单编号
     */
    @Pattern(regexp = "^[a-zA-Z0-9]{5,64}$", message = "订单编号格式错误")
    @ApiModelProperty(value = "订单编号", name = "orderNo", required = true)
    private String orderNo;

}
