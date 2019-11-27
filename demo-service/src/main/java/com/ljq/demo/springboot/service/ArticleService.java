package com.ljq.demo.springboot.service;

import com.ljq.demo.springboot.common.api.ApiResult;
import com.ljq.demo.springboot.vo.article.ArticleListParam;

/**
 * 文章表业务层接口
 * 
 * @author junqiang.lu
 * @date 2019-11-25 14:01:38
 */
public interface ArticleService {

	/**
     * 查询列表
     *
     * @param articleListParam
     * @return
     * @throws Exception
     */
	ApiResult list(ArticleListParam articleListParam) throws Exception;


}
