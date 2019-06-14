package com.ljq.demo.springboot.schedule.cronjob;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Component;

/**
 * @Description: 用户模块定时任务
 * @Author: junqiang.lu
 * @Date: 2019/6/14
 */
@Slf4j
@Component
public class UserSchedule {

    /**
     * fixedRate 测试
     * fixedRate: 间隔固定的时间(单位: 毫秒)进行任务执行,无论当前任务是否执行完成,
     *     都不会影响下一次的任务执行;
     *     如果多次任务累计没有执行完成,可能会出现内存溢出的问题(Out of memory exception)
     */
    @Scheduled(fixedRate = 5 * 1000)
    public void fixedRateDemo() {
        log.debug("{}","FixedRate Demo ----------");
    }

    /**
     * fixedDelay 测试
     * fixedDelay: 每次任务执行结束后间隔固定的时间(单位: 毫秒)进行下一次的任务
     */
    @Scheduled(fixedDelay = 5 * 1000)
    public void fixedDelayDemo() throws InterruptedException {
        Thread.sleep(1000);
        log.debug("{}", "FixedDelay Demo ++++++++++");
    }

    /**
     * cron 表达式测试
     * 注意: Spring schedule cron 表达式必须是 6 个字段,而非 7 个字段
     * 没有「年」这一项,如果使用 7 个字段的 cron 表达式,则会报错
     * spring cron 表达式参考: https://riptutorial.com/spring/example/21209/cron-expression
     */
    @Scheduled(cron = "*/5 * * * * ?")
    public void cronDemo() {
        String[] cron = {"*/5 * * * * ?", "*/5 * * * * ? *"};
        boolean cronFlag;
        for (int i = 0; i < cron.length; i++) {
            cronFlag = CronSequenceGenerator.isValidExpression(cron[i]);
            log.debug("cron: {}, cronFlag: {}", cron[i], cronFlag);
        }
        log.debug("{}","Cron Demo .........");
    }






}
