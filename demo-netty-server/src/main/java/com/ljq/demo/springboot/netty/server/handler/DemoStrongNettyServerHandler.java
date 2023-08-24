package com.ljq.demo.springboot.netty.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 强化版 netty 服务端事务处理器
 * @Author: junqiang.lu
 * @Date: 2023/8/23
 */
@Slf4j
@Component
// 标记该类实例可以被多个 channel 共享
@ChannelHandler.Sharable
public class DemoStrongNettyServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 工作线程池
     */
    private final ExecutorService executorService = new ThreadPoolExecutor(4, 8, 60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(10000), new DefaultThreadFactory("strong-netty-work-pool"),
            new ThreadPoolExecutor.CallerRunsPolicy());


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        // 打印接收到的消息(观察 netty work 线程)
        log.info("strong netty server received message: {}", s);
        executorService.execute(() -> {
            // 异步处理业务(观察线程池线程)
            log.info("consume message async: {}", s);
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                log.error("work thread sleep error.", e);
            }
        });
    }


}
