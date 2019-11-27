package com.ljq.demo.springboot.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 文章标签表实体类
 *
 * @author junqiang.lu
 * @date 2019-11-25 14:01:38
 */
@Data
@ApiModel(value = "文章标签表", description = "文章标签表")
public class ArticleTagEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

    /**
	 * 文章标签 id,主键
	 * */
	@ApiModelProperty(value = "文章标签 id,主键", name = "id")
	private Long id;
    /**
	 * 标签名称
	 * */
	@ApiModelProperty(value = "标签名称", name = "tagName")
	private String tagName;

}
