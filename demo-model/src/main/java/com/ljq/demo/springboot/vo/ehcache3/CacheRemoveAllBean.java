package com.ljq.demo.springboot.vo.ehcache3;

import com.ljq.demo.springboot.BaseBean;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Description: CacheRemoveAll 注解测试 参数接收 bean
 * @Author: junqiang.lu
 * @Date: 2019/3/16
 */
@Data
public class CacheRemoveAllBean extends BaseBean {

    private static final long serialVersionUID = 7311007086863087333L;

    /**
     * 当前页
     */
    @NotNull(message = "当前页不能为空")
    @Min(value = 1, message = "当前页至少为 1")
    private Integer currPage;

    /**
     * 每页显示条数
     */
    @NotNull(message = "每页显示条数不能为空")
    @Min(value = 5, message = "每页至少展示 5 条结果")
    private Integer pageLimit;

    /**
     * 排序依据,如依据 "id" 排序
     */
    @NotNull(message = "排序依据不能为空")
    @Pattern(regexp = "^[\\s\\S]{1,30}$", message = "排序依据需要控制在 1-30 个字符以内")
    private String sidx;

    /**
     * 排序规则,升序:asc;降序:desc
     */
    @NotNull(message = "排序规则不能为空")
    @Pattern(regexp = "^[\\s\\S]{1,10}$", message = "排序规则需要控制在 1-10 个字符以内")
    private String order;

}
