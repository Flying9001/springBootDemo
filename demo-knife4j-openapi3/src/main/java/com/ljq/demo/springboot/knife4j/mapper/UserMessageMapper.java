package com.ljq.demo.springboot.knife4j.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljq.demo.springboot.knife4j.model.entity.UserMessageEntity;
import org.springframework.stereotype.Repository;

/**
 * 用户消息
 * 
 * @author junqiang.lu
 * @date 2023-08-14 18:06:40
 */
@Repository
public interface UserMessageMapper extends BaseMapper<UserMessageEntity> {

    /**
     * 查询未推送成功的消息
     *
     * @param page
     * @return
     */
    IPage<UserMessageEntity> queryPageFailMessage(IPage<UserMessageEntity> page);
	
}
