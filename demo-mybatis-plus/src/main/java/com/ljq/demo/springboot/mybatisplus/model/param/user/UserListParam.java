package com.ljq.demo.springboot.mybatisplus.model.param.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户表参数接收类
 *
 * @author junqiang.lu
 * @date 2020-08-31 14:09:53
 */
@Data
@ApiModel(value = "用户表查询列表", description = "用户表查询列表")
public class UserListParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     * */
    @Length(max = 40, message = "用户名需要控制在 40 字符以内")
    @ApiModelProperty(value = "用户名", name = "userName")
    private String userName;
    /**
     * 当前页
     */
    @NotNull(message = "当前页不能为空")
    @Min(value = 1, message = "当前页至少为 1")
    @ApiModelProperty(value = "当前页,不能为空,至少为 1", name = "currentPage", required = true, example = "1")
    private Integer currentPage;
    /**
     * 每页显示条数
     */
    @NotNull(message = "每页显示条数不能为空")
    @Min(value = 3, message = "每页至少展示 3 条结果")
    @Max(value = 100, message = "每页最多展示 100 条结果")
    @ApiModelProperty(value = "每页显示条数,每页至少展示 3 条结果,最多为 100 条", name = "pageSize", required = true, example = "5")
    private Integer pageSize;
    /**
     * 排序规则,true:升序,false:降序(默认)
     */
    @ApiModelProperty(value = "排序规则,true:升序,false:降序(默认)", example = "desc")
    private Boolean ascFlag = false;





}
