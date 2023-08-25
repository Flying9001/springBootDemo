package com.ljq.demo.springboot.netty.server.client;

import cn.hutool.core.util.RandomUtil;
import com.ljq.demo.springboot.netty.server.handler.DemoUdpNettyClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @Description: UDP netty 客户端
 * @Author: junqiang.lu
 * @Date: 2023/8/25
 */
@Slf4j
public class DemoUdpNettyClient {

    private final String serverHost;

    private final int serverPort;

    private final int clientPort;

    private final EventLoopGroup mainGroup;

    private final Bootstrap bootstrap;

    private Channel channel;

    public DemoUdpNettyClient(String serverHost, int serverPort, int clientPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
        this.clientPort = clientPort;
        this.mainGroup = new NioEventLoopGroup();
        this.bootstrap = new Bootstrap();

    }

    public Channel getChannel() {
        return this.channel;
    }

    /**
     * 创建连接
     */
    public void connect() throws InterruptedException {
        bootstrap.group(mainGroup)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .localAddress(clientPort)
                .handler(new DemoUdpNettyClientHandler());
        ChannelFuture future = bootstrap.bind().sync();
        this.channel = future.channel();
    }

    /**
     * 发送消息
     *
     * @param message
     */
    public void sendMessage(String message) {
        log.info("客户端待发送消息:{}", message);
        Channel channel = this.getChannel();
        byte[] resBytes = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(Unpooled.copiedBuffer(resBytes), new InetSocketAddress(serverHost, serverPort));
        channel.writeAndFlush(sendPacket);
    }

    public void close() throws InterruptedException {
        log.info("关闭客户端");
        mainGroup.shutdownGracefully();
    }




    public static void main(String[] args) throws InterruptedException {
        String serverHost = "127.0.0.1";
        int serverPort = 9130;
        int clientPort = 9131;
        String message = RandomUtil.randomString(1024);
        DemoUdpNettyClient nettyClient = new DemoUdpNettyClient(serverHost, serverPort, clientPort);
        nettyClient.connect();
        for (int i = 0; i < 10000; i++) {
            nettyClient.sendMessage(message + i);
        }
        log.info("--------开始休眠 5 秒------------");
        Thread.sleep(5000L);
        log.info("--------休眠 5 秒结束------------");
        for (int i = 0; i < 5; i++) {
            nettyClient.sendMessage(i + message);
            Thread.sleep(100L);
        }
        Thread.sleep(5000L);
        nettyClient.close();
    }

}
