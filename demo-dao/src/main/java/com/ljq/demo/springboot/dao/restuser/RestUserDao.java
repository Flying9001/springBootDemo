package com.ljq.demo.springboot.dao.restuser;

import com.ljq.demo.springboot.dao.BaseDao;
import com.ljq.demo.springboot.entity.RestUserEntity;
import org.springframework.stereotype.Repository;

/**
 * REST示例-用户表
 * 
 * @author junqiang.lu
 * @date 2019-09-19 17:19:42
 */
@Repository
public interface RestUserDao extends BaseDao<RestUserEntity> {
	
}
