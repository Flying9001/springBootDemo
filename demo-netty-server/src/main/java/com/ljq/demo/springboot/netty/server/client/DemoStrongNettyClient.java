package com.ljq.demo.springboot.netty.server.client;

import com.ljq.demo.springboot.netty.server.handler.DemoStrongNettyClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @Description: 强化版 Netty 客户端模拟器
 * @Author: junqiang.lu
 * @Date: 2023/8/23
 */
@Slf4j
public class DemoStrongNettyClient {

    private final String host;

    private final int port;

    private final EventLoopGroup mainGroup;

    private final Bootstrap bootstrap;

    private Channel channel;

    public DemoStrongNettyClient(String host, int port) {
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
                                // 消息解码: 读取消息头和消息体
                                .addLast(new LengthFieldBasedFrameDecoder(4096,0,4,0,4))
                                // 消息编码: 将消息封装为消息头和消息体,在响应字节数据前面添加消息体长度
                                .addLast(new LengthFieldPrepender(4))
                                // 字符串编解码器
                                .addLast(new StringEncoder())
                                .addLast(new StringDecoder())
                                .addLast(new DemoStrongNettyClientHandler());
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
        channel.writeAndFlush(message);
    }

    public void close() throws InterruptedException {
        log.info("关闭客户端");
        mainGroup.shutdownGracefully();
    }


    public static void main(String[] args) throws InterruptedException {
        String serverHost = "127.0.0.1";
        int serverPort = 9125;
        String message = "abcde啊哈哈哈";
        DemoStrongNettyClient nettyClient = new DemoStrongNettyClient(serverHost, serverPort);
        nettyClient.connect();
        for (int i = 0; i < 100000; i++) {
            nettyClient.sendMessage(message + i);
        }
        log.info("--------开始休眠 5 秒------------");
        Thread.sleep(5000L);
        log.info("--------休眠 5 秒结束------------");
        for (int i = 0; i < 5; i++) {
            nettyClient.sendMessage(i + message);
            Thread.sleep(100L);
        }
        nettyClient.close();
    }

}
