package com.ljq.demo.springboot.quartz.group.common.config;

import com.ljq.demo.springboot.quartz.group.DemoScheduleQuartzGroupApplication;
import com.ljq.demo.springboot.quartz.group.job.UserJob;
import com.ljq.demo.springboot.quartz.group.job.UserJob2;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.Sets;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 定时任务配置测试类
 */
@SpringBootTest(classes = DemoScheduleQuartzGroupApplication.class)
class QuartzScheduleConfigTest {


    @Autowired
    private Scheduler scheduler;

    private static final String USER_JOB_DETAIL_NAME = "userJob001";
    private static final String USER_JOB_TRIGGER_NAME = "userJobTrigger001";

    private static final String USER_JOB_2_DETAIL_NAME = "userJob002";
    private static final String USER_JOB_2_TRIGGER_NAME = "userJob2Trigger002";

    private static final String USER_JOB_2_CRON = "0/10 * * * * ? *";

    /**
     * 手动添加用户定时任务配置
     *
     * @throws SchedulerException
     */
    @Test
    public void addUserJobConfig() throws SchedulerException {
        // 创建 JobDetail
        JobDetail jobDetail = JobBuilder.newJob(UserJob.class)
                .withIdentity(USER_JOB_DETAIL_NAME)
                .storeDurably()
                .build();
        // 创建 Trigger
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5)
                .repeatForever();
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(USER_JOB_TRIGGER_NAME)
                .withSchedule(scheduleBuilder)
                .build();
        // 添加调度任务
        // 不覆盖已有任务
//        scheduler.scheduleJob(jobDetail, trigger);

        // 覆盖已有任务
        scheduler.scheduleJob(jobDetail, Sets.newSet(trigger), true);
    }


    /**
     * 手动创建用户2定时任务
     *
     * @throws SchedulerException
     */
    @Test
    public void addUserJob2Config() throws SchedulerException {
        // 创建 JobDetail
        JobDetail jobDetail = JobBuilder.newJob(UserJob2.class)
                .withIdentity(USER_JOB_2_DETAIL_NAME)
                .storeDurably()
                .build();
        // 创建 Trigger
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(USER_JOB_2_CRON);
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(USER_JOB_2_TRIGGER_NAME)
                .withSchedule(scheduleBuilder)
                .build();
        // 添加调度任务
        // 不覆盖已有任务
//        scheduler.scheduleJob(jobDetail, trigger);
        // 覆盖已有任务
        scheduler.scheduleJob(jobDetail, Sets.newSet(trigger), true);

    }







}