package com.ljq.demo.springboot.netty.server.init;

import com.ljq.demo.springboot.netty.server.handler.UdpRadarNettyServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

/**
 * @Description: 初始化雷达 udp 服务
 * @Author: Mr.lu
 * @Date: 2024/8/15
 */
@Slf4j
@Component
public class UdpRadarNettyServer implements ApplicationRunner {

    @Value("${netty.portUdpRadar:9135}")
    private Integer nettyPort;

    @Resource
    private UdpRadarNettyServerHandler udpRadarNettyServerHandler;

    /**
     * 通道
     */
    private Channel channel;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.start();
    }

    /**
     * 启动服务
     *
     * @throws InterruptedException
     */
    public void start() throws InterruptedException {
        // 连接管理线程池
        EventLoopGroup mainGroup = new NioEventLoopGroup(2);
        EventLoopGroup workGroup = new NioEventLoopGroup(8);
        // 工作线程池
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(mainGroup)
                // 指定 nio 通道，支持 UDP
                .channel(NioDatagramChannel.class)
                // 广播模式
                .option(ChannelOption.SO_BROADCAST, true)
                // 设置读取缓冲区大小为 10M
                .option(ChannelOption.SO_RCVBUF, 1024 * 1024 * 10)
                // 设置发送缓冲区大小为 10M
                .option(ChannelOption.SO_SNDBUF, 1024 * 1024 * 10)
                // 线程池复用缓冲区
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                // 指定 socket 地址和端口
                .localAddress(new InetSocketAddress(nettyPort))
                // 添加通道 handler
                .handler(new ChannelInitializer<NioDatagramChannel>() {
                    @Override
                    protected void initChannel(NioDatagramChannel nioDatagramChannel) throws Exception {
                        nioDatagramChannel.pipeline()
                                // 指定工作线程，提高并发性能
                                .addLast(workGroup, udpRadarNettyServerHandler);
                    }
                });
        // 异步绑定服务器，调用sync()方法阻塞等待直到绑定完成
        ChannelFuture future = bootstrap.bind().sync();
        this.channel = future.channel();
        log.info("---------- [init] UDP Radar netty server start, port:{} ----------", nettyPort);
    }

    public Channel getChannel() {
        return this.channel;
    }

}
