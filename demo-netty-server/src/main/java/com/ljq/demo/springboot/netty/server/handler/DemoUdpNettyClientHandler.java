package com.ljq.demo.springboot.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description: UDP Netty 客户端事务处理器
 * @Author: junqiang.lu
 * @Date: 2023/8/25
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class DemoUdpNettyClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        // 读取数据
        ByteBuf byteBuf = datagramPacket.content();
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        log.info("receive server msg:" + new String(bytes));
    }
}
