package com.ljq.demo.springboot.netty.server.client;

import com.ljq.demo.springboot.netty.server.handler.DemoNettyClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * @Description: Netty 客户端
 * @Author: junqiang.lu
 * @Date: 2023/8/22
 */
@Slf4j
public class DemoNettyClient {

    private final String host;

    private final int port;

    private final EventLoopGroup mainGroup;

    private final Bootstrap bootstrap;

    private Channel channel;

    public DemoNettyClient(String host, int port) {
        this.host = host;
        this.port = port;
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
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(host, port))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast(new StringEncoder())
                                .addLast(new ByteArrayEncoder())
                                .addLast(new DemoNettyClientHandler());
                    }
                });
        ChannelFuture future = bootstrap.connect().sync();
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
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        ByteBuf byteBuf = Unpooled.wrappedBuffer(bytes);
        channel.writeAndFlush(byteBuf);
    }

    public void close() throws InterruptedException {
        log.info("关闭客户端");
        mainGroup.shutdownGracefully().sync();
    }


    public static void main(String[] args) throws InterruptedException {
        String serverHost = "127.0.0.1";
        int serverPort = 9120;
        String message = "ahahaha啊哈哈哈啊哈";
        DemoNettyClient nettyClient = new DemoNettyClient(serverHost, serverPort);
        nettyClient.connect();
        for (int i = 0; i < 10; i++) {
            nettyClient.sendMessage(message + i);
        }
        log.info("--------开始休眠 5 秒------------");
        Thread.sleep(5000L);
        for (int i = 0; i < 5; i++) {
            nettyClient.sendMessage(i + message);
            Thread.sleep(100L);
        }
        nettyClient.close();
    }


}
