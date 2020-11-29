package com.ljq.demo.springboot.xxljob.common.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: xxl-job 配置信息
 * @Author: junqiang.lu
 * @Date: 2020/11/26
 */
@Getter
@Configuration
public class XxlJobConfig {

    /**
     * 调度中心部署地址，选填，可以多个地址，使用逗号分隔，为空则关闭自动注册
     */
    @Value("${xxl.job.admin.address}")
    private String adminAddress;
    /**
     * 执行器 App 名称，选填，执行器心跳注册分组依据，为空则关闭自动注册
     */
    @Value("${xxl.job.executor.appName}")
    private String appName;
    /**
     * 执行器 ip，选填，为空则自动获取 ip，多网卡可手动指定 ip，该 ip 不会绑定 host，仅作通讯使用；
     * 地址信息用于"执行器注册"和"调度中心请求并触发任务"
     */
    @Value("${xxl.job.executor.ip}")
    private String ip;
    /**
     * 执行器端口，选填，默认 9999
     */
    @Value("${xxl.job.executor.port}")
    private Integer port;
    /**
     * 执行器运行日志文件储存路径，选填
     */
    @Value("${xxl.job.executor.logPath}")
    private String logPath;
    /**
     * 执行器日志文件保留天数，至少为 7，选填，默认 -1，不清理
     */
    @Value("${xxl.job.executor.logRetentionDays}")
    private Integer logRetentionDays;
    /**
     * 执行器通讯认证 Token，选填
     */
    @Value("${xxl.job.accessToken}")
    private String accessToken;


    /**
     * 创建 xxl-job 执行器
     *
     * @return
     */
    @Bean
    public XxlJobSpringExecutor xxlJobSpringExecutor(){
        XxlJobSpringExecutor executor = new XxlJobSpringExecutor();
        executor.setAdminAddresses(adminAddress);
        executor.setAppname(appName);
        executor.setIp(ip);
        executor.setPort(port);
        executor.setLogPath(logPath);
        executor.setLogRetentionDays(logRetentionDays);
        executor.setAccessToken(accessToken);
        return executor;
    }


}
