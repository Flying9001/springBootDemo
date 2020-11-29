package com.ljq.demo.springboot.xxljob.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 用户定时任务负载类(Bean 模式)
 * @Author: junqiang.lu
 * @Date: 2020/11/27
 */
@Slf4j
@Component
public class UserJob {

    /**
     * 用户定时任务处理器名称
     */
    private static final String USER_JOB_HANDLE_NAME = "user-job-handle";

    private final AtomicInteger count = new AtomicInteger();

    /**
     * 用户定时任务处理方法
     *
     * @param param
     * @return
     * @throws Exception
     */
    @XxlJob(value = USER_JOB_HANDLE_NAME, init = "userJobInit", destroy = "userJobDestroy")
    public ReturnT<String> userJobHandle(String param) throws Exception {
        log.info("输入参数: {}", param);
        log.info("[Bean模式]第[{}]次执行定时任务",count.incrementAndGet());
        return ReturnT.SUCCESS;
    }

    /**
     * 用户定时任务初始化方法，仅在执行器第一次执行前执行
     * @return
     */
    public ReturnT<String> userJobInit() {
        log.info("[user-job-init]");
        return ReturnT.SUCCESS;
    }

    /**
     * 用户定时任务销毁方法，仅在执行器销毁时执行
     * @return
     */
    public ReturnT<String> userJobDestroy() {
        log.info("[user-job-destroy]");
        return ReturnT.SUCCESS;
    }

}
