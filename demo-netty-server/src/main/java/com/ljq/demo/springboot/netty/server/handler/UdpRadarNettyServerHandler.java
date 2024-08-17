package com.ljq.demo.springboot.netty.server.handler;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.RandomUtil;
import com.ljq.demo.springboot.netty.server.model.response.UdpRadarHeader;
import com.ljq.demo.springboot.netty.server.util.UdpRadarServerUtil;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description: udp 雷达服务端处理器
 * @Author: Mr.lu
 * @Date: 2024/8/15
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class UdpRadarNettyServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    /**
     * 工作线程池
     */
    private final ExecutorService executorService = new ThreadPoolExecutor(4, 8, 60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(10000), new DefaultThreadFactory("UDP-Radar-work-pool"),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket packet) throws Exception {
        // 读取数据
        // 十六进制数据字符串
        String hexMsg = ByteBufUtil.hexDump(packet.content()).toUpperCase();
        // 校验数据
        if (!UdpRadarServerUtil.validate(hexMsg)) {
            log.info("invalid data");
            return;
        }
        InetSocketAddress clientAddress = packet.sender();
        // 业务处理
        executorService.execute(() -> {
            // 解析请求头
            UdpRadarHeader header = UdpRadarServerUtil.parseHeader(hexMsg);
            log.info("UDP Radar server receive msg,client: {},cmd:{},operate:{}", clientAddress.getHostString(),
                    header.getCmd(), header.getOperate());
            // 根据命令执行对应的业务
            // TODO  业务处理,返回响应客户端数据
            String responseHexMsg = demoService(hexMsg);

            // 回复客户端
            if (Objects.equals(11, header.getOperate())) {
                // 发送数据
                UdpRadarServerUtil.sendData(clientAddress.getHostString(), clientAddress.getPort(), responseHexMsg);
            }
        });

    }


    /**
     * 示例业务方法
     *
     * @param hexMsg
     * @return
     */
    private String demoService(String hexMsg) {
        // TODO 业务处理

        UdpRadarHeader header = UdpRadarServerUtil.parseHeader(hexMsg);

        // 回复内容
        UdpRadarHeader responseHeader = new UdpRadarHeader();
        responseHeader.setCmd(header.getCmd());
        responseHeader.setOperate(21);
        responseHeader.setLength(UdpRadarServerUtil.HEADER_BYTE_LENGTH + 5);
        responseHeader.setMsgNo(header.getMsgNo());
        // 编码响应数据
        StringBuilder responseHexMsgBuilder = new StringBuilder();
        responseHexMsgBuilder.append(UdpRadarServerUtil.getHexData(responseHeader));
        responseHexMsgBuilder.append(String.format("%02x", RandomUtil.randomInt(0,128)));
        responseHexMsgBuilder.append(HexUtil.encodeHexStr(RandomUtil.randomString(4)));
        return responseHexMsgBuilder.toString();
    }

}
