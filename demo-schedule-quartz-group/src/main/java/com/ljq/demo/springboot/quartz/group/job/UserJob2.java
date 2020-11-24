package com.ljq.demo.springboot.quartz.group.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @Description: 用户工作负载2
 * @Author: junqiang.lu
 * @Date: 2020/11/14
 */
@Slf4j
@DisallowConcurrentExecution
public class UserJob2 extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.debug("------定时任务开始执行-------");
    }
}
