package com.ljq.demo.springboot.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @Description: UDP Netty 服务端事务处理器
 * @Author: junqiang.lu
 * @Date: 2023/8/25
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class DemoUdpNettyServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    /**
     * 工作线程池
     */
    private final ExecutorService executorService = new ThreadPoolExecutor(4, 8, 60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(10000), new DefaultThreadFactory("UDP-netty-work-pool"),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
        ByteBuf byteBuf = packet.content();
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        // 异步处理业务
        executorService.execute(() -> {
            // 读取数据
            log.info("UDP server receive client msg:" + new String(bytes));
            try {
                // 添加休眠，模拟业务处理
                Thread.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("UDP server read message error", cause);
    }
}
