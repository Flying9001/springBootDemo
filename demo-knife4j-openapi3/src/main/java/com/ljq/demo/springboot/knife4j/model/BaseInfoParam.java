package com.ljq.demo.springboot.knife4j.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description: 查询详情基础参数类
 * @Author: junqiang.lu
 * @Date: 2021/4/12
 */
@Data
public class BaseInfoParam implements Serializable {

    private static final long serialVersionUID = -4477890894587488060L;

    @NotNull(message = "请选择需要操作的对象")
    @Min(value = 1, message = "请求参数不合理")
    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

}
