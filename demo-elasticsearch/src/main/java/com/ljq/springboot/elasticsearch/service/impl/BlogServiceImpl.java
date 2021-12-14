package com.ljq.springboot.elasticsearch.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.ljq.springboot.elasticsearch.common.api.ApiMsgEnum;
import com.ljq.springboot.elasticsearch.common.api.ApiResult;
import com.ljq.springboot.elasticsearch.model.entity.BlogEntity;
import com.ljq.springboot.elasticsearch.model.param.*;
import com.ljq.springboot.elasticsearch.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description: 博客业务实现类
 * @Author: junqiang.lu
 * @Date: 2021/12/11
 */
@Slf4j
@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private ElasticsearchRestTemplate elasticTemplate;

    /**
     * 新增单条
     *
     * @param addParam
     * @return
     */
    @Override
    public ApiResult<BlogEntity> save(BlogAddParam addParam) {
        BlogEntity blogEntity = new BlogEntity();
        BeanUtil.copyProperties(addParam, blogEntity, CopyOptions.create().ignoreError().ignoreNullValue());
        elasticTemplate.save(blogEntity);
        return ApiResult.success(blogEntity);
    }

    /**
     * 查询单条
     *
     * @param queryOneParam
     * @return
     */
    @Override
    public ApiResult<BlogEntity> queryOne(BlogQueryOneParam queryOneParam) {
        return ApiResult.success(elasticTemplate.get(queryOneParam.getId(), BlogEntity.class));
    }

    /**
     * 分页查询
     *
     * @param queryPageParam
     * @return
     */
    @Override
    public ApiResult<Page<BlogEntity>> queryPage(BlogQueryPageParam queryPageParam) {
        Pageable pageable = PageRequest.of(queryPageParam.getCurrentPage() - 1, queryPageParam.getPageSize());
        // 构建查询条件
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder filter = QueryBuilders.boolQuery();
        // id-精确查询-idsQuery
        if (StrUtil.isNotBlank(queryPageParam.getId())) {
           filter.must(QueryBuilders.idsQuery().addIds(queryPageParam.getId()));
        }
        // 标题-模糊查询-matchQuery
        if (StrUtil.isNotBlank(queryPageParam.getTitle())) {
            filter.must(QueryBuilders.matchQuery("title",
                            queryPageParam.getTitle()).operator(Operator.OR).fuzziness(Fuzziness.AUTO));
        }
        // 作者-精确查询-queryStringQuery
        if (StrUtil.isNotBlank(queryPageParam.getAuthor())) {
            filter.must(QueryBuilders.queryStringQuery(
                    queryPageParam.getAuthor()).defaultField("author").fuzziness(Fuzziness.AUTO));
        }
        // 内容-模糊查询-fuzzyQuery
        if (StrUtil.isNotBlank(queryPageParam.getContent())) {
            filter.must(QueryBuilders.fuzzyQuery("content",
                            queryPageParam.getContent().toLowerCase()).fuzziness(Fuzziness.AUTO));
        }
        // 全文查询-模糊查询-multiMatchQuery
        if (StrUtil.isNotBlank(queryPageParam.getKeyword())) {
            filter.must(QueryBuilders.multiMatchQuery(
                    queryPageParam.getKeyword(),"title", "author", "content").fuzziness(Fuzziness.AUTO));
        }
        // 客户端时间戳-范围查询-rangeQuery
        if (Objects.nonNull(queryPageParam.getMinClientTimestamp())) {
            filter.must(QueryBuilders.rangeQuery("clientTimestamp")
                    .gte(queryPageParam.getMinClientTimestamp()));
        }
        if (Objects.nonNull(queryPageParam.getMaxClientTimestamp())) {
            filter.must(QueryBuilders.rangeQuery("clientTimestamp")
                    .lte(queryPageParam.getMaxClientTimestamp()));
        }

        searchQueryBuilder.withFilter(filter);
        // 分页信息
        searchQueryBuilder.withPageable(pageable);

        NativeSearchQuery query = searchQueryBuilder.build();
        SearchHits<BlogEntity> searchHits = elasticTemplate.search(query, BlogEntity.class);
        log.info("搜索结果: {}\n{}", searchHits, JSONUtil.toJsonStr(searchHits.getSearchHits()));
        Page<BlogEntity> page = PageableExecutionUtils.getPage(searchHits.getSearchHits().stream()
                .map(SearchHit::getContent).collect(Collectors.toList()),pageable, searchHits::getTotalHits);
        return ApiResult.success(page);
    }

    /**
     * 更新单条
     *
     * @param updateParam
     * @return
     */
    @Override
    public ApiResult<BlogEntity> update(BlogUpdateParam updateParam) {
        BlogEntity blogEntity = elasticTemplate.get(updateParam.getId(), BlogEntity.class);
        if (Objects.isNull(blogEntity)) {
            return ApiResult.fail(ApiMsgEnum.BLOG_NOT_EXIST);
        }
        BeanUtil.copyProperties(updateParam, blogEntity, CopyOptions.create().ignoreError().ignoreNullValue());
        elasticTemplate.save(blogEntity);
        return ApiResult.success(blogEntity);
    }

    /**
     * 删除单条
     *
     * @param deleteOneParam
     * @return
     */
    @Override
    public ApiResult<Void> delete(BlogDeleteOneParam deleteOneParam) {
        BlogEntity blogEntity = elasticTemplate.get(deleteOneParam.getId(), BlogEntity.class);
        if (Objects.isNull(blogEntity)) {
            return ApiResult.fail(ApiMsgEnum.BLOG_NOT_EXIST);
        }
        elasticTemplate.delete(deleteOneParam.getId(), BlogEntity.class);
        return ApiResult.success();
    }


}
