package com.ljq.demo.springboot.vo.ehcache3;

import com.ljq.demo.springboot.BaseBean;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Description: 不使用缓存 参数接收bean
 * @Author: junqiang.lu
 * @Date: 2019/3/16
 */
@Data
public class NoCacheBean extends BaseBean {


    private static final long serialVersionUID = 8568564661258311962L;

    /**
     * 用户 id
     */
    @NotNull(message = "用户 id 不能为空")
    @Min(value = 1, message = "用户 id 至少为 1")
    private Long id;

}
