package com.ljq.demo.springboot.mongodb.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.ljq.demo.springboot.mongodb.common.api.ApiResult;
import com.ljq.demo.springboot.mongodb.common.api.ResponseCode;
import com.ljq.demo.springboot.mongodb.model.entity.BlogEntity;
import com.ljq.demo.springboot.mongodb.model.param.*;
import com.ljq.demo.springboot.mongodb.service.BlogService;
import com.mongodb.client.result.DeleteResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Description: 博客业务实现类
 * @Author: junqiang.lu
 * @Date: 2021/11/13
 */
@Slf4j
@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 新增单条博客
     *
     * @param addParam
     * @return
     */
    @Override
    public ApiResult add(BlogAddParam addParam) {
        BlogEntity blogEntity = new BlogEntity();
        BeanUtil.copyProperties(addParam, blogEntity, CopyOptions.create().ignoreError().ignoreNullValue());
        mongoTemplate.save(blogEntity);
        return ApiResult.success(blogEntity);
    }

    /**
     * 查询单条博客
     *
     * @param queryOneParam
     * @return
     */
    @Override
    public ApiResult queryOne(BlogQueryOneParam queryOneParam) {
        Query query = Query.query(Criteria.where("id").is(queryOneParam.getId()));
        return ApiResult.success(mongoTemplate.findOne(query, BlogEntity.class));
    }

    /**
     * 分页查询博客
     *
     * @param queryPageParam
     * @return
     */
    @Override
    public ApiResult queryPage(BlogQueryPageParam queryPageParam) {
        // 查询条件
        Criteria criteria = new Criteria();
        if (StrUtil.isNotBlank(queryPageParam.getId())) {
            criteria = criteria.and("id").is(queryPageParam.getId());
        }
        if (StrUtil.isNotBlank(queryPageParam.getTitle())) {
            Pattern titlePattern = Pattern.compile("^.*" + queryPageParam.getTitle() + ".*$",
                    Pattern.CASE_INSENSITIVE);
            criteria.and("title").regex(titlePattern);
        }
        if (StrUtil.isNotBlank(queryPageParam.getAuthor())) {
            criteria.and("author").is(queryPageParam.getAuthor());
        }
        if (StrUtil.isNotBlank(queryPageParam.getContent())) {
            Pattern contentPattern = Pattern.compile("^.*" + queryPageParam.getContent() + ".*$",
                    Pattern.CASE_INSENSITIVE);
            criteria.and("content").regex(contentPattern);
        }
        Query query = Query.query(criteria);
        // 分页查询
        long count = mongoTemplate.count(query, BlogEntity.class);
        Pageable pageParam = PageRequest.of(queryPageParam.getCurrentPage()-1, queryPageParam.getPageSize(),
                Sort.by(Sort.Direction.DESC, "updateTime"));
        List<BlogEntity> list = mongoTemplate.find(query.with(pageParam), BlogEntity.class);
        Page<BlogEntity> pageResult = PageableExecutionUtils.getPage(list, pageParam, () -> count);
        return ApiResult.success(pageResult);
    }

    /**
     * 更新单条博客
     *
     * @param updateParam
     * @return
     */
    @Override
    public ApiResult update(BlogUpdateParam updateParam) {
        BlogEntity blogEntity = mongoTemplate.findById(updateParam.getId(), BlogEntity.class);
        if (Objects.isNull(blogEntity)) {
            return ApiResult.failure(ResponseCode.BLOG_NOT_EXIST);
        }
        BeanUtil.copyProperties(updateParam, blogEntity, CopyOptions.create().ignoreError().ignoreNullValue());
        mongoTemplate.save(blogEntity);
        return ApiResult.success(blogEntity);
    }

    /**
     * 删除单条博客
     *
     * @param deleteOneParam
     * @return
     */
    @Override
    public ApiResult deleteOne(BlogDeleteOneParam deleteOneParam) {
        DeleteResult result = mongoTemplate.remove(Query.query(Criteria.where("id").is(deleteOneParam.getId())),
                BlogEntity.class);
        if (result.getDeletedCount() < 1) {
            return ApiResult.failure(ResponseCode.BLOG_NOT_EXIST);
        }
        return ApiResult.success();
    }

    /**
     * 批量删除博客
     *
     * @param deleteBatchParam
     * @return
     */
    @Override
    public ApiResult deleteBatch(BlogDeleteBatchParam deleteBatchParam) {
        List<String> idList = deleteBatchParam.getIdList().stream()
                .map(BlogDeleteOneParam::getId).collect(Collectors.toList());
        DeleteResult result = mongoTemplate.remove(Query.query(Criteria.where("id").in(idList)), BlogEntity.class);
        return ApiResult.success(result.getDeletedCount());
    }
}
