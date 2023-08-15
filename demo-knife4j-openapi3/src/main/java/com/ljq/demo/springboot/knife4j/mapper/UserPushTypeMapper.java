package com.ljq.demo.springboot.knife4j.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljq.demo.springboot.knife4j.model.entity.UserPushTypeEntity;
import org.springframework.stereotype.Repository;

/**
 * 用户消息推送方式
 * 
 * @author junqiang.lu
 * @date 2023-08-14 17:07:45
 */
@Repository
public interface UserPushTypeMapper extends BaseMapper<UserPushTypeEntity> {
	
}
