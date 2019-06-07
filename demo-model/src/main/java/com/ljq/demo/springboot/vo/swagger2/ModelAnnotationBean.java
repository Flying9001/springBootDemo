package com.ljq.demo.springboot.vo.swagger2;

import com.ljq.demo.springboot.BasePageBean;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Description: Swagger2 注解示例 bean
 * @Author: junqiang.lu
 * @Date: 2019/3/23
 */
@Data
@ApiModel(value = "Swagger2 @ApiModel 注解",description = "Swagger 2 注解接收参数")
public class ModelAnnotationBean extends BasePageBean {

    private static final long serialVersionUID = -3891566801822635611L;

}
