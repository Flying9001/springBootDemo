package com.ljq.demo.springboot.netty.server.init;

import com.ljq.demo.springboot.netty.server.handler.DemoNettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

/**
 * @Description: 初始化 netty 服务端
 * @Author: junqiang.lu
 * @Date: 2023/8/18
 */
@Slf4j
@Component
public class InitNettyServer implements ApplicationRunner {


    @Value("${netty.port:9120}")
    private Integer nettyPort;


    @Resource
    private DemoNettyServerHandler nettyServerHandler;

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
        EventLoopGroup mainGroup = new NioEventLoopGroup(1);
        // 工作线程池
        EventLoopGroup workGroup = new NioEventLoopGroup(4);
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(mainGroup, workGroup)
                // 指定 nio 通道
                .channel(NioServerSocketChannel.class)
                // 指定 socket 地址和端口
                .localAddress(new InetSocketAddress(nettyPort))
                // 添加子通道 handler
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast(nettyServerHandler);
                    }
                });
        // 异步绑定服务器，调用sync()方法阻塞等待直到绑定完成
        bootstrap.bind().sync();
        log.info("---------- [init] netty server start,port:{} ----------", nettyPort);
    }


}
