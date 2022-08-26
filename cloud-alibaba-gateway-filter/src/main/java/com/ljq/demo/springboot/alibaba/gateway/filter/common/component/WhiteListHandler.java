package com.ljq.demo.springboot.alibaba.gateway.filter.common.component;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ljq.demo.springboot.alibaba.gateway.filter.common.constant.RedisKeyConst;
import com.ljq.demo.springboot.alibaba.gateway.filter.dao.WhiteListMapper;
import com.ljq.demo.springboot.alibaba.gateway.filter.model.WhiteListEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 网关白名单处理类
 * @Author: junqiang.lu
 * @Date: 2022/8/23
 */
@Slf4j
@Component
public class WhiteListHandler implements ApplicationRunner {

    @Autowired
    private WhiteListMapper whiteListMapper;
    @Autowired
    private RedisComponent redisComponent;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 将所有白名单加载到缓存中
        log.info("-------------加载网关路由白名单------------------");
        List<WhiteListEntity> whiteListList = whiteListMapper.selectList(Wrappers.emptyWrapper());
        Map<String, Object> whiteListMap = new HashMap<>(16);
        whiteListList.forEach(whiteList -> whiteListMap.put(whiteList.getId().toString(), whiteList));
        redisComponent.mapPutBatch(RedisKeyConst.KEY_GATEWAY_WHITE_LIST, whiteListMap);
    }
}
