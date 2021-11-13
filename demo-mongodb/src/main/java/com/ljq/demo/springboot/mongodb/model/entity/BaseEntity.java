package com.ljq.demo.springboot.mongodb.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @Description: 基础实体类
 * @Author: junqiang.lu
 * @Date: 2021/9/24
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -3003658740476069858L;

    /**
     * id,主键
     */
    @Id
    @ApiModelProperty(value = "id,主键", name = "id")
    private String id;
    /**
     * 创建时间
     */
    @Field
    @CreatedDate
    @ApiModelProperty(value = "创建时间", name = "createTime")
    private Long createTime;
    /**
     * 修改时间
     */
    @Field
    @LastModifiedDate
    @ApiModelProperty(value = "修改时间", name = "updateTime")
    private Long updateTime;
}
