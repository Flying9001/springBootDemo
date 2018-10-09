package com.ljq.demo.common.page;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: 查询分页工具类
 * @Author: junqiang.lu
 * @Date: 2018/10/9
 */
@Data
public class QueryUtil extends LinkedHashMap<String, Object> {

    // 当前页码
    private int page = 1;
    // 每页条数
    private int limit = 10;

    public QueryUtil() {
        super();
    }

    public QueryUtil(Map<String, Object> params) {
        this.putAll(params);

//        // 分页参数
//        if (!BaseUtils.isEmptyObject(params.get("page"))) {
//            P2PAssert.isInteger(params.get("page"), "查询页码page格式不正确");
//            this.page = Integer.parseInt(params.get("page").toString());
//        }
//
//        if (!BaseUtils.isEmptyObject(params.get("pageSize"))) {
//            P2PAssert.isInteger(params.get("pageSize"), "每页查询数量pageSize格式不正确");
//            this.limit = Integer.parseInt(params.get("pageSize").toString());
//        }
//
//
//        this.put("offset", (page - 1) * limit);
//        this.put("page", page);
//        this.put("limit", limit);
//
//        // 防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
//        String sidx = (String) params.get("sidx");
//        String order = (String) params.get("order");
//        if (StringUtils.isEmpty(sidx)) {
//            this.put("sidx", SQLFilter.sqlInject(sidx));
//        }
//        if (StringUtils.isEmpty(order)) {
//            this.put("order", SQLFilter.sqlInject(order));
//        }

    }




}
