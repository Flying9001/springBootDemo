package com.ljq.demo.springboot.mybatisplus.common.util;

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
    private int pageSize;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 当前页数
     */
    private int currentPage;
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
     * @param pageSize 每页记录数
     * @param currentPage 当前页数
     */
    public PageUtil(List<T> list, int totalCount, int pageSize, int currentPage){
        if (totalCount < DEFAULT_TOTAL_COUNT) {
            totalCount = DEFAULT_TOTAL_COUNT;
        }
        this.totalCount = totalCount;

        if (pageSize < DEFAULT_PAGE_LIMIT) {
            pageSize = DEFAULT_PAGE_LIMIT;
        }
        this.pageSize = pageSize;

        if (currentPage < DEFAULT_CURR_PAGE) {
            currentPage = DEFAULT_CURR_PAGE;
        }
        this.currentPage = currentPage;

        int remainder = totalCount % pageSize;
        if (remainder > 0) {
            this.totalPage = (totalCount / pageSize + 1);
        } else {
            this.totalPage = (totalCount / pageSize);
        }

        this.list = list;
        if (list == null) {
            this.list = Collections.emptyList();
        }
    }




}
