package com.ljq.demo.springboot.activiti.dao;

import com.ljq.demo.springboot.activiti.model.entity.LeaveInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 请假信息
 * 
 * @author junqiang.lu
 * @date 2020-07-09 16:25:39
 */
@Repository
public interface LeaveInfoDao  {

    /**
     * 保存
     *
     * @param leaveInfoEntity
     * @return
     */
    int save(LeaveInfoEntity leaveInfoEntity);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    LeaveInfoEntity queryObject(Object id);

    /**
     * 统计查询条数
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
    List<LeaveInfoEntity> queryList(Map<String, Object> queryMap);

    /**
     * 删除单条
     *
     * @param entity
     * @return
     */
    int delete(LeaveInfoEntity entity);

    /**
     * 批量删除
     *
     * @param leaveInfoList
     * @return
     */
    int deleteBatch(@Param("list") List<LeaveInfoEntity> leaveInfoList);


}
