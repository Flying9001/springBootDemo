package com.ljq.demo.springboot.netty.server.model.response;

import cn.hutool.core.util.HexUtil;
import org.junit.jupiter.api.Test;

import java.util.Locale;

class UdpRadarHeaderTest {

    @Test
    void testToString() {
        String flag = "9527";
        System.out.println(HexUtil.encodeHexStr(flag).toUpperCase(Locale.ROOT));
        System.out.println(String.format("%04x", 1000));
        System.out.println(HexUtil.encodeHexStr("1000"));

    }
}