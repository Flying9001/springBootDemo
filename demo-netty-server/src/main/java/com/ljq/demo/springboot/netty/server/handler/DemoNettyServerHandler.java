package com.ljq.demo.springboot.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description: Netty 服务处理器
 * @Author: junqiang.lu
 * @Date: 2023/8/18
 */
@Slf4j
@Component
// 标记该类实例可以被多个 channel 共享
@ChannelHandler.Sharable
public class DemoNettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 每个传入的消息都会调用该方法
     *
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] body = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(body);
        log.info("服务端接收到数据:{}", new String(body));
        byte[] responseBytes = "hi,客户端,我是服务端".getBytes();
        ctx.writeAndFlush(Unpooled.wrappedBuffer(responseBytes));
    }

    /**
     * 在读取期间，有异常抛出时会调用
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 打印异常栈跟踪
        log.error("netty server error",cause);
        //关闭该channel
        ctx.close();
    }


}
