package com.ljq.demo.springboot.sharding.jdbc.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 基础实体类
 * @Author: junqiang.lu
 * @Date: 2022/4/1
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 2494094207811184245L;

    /**
     * id
     **/
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    /**
     * 创建时间
     **/
    @TableField(value = "CREATE_TIME")
    private Date createTime;
    /**
     * 更新时间
     **/
    @TableField(value = "UPDATE_TIME")
    private Date updateTime;


}
