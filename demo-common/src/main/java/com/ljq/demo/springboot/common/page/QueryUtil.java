package com.ljq.demo.springboot.common.page;

import com.ljq.demo.springboot.common.util.SQLCheckUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 分页查询工具(极简版)
 * @Author: junqiang.lu
 * @Date: 2018/11/28
 */
@Data
public class QueryUtil extends HashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = -731879956899505222L;

    /**
     * 默认当前页数
     * 最小当前页数
     */
    private static final int DEFAULT_PAGE = 1;

    /**
     * 默认每页显示条数
     * 最小每页显示条数
     */
    private static final int DEFAULT_LIMIT = 5;
    /**
     * 最大每页显示条数
     */
    private static final int MAX_LIMIT = 100;

    private int currPage = 1;

    private int pageLimit = 5;

    private QueryUtil(){}

    /**
     * 有参构造方法
     *
     * @param queryMap 包含分页查询参数的 map 集合
     *     map 中需要包含的分页参数:
     *         currPage: 当前页数
     *         pageLimit: 每页显示条数
     *         sidx: 排序依据,如按照 "id" 排序,则 map.put("sidx","id")
     *         order: 排序规则,升序(asc)或者降序(desc),如升序排序,则 map.put("order","desc")
     * @throws Exception sql 参数不合法
     */
    public QueryUtil(Map<String, Object> queryMap) throws Exception {

        /**
         * 当前页码参数获取与校验
         */
        String currPageParam = String.valueOf(queryMap.get("currPage"));
        if (currPageParam != null && currPageParam.length() > 0) {
            int currPage = Integer.parseInt(currPageParam);
            this.currPage = currPage < DEFAULT_PAGE ? DEFAULT_PAGE : currPage;
        }
        /**
         * 每页显示条数参数获取与校验
         */
        String pageLimitParam = String.valueOf(queryMap.get("pageLimit"));
        if (pageLimitParam != null && pageLimitParam.length() > 0) {
            int pageLimit = Integer.parseInt(pageLimitParam);
            this.pageLimit = pageLimit < DEFAULT_PAGE ? DEFAULT_LIMIT : pageLimit;
            this.pageLimit = pageLimit > MAX_LIMIT ? MAX_LIMIT : pageLimit;
        }
        this.put("offset", (this.currPage - 1) * this.pageLimit);
        this.put("currPage", this.currPage);
        this.put("pageLimit", this.pageLimit);
        /**
         * 排序规则参数获取(防止 sql 注入)
         */
        this.put("sidx", SQLCheckUtil.getSafeSQL(String.valueOf(queryMap.get("sidx"))));
        this.put("order", SQLCheckUtil.getSafeSQL(String.valueOf(queryMap.get("order"))));
    }


}
