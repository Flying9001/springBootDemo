package com.ljq.demo.springboot.common.page;

import com.ljq.demo.springboot.common.util.SqlCheckUtil;
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
    private static final int DEFAULT_LIMIT = 3;
    /**
     * 最大每页显示条数
     */
    private static final int MAX_LIMIT = 100;
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
    private int currPage = DEFAULT_PAGE;
    private static final String CURR_PAGE_FILED = "currPage";
    /**
     * 每页显示条数
     */
    private int pageLimit = DEFAULT_LIMIT;
    private static final String PAGE_LIMIT_FILED = "pageLimit";
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
     *         currPage: 当前页数
     *         pageLimit: 每页显示条数
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
        String currPageParam = String.valueOf(queryMap.getOrDefault(CURR_PAGE_FILED,""));
        if (currPageParam != null && currPageParam.length() > 0) {
            int currPage = Integer.parseInt(currPageParam);
            this.currPage = currPage < DEFAULT_PAGE ? DEFAULT_PAGE : currPage;
        }
        /**
         * 每页显示条数参数获取与校验
         */
        String pageLimitParam = String.valueOf(queryMap.getOrDefault(PAGE_LIMIT_FILED,""));
        if (pageLimitParam != null && pageLimitParam.length() > 0) {
            int pageLimit = Integer.parseInt(pageLimitParam);
            this.pageLimit = pageLimit < DEFAULT_PAGE ? DEFAULT_LIMIT : pageLimit;
            this.pageLimit = pageLimit > MAX_LIMIT ? MAX_LIMIT : pageLimit;
        }
        int offset = (this.currPage - 1) * this.pageLimit;
        this.offset = offset;
        /**
         * 排序规则,升序:ASC;降序:DESC
         */
        String direction = String.valueOf(queryMap.getOrDefault(DIRECTION_FILED,""));
        if (currPageParam != null && currPageParam.length() > 0) {
            switch (direction.toUpperCase()) {
                case "ASC":
                    this.direction = direction;
                    break;
                case "DESC" :
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
        this.put(CURR_PAGE_FILED, this.currPage);
        this.put(PAGE_LIMIT_FILED, this.pageLimit);
        this.put(OFFSET_FILED, this.offset);
        this.put(DIRECTION_FILED, this.direction);
        this.put(PROPERTIES_FILED, this.properties);

    }

    public void setOffset(int offset) {
        this.offset = offset;
        this.put(OFFSET_FILED, this.offset);
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
        this.put(CURR_PAGE_FILED, this.currPage);
    }

    public void setPageLimit(int pageLimit) {
        this.pageLimit = pageLimit;
        this.put(PAGE_LIMIT_FILED, this.pageLimit);
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
