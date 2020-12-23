package com.ljq.demo.springboot.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljq.demo.springboot.mybatisplus.model.entity.ClassEntity;
import com.ljq.demo.springboot.mybatisplus.model.entity.ClassTeacherEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 班级
 * 
 * @author junqiang.lu
 * @date 2020-10-09 17:27:32
 */
@Repository
public interface ClassDao extends BaseMapper<ClassEntity> {

    /**
     * 插入单条
     *
     * @param classEntity
     * @return
     */
    int save(ClassEntity classEntity);

    /**
     * 批量插入班级教师
     *
     * @param classTeacherList
     * @return
     */
    int saveClassTeacherBatch(List<ClassTeacherEntity> classTeacherList);

    /**
     * 查询单条
     *
     * @param id
     * @return
     */
    ClassEntity queryOne(Long id);

    /**
     * 统计条数
     *
     * @param queryMap
     * @return
     */
    int queryCount(Map<String, Object> queryMap);

    /**
     * 查询列表
     *
     * @param queryMap
     * @return
     */
    List<ClassEntity> queryList(Map<String, Object> queryMap);

    /**
     * 列表查询(支持一对多分页)
     *
     * @param queryMap
     * @return
     */
    List<ClassEntity> queryList2(Map<String, Object> queryMap);

    /**
     * 分页查询
     *
     * @param page
     * @param queryMap
     * @return
     */
    IPage<ClassEntity> queryPage(@Param("page") IPage<?> page, @Param("map") Map<String, Object> queryMap);

    /**
     * 分页查询2
     * 一对多测试
     *
     * @param page
     * @param queryMap
     * @return
     */
    IPage<ClassEntity> queryPage2(@Param("page") IPage<?> page, @Param("map") Map<String, Object> queryMap);

    /**
     * 分页查询3
     * 条件过滤
     *
     * @param page
     * @param queryMap
     * @return
     */
    IPage<ClassEntity> queryPage3(@Param("page") IPage<?> page, @Param("map") Map<String, Object> queryMap);

    /**
     * 更新单条
     *
     * @param classEntity
     * @return
     */
    int updateOne(ClassEntity classEntity);

    /**
     * 删除单条
     *
     * @param id
     * @return
     */
    int deleteOne(Long id);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int deleteBatch(Long[] ids);

}
