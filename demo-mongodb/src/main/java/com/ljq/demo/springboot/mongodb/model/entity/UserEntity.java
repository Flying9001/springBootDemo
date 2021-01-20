package com.ljq.demo.springboot.mongodb.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Description: 用户信息
 * @Author: junqiang.lu
 * @Date: 2021/1/6
 */
@Data
@Document(value = "user")
@ApiModel(value = "用户信息", description = "用户信息")
public class UserEntity {

    /**
     * id
     */
    @Id
    @ApiModelProperty(value = "id", name = "id")
    private String id;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", name = "name")
    private String name;
    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄", name = "age")
    private Integer age;
    /**
     * 简介
     */
    @ApiModelProperty(value = "简介", name = "introduction")
    private String introduction;
}

