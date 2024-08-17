package com.ljq.demo.springboot.netty.server.util;

import cn.hutool.core.util.RandomUtil;
import com.ljq.demo.springboot.netty.server.model.response.UdpRadarHeader;
import org.junit.jupiter.api.Test;

class UdpRadarServerUtilTest {

    @Test
    void getHexData() {
        UdpRadarHeader header = new UdpRadarHeader();
        header.setCmd(RandomUtil.randomInt(1,255));
        header.setOperate(11);
        header.setLength(18);
        header.setMsgNo(RandomUtil.randomInt(1,255));
        String hexData = UdpRadarServerUtil.getHexData(header);
        System.out.println(hexData);

    }
}