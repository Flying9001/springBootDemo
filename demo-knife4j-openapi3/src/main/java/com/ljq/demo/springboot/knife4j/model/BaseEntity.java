package com.ljq.demo.springboot.knife4j.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 基础实体类
 * @Author: junqiang.lu
 * @Date: 2021/3/22
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -4742455868884603111L;

    @JsonSerialize(using= ToStringSerializer.class)
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    @Schema(description = "id", example = "0")
    private Long id;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
