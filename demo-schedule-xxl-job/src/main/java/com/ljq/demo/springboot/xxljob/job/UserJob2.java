package com.ljq.demo.springboot.xxljob.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 用户定时任务负载2(GLUE-Java 模式)
 * @Author: junqiang.lu
 * @Date: 2020/11/27
 */
public class UserJob2 extends IJobHandler {

    private final Logger log = LoggerFactory.getLogger(UserJob2.class);

    private final AtomicInteger count = new AtomicInteger();


    @Override
    public ReturnT<String> execute(String param) throws Exception {
        List<String> list = new ArrayList<>();

        log.info("入参: {}", param);
        XxlJobLogger.log("入参: {}", param);
        XxlJobLogger.log("[user-job-GLUE]方式第{}次执行", count.incrementAndGet());
        return ReturnT.SUCCESS;
    }
}
