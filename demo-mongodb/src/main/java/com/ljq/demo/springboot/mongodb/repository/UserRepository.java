package com.ljq.demo.springboot.mongodb.repository;

import com.ljq.demo.springboot.mongodb.model.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @Description: 用户数据持久层
 * @Author: junqiang.lu
 * @Date: 2021/1/6
 */
public interface UserRepository extends MongoRepository<UserEntity, String> {

    /**
     * 根据年龄查询
     *
     * @param age
     * @return
     */
    List<UserEntity> findByAge(Integer age);


}
