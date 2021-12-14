package com.ljq.springboot.elasticsearch.service;

import com.ljq.springboot.elasticsearch.common.api.ApiResult;
import com.ljq.springboot.elasticsearch.model.entity.BlogEntity;
import com.ljq.springboot.elasticsearch.model.param.*;
import org.springframework.data.domain.Page;

/**
 * @Description: 博客业务接口
 * @Author: junqiang.lu
 * @Date: 2021/12/11
 */
public interface BlogService {

    /**
     * 新增单条
     *
     * @param addParam
     * @return
     */
    ApiResult<BlogEntity> save(BlogAddParam addParam);

    /**
     * 查询单条
     *
     * @param queryOneParam
     * @return
     */
    ApiResult<BlogEntity> queryOne(BlogQueryOneParam queryOneParam);

    /**
     * 分页查询
     *
     * @param queryPageParam
     * @return
     */
    ApiResult<Page<BlogEntity>> queryPage(BlogQueryPageParam queryPageParam);

    /**
     * 更新单条
     *
     * @param updateParam
     * @return
     */
    ApiResult<BlogEntity> update(BlogUpdateParam updateParam);

    /**
     * 删除单条
     *
     * @param deleteOneParam
     * @return
     */
    ApiResult<Void> delete(BlogDeleteOneParam deleteOneParam);


}
