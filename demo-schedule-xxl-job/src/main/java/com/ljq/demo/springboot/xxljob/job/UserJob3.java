package com.ljq.demo.springboot.xxljob.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: GLUE-Java 测试2
 * @Author: junqiang.lu
 * @Date: 2020/11/29
 */
@Slf4j
public class UserJob3 {

    public ReturnT<String> test(String param) {
        int a = 10;
        int b = 20;
        int sum = a + b;
        log.info("本地日志打印:{}",sum);
        XxlJobLogger.log("计算结果:{}" ,sum);

        return ReturnT.SUCCESS;
    }


}
