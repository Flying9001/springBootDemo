package com.ljq.demo.springboot.knife4j.job;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljq.demo.springboot.knife4j.model.BasePageParam;
import com.ljq.demo.springboot.knife4j.model.entity.UserMessageEntity;
import com.ljq.demo.springboot.knife4j.service.UserMessageService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description: 消息推送定时任务
 * @Author: junqiang.lu
 * @Date: 2023/8/17
 */
@Component
public class MessagePushSchedule {

    @Resource
    private UserMessageService userMessageService;


    /**
     * 消息重试
     * 300 秒(5分钟) 1 次
     */
    @Scheduled(fixedDelay = 300000L, initialDelay = 10000L)
    public void checkAndRepush() {
        // 统计所有当天发送失败的消息
        int pageSize = 1000;
        BasePageParam pageParam = new BasePageParam();
        pageParam.setCurrentPage(1);
        pageParam.setPageSize(pageSize);
        IPage<UserMessageEntity> pageResult = userMessageService.queryPageFailMessage(pageParam);
        if (pageResult.getTotal() < 1) {
            return;
        }
        long countAll = pageResult.getTotal();
        long times = countAll % pageSize == 0 ? countAll / pageSize : (countAll / pageSize) + 1;
        pageResult.getRecords().forEach(userMessage -> userMessageService.repush(userMessage));
        for (int i = 2; i < times + 1; i++) {
            pageParam.setCurrentPage(i);
            pageResult = userMessageService.queryPageFailMessage(pageParam);
            if (CollUtil.isEmpty(pageResult.getRecords())) {
                continue;
            }
            pageResult.getRecords().forEach(userMessage -> userMessageService.repush(userMessage));
        }
    }


}
