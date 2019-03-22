package com.ljq.demo.springboot.vo.ehcache3;

import com.ljq.demo.springboot.BaseBean;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Description: CachePut 注解测试 参数接收 bean
 * @Author: junqiang.lu
 * @Date: 2019/3/16
 */
@Data
public class CachePutBean extends BaseBean {

    private static final long serialVersionUID = 5556099626135790988L;

    /**
     * 用户 id
     */
    @NotNull(message = "用户 id 不能为空")
    @Min(value = 1, message = "用户 id 至少为 1")
    private Long id;


}
