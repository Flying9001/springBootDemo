package com.ljq.demo.springboot.dao;

import java.util.List;
import java.util.Map;

/**
 * @Description: 数据操作基础类
 * @Author: junqiang.lu
 * @Date: 2018/9/29
 */
public interface BaseDao<T> {

    /**
     * 单个对象保存,插入
     * @param t
     * @return
     */
    long save(T t);

    /**
     * 复杂参数保存、插入
     * @param map
     * @return
     */
    long save(Map<String, Object> map);

    /**
     * 批量保存、插入
     * @param list
     * @return
     */
    long saveBatch(List<T> list);

    /**
     * 更新
     * @param t
     * @return
     */
    int update(T t);

    /**
     * 更新
     * @param map 复杂参数
     * @return
     */
    int update(Map<String, Object> map);

    /**
     * 删除单个对象
     * @param id
     * @return
     */
    int delete(Object id);

    /**
     * 删除(可多个,可单个)
     * @param map 复杂参数
     * @return
     */
    int delete(Map<String, Object> map);

    /**
     * 批量删除
     * @param id
     * @return
     */
    int deleteBatch(Object[] id);

    /**
     * 查询单个对象
     * @param id
     * @return
     */
    T queryObject(Object id);

    /**
     * 查询列表
     * @param map 复杂参数
     * @return
     */
    List<T> queryList(Map<String, Object> map);

    /**
     * 查询列表
     * @param id
     * @return
     */
    List<T> queryList(Object id);

    /**
     * 查询列表
     * @param t
     * @return
     */
    List<Map<String, Object>> queryListAll(T t);

    /**
     * 统计查询个数
     * @param map
     * @return
     */
    int queryCount(Map<String, Object> map);

    /**
     * 统计查询个数
     * @param t
     * @return
     */
    int queryCount(T t);

    /**
     * 统计查询个数
     * @return
     */
    int queryCount();
}
