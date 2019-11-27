package com.ljq.demo.springboot.vo.article;

import com.ljq.demo.springboot.BasePageBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 文章表参数接收类
 *
 * @author junqiang.lu
 * @date 2019-11-25 14:01:38
 */
@Data
@ApiModel(value = "文章表查询列表", description = "文章表查询列表")
public class ArticleListParam extends BasePageBean {

    private static final long serialVersionUID = 1L;

    /**
     * 文章标题
     */
    @Length(max = 20, message = "文章标题不能超过 20 字")
    @ApiModelProperty(value = "文章标题", name = "title")
    private String title;
    /**
     * 文章标签
     */
    @Length(max = 20, message = "文章标签不能超过 20 字")
    @ApiModelProperty(value = "文章标签", name = "articleTag")
    private String articleTag;




}
