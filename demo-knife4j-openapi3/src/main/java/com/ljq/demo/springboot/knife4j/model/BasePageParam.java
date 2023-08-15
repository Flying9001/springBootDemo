package com.ljq.demo.springboot.knife4j.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description: 分页查询基础参数类
 * @Author: junqiang.lu
 * @Date: 2021/3/24
 */
@Data
public class BasePageParam implements Serializable {

    private static final long serialVersionUID = -2733828616085361191L;

    @NotNull(message = "当前页不能为空")
    @Min(value = 1, message = "当前页至少为 1")
    @Schema(description = "当前页,不能为空,至少为 1", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer currentPage;

    @NotNull(message = "每页显示条数不能为空")
    @Min(value = 3, message = "每页至少展示 3 条结果")
    @Max(value = 100, message = "每页最多展示 100 条结果")
    @Schema(description = "每页显示条数,每页至少展示 3 条结果,最多为 100 条", requiredMode = Schema.RequiredMode.REQUIRED,
            example = "5")
    private Integer pageSize;

}
