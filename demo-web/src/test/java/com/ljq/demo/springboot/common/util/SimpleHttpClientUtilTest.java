package com.ljq.demo.springboot.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class SimpleHttpClientUtilTest {

    private static String API_HOST = "http://192.168.100.100:8848";
    private static String API_PATH_USER_LIST = "/api/user/list";
    private static String API_PATH_USER_LISTS = "/api/user/lists";
    private static String API_PATH_COMMON_UPLOAD = "/api/demo/common/upload";


    @Test
    public void doGet() throws IOException {
        String paramsStr = "?demoKey=demoValue";
        Map<String, String> paramsMap = new HashMap<>(16);
        paramsMap.put("demoMapKey", "demoMapValue");
        paramsMap.put("zhTest", "德玛西亚");

        HttpResponse httpResponse = SimpleHttpClientUtil.doGet(API_HOST, API_PATH_USER_LIST + paramsStr, paramsMap);

        printHttpResponse(httpResponse);
    }

    @Test
    public void doPostJson() throws IOException {
        Map<String, String> paramsMap = new HashMap<>(16);
        paramsMap.put("demoMapKey", "demoMapValue");
        paramsMap.put("zhTest", "德玛西亚");

        HttpResponse httpResponse = SimpleHttpClientUtil.doPost(API_HOST, API_PATH_USER_LIST, new ObjectMapper().writeValueAsString(paramsMap));

        printHttpResponse(httpResponse);

    }

    @Test
    public void doPostUrl() throws IOException {
        Map<String, String> paramsMap = new HashMap<>(16);
        paramsMap.put("demoMapKey", "demoMapValue");
        paramsMap.put("zhTest", "德玛西亚");

        HttpResponse httpResponse = SimpleHttpClientUtil.doPost(API_HOST, API_PATH_USER_LISTS, paramsMap);

        printHttpResponse(httpResponse);

    }

    @Test
    public void doPostMultipart() throws IOException {
        Map<String, String> paramsMap = new HashMap<>(16);
        paramsMap.put("demoMapKey", "demoMapValue");
        paramsMap.put("zhTest", "德玛西亚");
        Path path = Paths.get("F:\\download\\阿里巴巴Java开发手册（详尽版）.pdf");
        String name = "file";
        String fileOriginalName = "阿里巴巴Java开发手册（详尽版）.pdf";

        HttpResponse httpResponse = SimpleHttpClientUtil.doPost(API_HOST, API_PATH_COMMON_UPLOAD, paramsMap,
                Files.newInputStream(path),name, fileOriginalName);

        printHttpResponse(httpResponse);

    }


    /**
     * 打印 http 请求结果
     *
     * @param httpResponse
     * @throws IOException
     */
    public static void printHttpResponse(HttpResponse httpResponse) throws IOException {
        System.out.println("response Code: " + httpResponse.getStatusLine().getStatusCode());

        System.out.println(EntityUtils.toString(httpResponse.getEntity()));
    }


}