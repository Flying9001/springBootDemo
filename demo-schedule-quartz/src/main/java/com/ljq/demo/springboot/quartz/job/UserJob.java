package com.ljq.demo.springboot.quartz.job;

import com.ljq.demo.springboot.quartz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 用户模块工作负载
 * @Author: junqiang.lu
 * @Date: 2020/11/14
 */
@Slf4j
public class UserJob extends QuartzJobBean {

    @Autowired
    private UserService userService;

    private final AtomicInteger counts = new AtomicInteger();

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.debug("【定时任务】第【{}】次执行，用户总数:{}", counts.incrementAndGet(), userService.countAll());
    }
}
