package com.ljq.demo.springboot.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 文章-标签关联表实体类
 *
 * @author junqiang.lu
 * @date 2019-11-25 14:01:38
 */
@Data
@ApiModel(value = "文章-标签关联表", description = "文章-标签关联表")
public class ArticleToTagEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

    /**
	 * 文章-标签中间表
	 * */
	@ApiModelProperty(value = "文章-标签中间表", name = "id")
	private Long id;
    /**
	 * 文章 id
	 * */
	@ApiModelProperty(value = "文章 id", name = "articleId")
	private Long articleId;
    /**
	 * 标签 id
	 * */
	@ApiModelProperty(value = "标签 id", name = "tagId")
	private Long tagId;

}
