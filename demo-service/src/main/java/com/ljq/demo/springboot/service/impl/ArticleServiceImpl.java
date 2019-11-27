package com.ljq.demo.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljq.demo.springboot.common.api.ApiResult;
import com.ljq.demo.springboot.common.page.QueryUtil;
import com.ljq.demo.springboot.dao.article.ArticleDao;
import com.ljq.demo.springboot.entity.ArticleEntity;
import com.ljq.demo.springboot.service.ArticleService;
import com.ljq.demo.springboot.vo.article.ArticleListParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文章表业务层具体实现类
 *
 * @author junqiang.lu
 * @date 2019-11-25 14:01:38
 */
@Service("articleService")
@Transactional(rollbackFor = {Exception.class})
@Slf4j
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;

	/**
	 * 查询列表
	 *
	 * @param articleListParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public ApiResult list(ArticleListParam articleListParam) throws Exception {
		long start = System.currentTimeMillis();
		QueryUtil queryMap = new QueryUtil(BeanUtil.beanToMap(articleListParam, false, true));
		PageInfo<ArticleEntity> pageInfo = PageHelper.startPage(articleListParam.getCurrPage(), articleListParam.getPageLimit())
				.setOrderBy(articleListParam.getProperties() + " " + articleListParam.getDirection())
				.doSelectPageInfo(() -> articleDao.queryListPage(queryMap));
		long end = System.currentTimeMillis();
		log.info("查询耗时: {}", (end - start));
		return ApiResult.success(pageInfo);
	}

	
}
