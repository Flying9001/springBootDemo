package com.ljq.demo.springboot.netty.server.client;

import com.ljq.demo.springboot.netty.server.handler.DemoNettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @Description: netty 服务端
 * @Author: junqiang.lu
 * @Date: 2023/8/22
 */
@Slf4j
public class DemoNettyServer {

    private static final int NETTY_PORT = 9121;

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
        try {
            bootstrap.group(mainGroup, workGroup)
                    // 指定 nio 通道
                    .channel(NioServerSocketChannel.class)
                    // 指定 socket 地址和端口
                    .localAddress(new InetSocketAddress(NETTY_PORT))
                    // 添加子通道 handler
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast(new DemoNettyServerHandler());
                        }
                    });
            // 异步绑定服务器，调用sync（）方法阻塞等待直到绑定完成
            ChannelFuture channelFuture = bootstrap.bind().sync();
            log.info("netty server start");
            // 获取Channel的CloseFuture，并且阻塞当前线程直到它完成
            channelFuture.channel().closeFuture().sync();
        } finally {
            // 关闭 EventLoopGroup，释放资源
            mainGroup.shutdownGracefully().sync();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        DemoNettyServer nettyServer = new DemoNettyServer();
        nettyServer.start();
    }

}
