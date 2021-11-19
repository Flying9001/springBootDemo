package com.ljq.demo.springboot.mongodb.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.ljq.demo.springboot.mongodb.common.api.ApiResult;
import com.ljq.demo.springboot.mongodb.common.api.ResponseCode;
import com.ljq.demo.springboot.mongodb.common.constant.BlogConst;
import com.ljq.demo.springboot.mongodb.model.entity.BlogEntity;
import com.ljq.demo.springboot.mongodb.model.param.*;
import com.ljq.demo.springboot.mongodb.model.vo.BlogGroupDateVo;
import com.ljq.demo.springboot.mongodb.model.vo.BlogSummaryDiyVo;
import com.ljq.demo.springboot.mongodb.model.vo.BlogSummaryVo;
import com.ljq.demo.springboot.mongodb.service.BlogService;
import com.mongodb.client.result.DeleteResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.*;
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
        blogEntity.setCountRead(0);
        blogEntity.setCountLike(0);
        mongoTemplate.save(blogEntity);
        return ApiResult.success(blogEntity);
    }

    /**
     * 批量新增博客
     *
     * @param addBatchParam
     * @return
     */
    @Override
    public ApiResult addBatch(BlogAddBatchParam addBatchParam) {
        List<BlogEntity> blogEntityList = addBatchParam.getBlogList().stream().map(blogAddParam -> {
            BlogEntity blogEntity = new BlogEntity();
            BeanUtil.copyProperties(blogAddParam, blogEntity);
            blogEntity.setCountRead(0);
            blogEntity.setCountLike(0);
            return blogEntity;
        }).collect(Collectors.toList());
        mongoTemplate.insert(blogEntityList, BlogEntity.class);
        return ApiResult.success();
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
     * 查询博客阅读量
     *
     * @param readCountQueryParam
     * @return
     */
    @Override
    public ApiResult queryReadCount(BlogReadCountQueryParam readCountQueryParam) {
        // 查询条件
        Criteria criteria = Criteria.where("id").is(readCountQueryParam.getId());
        // 查询字段
        Query query = Query.query(criteria);
        query.fields().include("id", "title", "countRead", "countLike");
        return ApiResult.success(mongoTemplate.findOne(query, BlogEntity.class));
    }

    /**
     * 查询博客阅读量统计
     *
     * @param readCountSummaryParam
     * @return
     */
    @Override
    public ApiResult summaryReadCount(BlogReadCountSummaryParam readCountSummaryParam) {
        // 查询条件
        Criteria criteria = new Criteria();
        if (StrUtil.isNotBlank(readCountSummaryParam.getId())) {
            criteria.and("id").is(readCountSummaryParam.getId());
        }
        if (StrUtil.isNotBlank(readCountSummaryParam.getAuthor())) {
            criteria.and("author").is(readCountSummaryParam.getAuthor());
        }
        // 查询条件
        MatchOperation match = Aggregation.match(criteria);
        // 分组统计
        GroupOperation group = Aggregation.group()
                .count().as("totalBlog")
                .sum("countRead").as("totalRead")
                .avg("countRead").as("aveRead")
                .sum("countLike").as("totalLike")
                .avg("countLike").as("aveLike");
        // 查询结果
        AggregationResults<BlogSummaryVo> results = mongoTemplate.aggregate(Aggregation.newAggregation(BlogEntity.class,
                match, group), BlogSummaryVo.class);
        return ApiResult.success(results.getUniqueMappedResult());
    }

    /**
     * 按照作者分组查询博客数据
     *
     * @param queryGroupByAuthorParam
     * @return
     */
    @Override
    public ApiResult queryGroupByAuthor(BlogQueryGroupByAuthorParam queryGroupByAuthorParam) {
        // 查询条件
        Criteria criteria = new Criteria();
        if (StrUtil.isNotBlank(queryGroupByAuthorParam.getAuthor())) {
            criteria.and("author").is(queryGroupByAuthorParam.getAuthor());
        }
        MatchOperation matchOperation = Aggregation.match(criteria);
        // 查询字段
        ProjectionOperation projectionOperation = Aggregation.project("id", "author", "countRead", "countLike");
        // 分组统计
        GroupOperation groupOperation = Aggregation.group("author")
                .first("author").as("author")
                .count().as("totalBlog")
                .sum("countRead").as("totalRead")
                .avg("countRead").as("aveRead")
                .sum("countLike").as("totalLike")
                .avg("countLike").as("aveLike");
        // 查询结果
        AggregationResults<BlogSummaryVo> results = mongoTemplate.aggregate(Aggregation.newAggregation(
                BlogEntity.class,matchOperation, projectionOperation, groupOperation), BlogSummaryVo.class);
        log.info("查询结果: {}", results.getMappedResults());
        return ApiResult.success(results.getMappedResults());
    }

    /**
     * 按照作者分组分页查询你博客数据
     *
     * @param queryGroupByAuthorPageParam
     * @return
     */
    @Override
    public ApiResult queryGroupByAuthorPage(BlogQueryGroupByAuthorPageParam queryGroupByAuthorPageParam) {
        // 查询条件
        Criteria criteria = new Criteria();
        MatchOperation matchOperation = Aggregation.match(criteria);
        // 查询字段
        ProjectionOperation projectionOperation = Aggregation.project("id", "author","countRead", "countLike");
        // 分组统计
        GroupOperation groupOperation = Aggregation.group("author")
                .first("author").as("author")
                .count().as("totalBlog")
                .sum("countRead").as("totalRead")
                .avg("countRead").as("aveRead")
                .sum("countLike").as("totalLike")
                .avg("countLike").as("aveLike");
        // 查询总条数
        GroupOperation groupOperation2 = Aggregation.group().count().as("totalAuthor");
        AggregationResults<Map> totalAuthorResult = mongoTemplate.aggregate(Aggregation.newAggregation(BlogEntity.class,
                matchOperation, projectionOperation, groupOperation, groupOperation2), Map.class);
        int totalAuthor = (int) totalAuthorResult.getUniqueMappedResult().getOrDefault("totalAuthor", 0);
        // 设置分页信息
        SortOperation sortOperation = Aggregation.sort(Sort.by(Sort.Direction.DESC, "author"));
        SkipOperation skipOperation = Aggregation.skip((queryGroupByAuthorPageParam.getCurrentPage() - 1)
                * (long) queryGroupByAuthorPageParam.getPageSize());
        LimitOperation limitOperation = Aggregation.limit(queryGroupByAuthorPageParam.getPageSize());
        // 查询结果
        AggregationResults<BlogSummaryVo> results = mongoTemplate.aggregate(Aggregation.newAggregation(
                BlogEntity.class, matchOperation, projectionOperation, groupOperation, sortOperation,
                skipOperation, limitOperation), BlogSummaryVo.class);
        // 组装分页结果
        Page<BlogSummaryVo> page = PageableExecutionUtils.getPage(results.getMappedResults(),
                PageRequest.of(queryGroupByAuthorPageParam.getCurrentPage() -1,
                        queryGroupByAuthorPageParam.getPageSize()), () -> totalAuthor);
        return ApiResult.success(page);
    }

    /**
     * 按照客户端时间分组查询博客数据
     *
     * @param queryGroupByClientTimestampParam
     * @return
     */
    @Override
    public ApiResult queryGroupByClientTimestamp(BlogQueryGroupByClientTimestampParam
                                                             queryGroupByClientTimestampParam) {
        // 获取日期分组信息
        BlogGroupDateVo groupDateVo = getGroupDate(queryGroupByClientTimestampParam.getDateType());
        // 查询条件
        Criteria criteria = Criteria.where("clientTimestamp").gte(queryGroupByClientTimestampParam
                        .getMinClientTimestamp()).lte(queryGroupByClientTimestampParam.getMaxClientTimestamp());
        MatchOperation matchOperation = Aggregation.match(criteria);
        // 查询字段
        ProjectionOperation projectionOperation = Aggregation.project("countRead","countLike","clientTimestamp")
                .and(DateOperators.DateToString.dateOf(aggregationOperationContext ->
                                new Document("$add", Arrays.asList(new Date(28800000),
                                        new Document("$multiply", Arrays.asList("$clientTimestamp", 1000)))))
                        .toString(groupDateVo.getDateFormat())).as(groupDateVo.getDateGroupField());
        // 分组统计
        GroupOperation groupOperation = Aggregation.group(groupDateVo.getDateGroupField())
                .first(groupDateVo.getDateGroupField()).as("date")
                .count().as("totalBlog")
                .sum("countRead").as("totalRead")
                .avg("countRead").as("aveRead")
                .sum("countLike").as("totalLike")
                .avg("countLike").as("aveLike");
        // 排序
        SortOperation sortOperation = Aggregation.sort(Sort.by(Sort.Direction.ASC, "date"));
        // 查询结果
        AggregationResults<BlogSummaryVo> results = mongoTemplate.aggregate(Aggregation.newAggregation(
                BlogEntity.class, matchOperation, projectionOperation, groupOperation, sortOperation),
                BlogSummaryVo.class);
        return ApiResult.success(results.getMappedResults());
    }

    /**
     * 按照创建时间分组查询博客数据
     *
     * @param queryGroupByCreateTimeParam
     * @return
     */
    @Override
    public ApiResult queryGroupByCreateTime(BlogQueryGroupByCreateTimeParam queryGroupByCreateTimeParam) {
        // 获取日期分组信息
        BlogGroupDateVo groupDateVo = getGroupDate(queryGroupByCreateTimeParam.getDateType());
        // 查询条件
        Criteria criteria = Criteria.where("createTime").gte(queryGroupByCreateTimeParam.getMinCreateTime())
                .lte(queryGroupByCreateTimeParam.getMaxCreateTime());
        MatchOperation matchOperation = Aggregation.match(criteria);
        // 查询字段
        ProjectionOperation projectionOperation = Aggregation.project("countRead", "countLike","createTime")
                .and(DateOperators.DateToString.dateOf(aggregationOperationContext ->
                                new Document("$add", Arrays.asList(new Date(28800000),"$createTime")))
                        .toString(groupDateVo.getDateFormat())).as(groupDateVo.getDateGroupField());
        // 分组统计
        GroupOperation groupOperation = Aggregation.group(groupDateVo.getDateGroupField())
                .first(groupDateVo.getDateGroupField()).as("date")
                .count().as("totalBlog")
                .sum("countRead").as("totalRead")
                .avg("countRead").as("aveRead")
                .sum("countLike").as("totalLike")
                .avg("countLike").as("aveLike");
        // 排序
        SortOperation sortOperation = Aggregation.sort(Sort.by(Sort.Direction.ASC, "date"));
        // 查询结果
        AggregationResults<BlogSummaryVo> results = mongoTemplate.aggregate(Aggregation.newAggregation(
                BlogEntity.class, matchOperation, projectionOperation, groupOperation, sortOperation),
                BlogSummaryVo.class);
        return ApiResult.success(results.getMappedResults());
    }

    /**
     * 按照创建时间自定义区间分组查询博客数据
     *
     * @param queryGroupByCreateTimeDiyParam
     * @return
     */
    @Override
    public ApiResult queryGroupByCreateTimeDiy(BlogQueryGroupByCreateTimeDiyParam queryGroupByCreateTimeDiyParam) {
        // 查询条件
        Criteria criteria = Criteria.where("createTime").gte(queryGroupByCreateTimeDiyParam.getMinCreateTime())
                .lte(queryGroupByCreateTimeDiyParam.getMaxCreateTime());
        MatchOperation matchOperation = Aggregation.match(criteria);
        // 查询字段
        ProjectionOperation projectionOperation = Aggregation.project("countRead", "countLike","createTime")
                .and(DateOperators.Minute.minuteOf(aggregationOperationContext ->
                                new Document("$add", Arrays.asList(new Date(28800000),"$createTime"))))
                        .as("minute")
                .and(DateOperators.Hour.hourOf(aggregationOperationContext ->
                        new Document("$add", Arrays.asList(new Date(28800000),"$createTime"))))
                .as("hour")
                .and(DateOperators.DateToString.dateOf(aggregationOperationContext ->
                                new Document("$add", Arrays.asList(new Date(28800000),"$createTime")))
                        .toString("%Y-%m-%d")).as("day");
        ProjectionOperation projectionOperation2 = Aggregation.project("countRead", "countLike",
                "createTime", "minute", "hour","day")
                .andExpression("minute - minute % 30").as("halfHour");
        // 分组统计
        GroupOperation groupOperation = Aggregation.group("day","hour","halfHour")
                .addToSet("hour").as("hour")
                .addToSet("day").as("date")
                .addToSet("halfHour").as("minute")
                .count().as("totalBlog")
                .sum("countRead").as("totalRead")
                .avg("countRead").as("aveRead")
                .sum("countLike").as("totalLike")
                .avg("countLike").as("aveLike");
        // 排序
        SortOperation sortOperation = Aggregation.sort(Sort.by(Sort.Direction.ASC, "day", "hour", "halfHour"));
        // 查询结果
        AggregationResults<BlogSummaryDiyVo> results = mongoTemplate.aggregate(Aggregation.newAggregation(
                        BlogEntity.class, matchOperation, projectionOperation, projectionOperation2,
                        groupOperation, sortOperation), BlogSummaryDiyVo.class);
        return ApiResult.success(results.getMappedResults());
    }

    /**
     * 按照更新时间分组查询博客数据
     *
     * @param queryGroupByUpdateTimeParam
     * @return
     */
    @Override
    public ApiResult queryGroupByUpdateTime(BlogQueryGroupByUpdateTimeParam queryGroupByUpdateTimeParam) {
        // 查询条件
        Criteria criteria = Criteria.where("updateTime").gte(new Date(queryGroupByUpdateTimeParam.getMinUpdateTime()))
                .lte(new Date(queryGroupByUpdateTimeParam.getMaxUpdateTime()));
        MatchOperation matchOperation = Aggregation.match(criteria);
        // 查询字段
        ProjectionOperation projectionOperation = Aggregation.project("countRead", "countLike", "updateTime")
                .and(DateOperators.DayOfMonth.dayOfMonth("updateTime")).as("day")
                .and(DateOperators.Month.monthOf("updateTime")).as("month")
                .and(DateOperators.Year.yearOf("updateTime")).as("year");
        // 分组统计
        GroupOperation groupOperation = Aggregation.group("year","month","day")
                .addToSet("year").as("year")
                .addToSet("month").as("month")
                .addToSet("day").as("date")
                .count().as("totalBlog")
                .sum("countRead").as("totalRead")
                .avg("countRead").as("aveRead")
                .sum("countLike").as("totalLike")
                .avg("countLike").as("aveLike");
        // 排序
        SortOperation sortOperation = Aggregation.sort(Sort.by(Sort.Direction.ASC, "_id.year",
                        "_id.month", "_id.day"));
        // 查询结果
        AggregationResults<BlogSummaryDiyVo> results = mongoTemplate.aggregate(Aggregation.newAggregation(
                        BlogEntity.class, matchOperation, projectionOperation, groupOperation, sortOperation),
                BlogSummaryDiyVo.class);
        return ApiResult.success(results.getMappedResults());
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
     * 批量更新博客
     *
     * @param updateBatchParam
     * @return
     */
    @Override
    public ApiResult updateBatch(BlogUpdateBatchParam updateBatchParam) {
        List<BlogEntity> blogEntityList = updateBatchParam.getBlogList().stream().map(blogUpdateParam -> {
            BlogEntity blogEntity = new BlogEntity();
            BeanUtil.copyProperties(blogUpdateParam, blogEntity);
            return blogEntity;
        }).collect(Collectors.toList());
        BulkOperations operations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, BlogEntity.class);
        for (BlogEntity blogEntity : blogEntityList) {
            Update update = Update.update("id", blogEntity.getId())
                    .set("title", blogEntity.getTitle())
                    .set("content", blogEntity.getContent())
                    .set("author", blogEntity.getAuthor())
                    .set("countRead", blogEntity.getCountRead())
                    .set("countLike", blogEntity.getCountLike())
                    .set("clientTimestamp", blogEntity.getClientTimestamp());
            operations.updateOne(Query.query(Criteria.where("id").is(blogEntity.getId())), update);
        }
        operations.execute();
        return ApiResult.success();
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

    /**
     * 获取博客日期分组信息
     *
     * @param dateType
     * @return
     */
    private BlogGroupDateVo getGroupDate(Integer dateType) {
        BlogGroupDateVo groupDateVo = new BlogGroupDateVo();
        switch (dateType) {
            case BlogConst.DATE_TYPE_DAY:
                groupDateVo.setDateGroupField("day");
                groupDateVo.setDateFormat("%Y-%m-%d");
                break;
            case BlogConst.DATE_TYPE_MONTH:
                groupDateVo.setDateGroupField("month");
                groupDateVo.setDateFormat("%Y-%m");
                break;
            default:
                groupDateVo.setDateGroupField("day");
                groupDateVo.setDateFormat("%Y-%m-%d");
                break;
        }
        return groupDateVo;
    }
}
