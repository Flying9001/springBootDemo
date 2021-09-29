package com.ljq.demo.springboot.service.impl;

import com.ljq.demo.springboot.common.util.SimpleHttpClientUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class RestUserServiceImplTest {

    @Test
    public void distributedLock() throws IOException {
        String apiAddress = "http://127.0.0.1:8088/api/rest/user/lock";
        SimpleHttpClientUtil.doPost(apiAddress, null, (Map)null, null);
    }
}