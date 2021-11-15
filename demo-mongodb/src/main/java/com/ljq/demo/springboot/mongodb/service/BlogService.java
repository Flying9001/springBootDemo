package com.ljq.demo.springboot.mongodb.service;

import com.ljq.demo.springboot.mongodb.common.api.ApiResult;
import com.ljq.demo.springboot.mongodb.model.param.*;

/**
 * @Description: 博客业务接口
 * @Author: junqiang.lu
 * @Date: 2021/11/13
 */
public interface BlogService {

    /**
     * 新增单条博客
     *
     * @param addParam
     * @return
     */
    ApiResult add(BlogAddParam addParam);

    /**
     * 批量新增博客
     *
     * @param addBatchParam
     * @return
     */
    ApiResult addBatch(BlogAddBatchParam addBatchParam);

    /**
     * 查询单条博客
     *
     * @param queryOneParam
     * @return
     */
    ApiResult queryOne(BlogQueryOneParam queryOneParam);

    /**
     * 分页查询博客
     *
     * @param queryPageParam
     * @return
     */
    ApiResult queryPage(BlogQueryPageParam queryPageParam);

    /**
     * 查询博客阅读量
     *
     * @param readCountQueryParam
     * @return
     */
    ApiResult queryReadCount(BlogReadCountQueryParam readCountQueryParam);

    /**
     * 查询博客阅读量统计
     *
     * @param readCountSummaryParam
     * @return
     */
    ApiResult summaryReadCount(BlogReadCountSummaryParam readCountSummaryParam);

    /**
     * 更新单条博客
     *
     * @param updateParam
     * @return
     */
    ApiResult update(BlogUpdateParam updateParam);

    /**
     * 批量更新博客
     *
     * @param updateBatchParam
     * @return
     */
    ApiResult updateBatch(BlogUpdateBatchParam updateBatchParam);

    /**
     * 删除单条博客
     *
     * @param deleteOneParam
     * @return
     */
    ApiResult deleteOne(BlogDeleteOneParam deleteOneParam);

    /**
     * 批量删除博客
     *
     * @param deleteBatchParam
     * @return
     */
    ApiResult deleteBatch(BlogDeleteBatchParam deleteBatchParam);


}
