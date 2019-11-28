package com.ljq.demo.springboot.dao.article;

import com.ljq.demo.springboot.dao.BaseDao;
import com.ljq.demo.springboot.entity.ArticleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 文章表
 * 
 * @author junqiang.lu
 * @date 2019-11-25 14:01:38
 */
@Repository
public interface ArticleDao extends BaseDao<ArticleEntity> {

    /**
     * 查询列表
     *
     * @param queryMap
     * @return
     */
    List<ArticleEntity> queryListPage(Map<String, Object> queryMap);

    /**
     * 查询列表-2
     *
     * @param queryMap
     * @return
     */
    List<ArticleEntity> queryListPage2(Map<String, Object> queryMap);

    /**
     * 统计查询数量-4
     *
     * @param queryMap
     * @return
     */
    int countComplex4(Map<String, Object> queryMap);

    /**
     * 查询列表-4
     *
     * @param queryMap
     * @return
     */
    List<ArticleEntity> queryListComplex4(Map<String, Object> queryMap);
	
}
