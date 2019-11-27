package com.ljq.demo.springboot.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 文章表实体类
 *
 * @author junqiang.lu
 * @date 2019-11-25 14:01:38
 */
@Data
@ApiModel(value = "文章表", description = "文章表")
public class ArticleEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

    /**
	 * 文章 id,主键
	 * */
	@ApiModelProperty(value = "文章 id,主键", name = "id")
	private Long id;
    /**
	 * 文章标题
	 * */
	@ApiModelProperty(value = "文章标题", name = "title")
	private String title;
    /**
	 * 文章内容
	 * */
	@ApiModelProperty(value = "文章内容", name = "content")
	private String content;
	/**
	 * 文章标签列表
	 */
	@ApiModelProperty(value = "文章标签列表", name = "tagList")
	List<ArticleTagEntity> tagList;



}
