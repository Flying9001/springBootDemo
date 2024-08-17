package com.ljq.demo.springboot.netty.server.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.ljq.demo.springboot.netty.server.init.UdpRadarNettyServer;
import com.ljq.demo.springboot.netty.server.model.response.UdpRadarHeader;
import io.netty.buffer.Unpooled;
import io.netty.channel.socket.DatagramPacket;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Objects;

/**
 * @Description: udp 雷达服务端工具类
 * @Author: Mr.lu
 * @Date: 2024/8/15
 */
@Slf4j
public class UdpRadarServerUtil {

    /**
     * 请求头字节数
     */
    public static final int HEADER_BYTE_LENGTH = 9;

    /**
     * 请求头数据长度
     */
    public static final int HEADER_DATA_LENGTH = 18;

    /**
     * 消息最大长度
     */
    public static final int MSG_MAX_LENGTH = 1000;

    /**
     * 请求头标识
     */
    public static final String HEADER_FLAG = "39353237";

    private UdpRadarServerUtil() {
    }


    /**
     * 校验服务端接收到的数据是否合法
     *
     * @param hexMsg
     * @return
     */
    public static boolean validate(String hexMsg) {
        if (StrUtil.isBlank(hexMsg) || hexMsg.length() < HEADER_DATA_LENGTH
                || hexMsg.length() > MSG_MAX_LENGTH || !hexMsg.startsWith(HEADER_FLAG)) {
            return false;
        }
        return true;
    }

    /**
     * 解析请求头
     *
     * @param hexMsg
     * @return
     */
    public static UdpRadarHeader parseHeader(String hexMsg) {
        UdpRadarHeader header = new UdpRadarHeader();
        header.setFlag(HexUtil.decodeHexStr(hexMsg.substring(0, 8)));
        header.setCmd(HexUtil.hexToInt(hexMsg.substring(8, 10)));
        header.setOperate(HexUtil.hexToInt(hexMsg.substring(10, 12)));
        header.setLength(HexUtil.hexToInt(hexMsg.substring(12, 16)));
        header.setMsgNo(HexUtil.hexToInt(hexMsg.substring(16, 18)));
        return header;
    }


    /**
     * 生成发送数据(仅请求头)
     *
     * @param header
     * @return
     */
    public static String getHexData(UdpRadarHeader header) {
        if (Objects.isNull(header)) {
            return null;
        }
        StringBuilder dataBuilder = new StringBuilder();
        dataBuilder.append(HexUtil.encodeHexStr(header.getFlag()));
        dataBuilder.append(String.format("%02x", header.getCmd()));
        dataBuilder.append(String.format("%02x", header.getOperate()));
        dataBuilder.append(String.format("%04x", header.getLength()));
        dataBuilder.append(String.format("%02x", header.getMsgNo()));
        return dataBuilder.toString();
    }


    /**
     * 生成发送数据(包含业务数据)
     *
     * @param header
     * @param data
     * @return
     */
    public static String getHexData(UdpRadarHeader header, String data) {
        if (Objects.isNull(header)) {
            return null;
        }
        StringBuilder dataBuilder = new StringBuilder(getHexData(header));
        if (StrUtil.isNotBlank(data)) {
            dataBuilder.append(HexUtil.encodeHexStr(data));
        }
        return dataBuilder.toString();
    }


    /**
     * 向客户端发送数据
     *
     * @param clientIp
     * @param clientPort
     * @param hexMsg
     */
    public static void sendData(String clientIp, int clientPort, String hexMsg) {
        // 获取UDP服务端
        UdpRadarNettyServer udpRadarNettyServer = SpringUtil.getBean(UdpRadarNettyServer.class);
        if (Objects.isNull(udpRadarNettyServer)) {
            log.warn("UDP Radar Server is not running");
            return;
        }
        DatagramPacket sendPacket = new DatagramPacket(Unpooled.copiedBuffer(Convert.hexToBytes(hexMsg)),
                new InetSocketAddress(clientIp, clientPort));
        udpRadarNettyServer.getChannel().writeAndFlush(sendPacket);
    }

    /**
     * 向客户端发送数据
     *
     * @param clientId
     * @param clientPort
     * @param header
     * @param data
     */
    public static void sendData(String clientId, int clientPort,UdpRadarHeader header, String data) {
        // 组装数据
        String hexMsg = getHexData(header, data);
        // 发送数据
        sendData(clientId, clientPort, hexMsg);
    }



}
