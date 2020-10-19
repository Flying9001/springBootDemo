package com.ljq.demo.springboot.mybatisplus.common.util;

import java.sql.SQLException;
import java.util.regex.Pattern;

/**
 * @Description: sql 校验工具
 * @Author: junqiang.lu
 * @Date: 2018/11/28
 */
public class SqlCheckUtil {

    /**
     * sql 最大长度
     */
    private static final int MAX_SQL_LENGTH = 1024 * 1024;

    private SqlCheckUtil(){}

    /**
     * 获取安全 sql 语句(防止 sql 注入)
     * 返回为空(null)的情况:
     *     1) sql 语句为空
     *     2) sql 语句中包含可能产生 sql 注入风险的关键词
     *
     * @param sql sql 语句
     * @return null or safe sql
     * @throws SQLException 当 sql 语句长度超过 ${MAX_SQL_LENGTH} 字符时抛出异常
     */
    public static String getSafeSQL(String sql) throws SQLException {
        if (sql == null || sql.length() <= 0) {
            return null;
        }
        if (sql.length() > MAX_SQL_LENGTH) {
            throw new SQLException("Query string is too long,it must be less than 1048576 = 1024*1024.");
        }
        /**
         * 防止 sql 注入
         */
        String sqlRegex = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
                                + "(\\b(select|update|union|and|or|delete|insert|trancate|char|"
                                + "into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
        Pattern sqlPattern = Pattern.compile(sqlRegex, Pattern.CASE_INSENSITIVE);
        if (sqlPattern.matcher(sql).find()) {
            return null;
        }
        return sql;
    }


}
