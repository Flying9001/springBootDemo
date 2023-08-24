package com.ljq.demo.springboot.netty.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description: 强化版 netty 客户端事务处理器
 * @Author: junqiang.lu
 * @Date: 2023/8/23
 */
@Slf4j
@Component
// 标记该类实例可以被多个 channel 共享
@ChannelHandler.Sharable
public class DemoStrongNettyClientHandler extends SimpleChannelInboundHandler<String> {


    /**
     * 接收消息
     *
     * @param channelHandlerContext
     * @param s
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        log.info("strong netty client receive server message: {}", s);
    }

    /**
     * 通道激活，创建连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("strong netty client connect to server.");
    }
}
