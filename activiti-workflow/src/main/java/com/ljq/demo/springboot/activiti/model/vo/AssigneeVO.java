package com.ljq.demo.springboot.activiti.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description: 代理人对象
 * @Author: junqiang.lu
 * @Date: 2020/7/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "代理人对象")
public class AssigneeVO implements Serializable {

    private static final long serialVersionUID = 4350399845318007902L;

    /**
     * 用户 id
     */
    @ApiModelProperty(value = "用户 id", name = "userId")
    private String userId;
    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名", name = "userName")
    private String userName;


}
