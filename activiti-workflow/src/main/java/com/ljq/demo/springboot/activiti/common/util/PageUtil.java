package com.ljq.demo.springboot.activiti.common.util;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @Description: 分页工具(极简版)
 * @Author: junqiang.lu
 * @Date: 2018/11/28
 */
@Data
public class PageUtil<T> implements Serializable {

    private static final long serialVersionUID = -1989898212258749371L;

    /**
     * 默认总记录条数
     * 总记录条数最小值
     */
    private static final int DEFAULT_TOTAL_COUNT = 0;
    /**
     * 默认当前页数
     * 当前页数最小值
     */
    private static final int DEFAULT_CURR_PAGE = 1;
    /**
     * 默认每页记录数
     * 每页最小记录数
     */
    private static final int DEFAULT_PAGE_LIMIT = 3;

    /**
     * 总记录条数
     */
    private int totalCount;
    /**
     * 每页记录数
     */
    private int pageLimit;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 当前页数
     */
    private int currPage;
    /**
     * 列表数据
     */
    private List<T> list;


    private PageUtil(){}

    /**
     * 有参构造方法
     *
     * @param list 列表数据
     * @param totalCount 总记录条数
     * @param pageLimit 每页记录数
     * @param currPage 当前页数
     */
    public PageUtil(List<T> list, int totalCount, int pageLimit, int currPage){
        if (totalCount < DEFAULT_TOTAL_COUNT) {
            totalCount = DEFAULT_TOTAL_COUNT;
        }
        this.totalCount = totalCount;

        if (pageLimit < DEFAULT_PAGE_LIMIT) {
            pageLimit = DEFAULT_PAGE_LIMIT;
        }
        this.pageLimit = pageLimit;

        if (currPage < DEFAULT_CURR_PAGE) {
            currPage = DEFAULT_CURR_PAGE;
        }
        this.currPage = currPage;

        int remainder = totalCount % pageLimit;
        if (remainder > 0) {
            this.totalPage = (totalCount / pageLimit + 1);
        } else {
            this.totalPage = (totalCount / pageLimit);
        }

        this.list = list;
        if (list == null) {
            this.list = Collections.emptyList();
        }
    }




}
