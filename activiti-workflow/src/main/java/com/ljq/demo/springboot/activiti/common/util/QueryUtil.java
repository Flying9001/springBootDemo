package com.ljq.demo.springboot.activiti.common.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 分页查询工具(极简版)
 * @Author: junqiang.lu
 * @Date: 2018/11/28
 */
@Getter
@ToString
@NoArgsConstructor
public class QueryUtil extends HashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = -2496264573068256845L;

    /**
     * 默认起始点
     */
    private static final int DEFAULT_OFFSET = 0;
    /**
     * 默认当前页数
     * 最小当前页数
     */
    private static final int DEFAULT_PAGE = 1;
    /**
     * 默认每页显示条数
     * 最小每页显示条数
     */
    private static final int DEFAULT_PAGE_SIZE = 3;
    /**
     * 最大每页显示条数
     */
    private static final int MAX_PAGE_SIZE = 100;
    /**
     * 默认排序规则,降序(DESC)
     */
    private static final String DEFAULT_DIRECTION = "DESC";
    /**
     * 默认排序依据
     */
    private static final String DEFAULT_PROPERTIES = "id";

    /**
     * 起始点
     */
    private int offset = DEFAULT_OFFSET;
    private static final String OFFSET_FILED = "offset";
    /**
     * 当前页
     */
    private int currentPage = DEFAULT_PAGE;
    private static final String CURRENT_PAGE_FILED = "currentPage";
    /**
     * 每页显示条数
     */
    private int pageSize = DEFAULT_PAGE_SIZE;
    private static final String PAGE_SIZE_FILED = "pageSize";
    /**
     * 排序规则,升序:ASC;降序:DESC
     */
    private String direction = DEFAULT_DIRECTION;
    private static final String DIRECTION_FILED = "direction";
    /**
     * 排序依据
     */
    private String properties = DEFAULT_PROPERTIES;
    private static final String PROPERTIES_FILED = "properties";

    /**
     * 有参构造方法
     *
     * @param queryMap 包含分页查询参数的 map 集合
     *     map 中需要包含的分页参数:
     *         currentPage: 当前页数
     *         pageSize: 每页显示条数
     *         direction: 排序规则,升序(asc)或者降序(desc),如升序排序,则 map.put("direction","asc")
     *         properties: 排序依据,如按照 "id" 排序,则 map.put("properties","id")
     * @throws Exception sql 参数不合法
     */
    public QueryUtil(Map<String, Object> queryMap) throws Exception {
        if (queryMap != null && !queryMap.isEmpty()) {
            this.putAll(queryMap);
        } else {
            queryMap = new HashMap<>(16);
        }
        /**
         * 当前页码参数获取与校验
         */
        String currentPageParam = String.valueOf(queryMap.getOrDefault(CURRENT_PAGE_FILED,""));
        if (currentPageParam != null && currentPageParam.length() > 0) {
            int currentPage = Integer.parseInt(currentPageParam);
            this.currentPage = currentPage < DEFAULT_PAGE ? DEFAULT_PAGE : currentPage;
        }
        /**
         * 每页显示条数参数获取与校验
         */
        String pageSizeParam = String.valueOf(queryMap.getOrDefault(PAGE_SIZE_FILED,""));
        if (pageSizeParam != null && pageSizeParam.length() > 0) {
            int pageSize = Integer.parseInt(pageSizeParam);
            this.pageSize = pageSize < DEFAULT_PAGE ? DEFAULT_PAGE_SIZE : pageSize;
            this.pageSize = pageSize > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : pageSize;
        }
        int offset = (this.currentPage - 1) * this.pageSize;
        this.offset = offset;
        /**
         * 排序规则,升序:ASC;降序:DESC
         */
        String direction = String.valueOf(queryMap.getOrDefault(DIRECTION_FILED,""));
        if (currentPageParam != null && currentPageParam.length() > 0) {
            switch (direction.toUpperCase()) {
                case "ASC":
                case "DESC":
                    this.direction = direction;
                    break;
                default:
                    break;
            }
        }
        /**
         * 排序依据参数(防止 sql 注入)
         */
        String properties = SqlCheckUtil.getSafeSQL(String.valueOf(queryMap.getOrDefault(PROPERTIES_FILED,"")));
        if (properties != null && properties.length() > 0) {
            this.properties = properties;
        }
        this.put(CURRENT_PAGE_FILED, this.currentPage);
        this.put(PAGE_SIZE_FILED, this.pageSize);
        this.put(OFFSET_FILED, this.offset);
        this.put(DIRECTION_FILED, this.direction);
        this.put(PROPERTIES_FILED, this.properties);

    }

    public void setOffset(int offset) {
        this.offset = offset;
        this.put(OFFSET_FILED, this.offset);
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        this.put(CURRENT_PAGE_FILED, this.currentPage);
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        this.put(PAGE_SIZE_FILED, this.pageSize);
    }

    public void setDirection(String direction) {
        this.direction = direction;
        this.put(DIRECTION_FILED, this.direction);
    }

    public void setProperties(String properties) {
        this.properties = properties;
        this.put(PROPERTIES_FILED, this.properties);
    }
}
