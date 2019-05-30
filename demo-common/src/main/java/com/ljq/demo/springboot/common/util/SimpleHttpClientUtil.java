package com.ljq.demo.springboot.common.util;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Description: 网络请求工具类--Powered by Apache HttpClient
 * @Author: junqiang.lu
 * @Date: 2019/5/16
 */
public class SimpleHttpClientUtil implements Serializable {

    private static final long serialVersionUID = 4634161754990919271L;

    private static volatile HttpClient httpClient;

    private SimpleHttpClientUtil(){}


    /**
     * GET 方式请求
     * 参数通过 url 拼接
     *
     * @param host 请求地址
     * @param path 接口路径
     * @param paramsMap 请求参数
     * @return
     * @throws IOException
     */
    public static HttpResponse doGet(String host, String path, Map<String, String> paramsMap) throws IOException {
        initHttpClient();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(10000)
                .setConnectTimeout(5000)
                .build();
        HttpGet httpGet = new HttpGet(getRequestUrl(host, path, paramsMap));
        httpGet.setConfig(requestConfig);
        httpGet.setHeader(HTTP.CONTENT_TYPE,ContentType.create(ContentType.APPLICATION_FORM_URLENCODED
                .getMimeType(),Consts.UTF_8).toString());

        return httpClient.execute(httpGet);
    }

    /**
     * POST 方式请求
     * 参数通过 url 拼接
     *
     * @param host 请求地址
     * @param path 接口路径
     * @param paramsMap 请求参数
     * @return
     * @throws IOException
     */
    public static HttpResponse doPost(String host, String path, Map<String, String> paramsMap) throws IOException {
        initHttpClient();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(10000)
                .setConnectTimeout(5000)
                .build();
        HttpPost httpPost = new HttpPost(getRequestUrl(host, path, paramsMap));
        httpPost.setConfig(requestConfig);
        httpPost.setHeader(HTTP.CONTENT_TYPE,ContentType.create(ContentType.APPLICATION_FORM_URLENCODED
                .getMimeType(),Consts.UTF_8).toString());

        return httpClient.execute(httpPost);
    }

    /**
     * POST 方式请求
     * 参数通过 Body 传送,JSON 格式
     *
     * @param host 请求地址
     * @param path 接口路径
     * @param jsonParams 请求参数(json 字符串)
     * @return
     */
    public static HttpResponse doPost(String host, String path, String jsonParams) throws IOException {
        initHttpClient();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(10000)
                .setConnectTimeout(5000)
                .build();
        String uri = host;
        if (path != null && path.length() > 0) {
            uri += path;
        }
        HttpPost httpPost = new HttpPost(uri);
        StringEntity stringentity = new StringEntity(jsonParams, ContentType.APPLICATION_JSON);
        httpPost.setEntity(stringentity);
        httpPost.setConfig(requestConfig);
        httpPost.addHeader(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());

        return httpClient.execute(httpPost);
    }

    /**
     * POST 方式请求
     * (允许一个字段多个值)
     *
     * @param host
     * @param path
     * @param nameValuePairList
     * @return
     */
    public static HttpResponse doPost(String host, String path, List<NameValuePair> nameValuePairList) throws IOException {
        initHttpClient();
        String uri = host;
        if (path != null && path.length() > 0) {
            uri += path;
        }
        HttpPost httpPost = new HttpPost(uri);httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList));
        httpPost.setHeader(HTTP.CONTENT_TYPE, ContentType.create(ContentType.APPLICATION_FORM_URLENCODED.getMimeType(),
                Consts.UTF_8).toString());

        return httpClient.execute(httpPost);
    }

    /**
     * POST 方式请求
     * 文件上传
     *
     * @param host 请求地址
     * @param path 接口路径
     * @param paramsMap 请求参数
     * @param fileInputStream 待上传文件流
     * @param name 文件对应字段名
     * @param fileOriginalName 原始文件名
     * @return
     */
    public static HttpResponse doPost(String host, String path, Map<String, String> paramsMap,
                                      InputStream fileInputStream, String name, String fileOriginalName) throws IOException {
        initHttpClient();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(10000)
                .setConnectTimeout(5000)
                .build();
        String uri = host;
        if (path != null && path.length() > 0) {
            uri += path;
        }
        HttpPost httpPost = new HttpPost(uri);
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        // 解决中文文件名乱码问题
        entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        entityBuilder.setCharset(Consts.UTF_8);
        ContentType contentType = ContentType.create(ContentType.TEXT_PLAIN.getMimeType(), Consts.UTF_8);
        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            entityBuilder.addTextBody(entry.getKey(), entry.getValue(), contentType);
        }
        if (fileInputStream != null && name != null && fileOriginalName != null) {
            entityBuilder.addBinaryBody(name, fileInputStream, ContentType.DEFAULT_BINARY, fileOriginalName);
        }
        httpPost.setEntity(entityBuilder.build());
        httpPost.setConfig(requestConfig);

        return httpClient.execute(httpPost);
    }



    /**
     * 初始化 httpClient
     * @return
     */
    private static HttpClient initHttpClient() {
        if  (httpClient == null) {
            synchronized (SimpleHttpClientUtil.class) {
                if (httpClient == null) {
                    httpClient = HttpClients.createDefault();
                }
            }
        }
        return httpClient;
    }

    /**
     * 获取完整请求地址(包含参数)
     * 参数拼接在 url 中
     *
     * @param host 请求地址
     * @param path 接口路径
     * @param paramsMap 请求参数
     * @return
     */
    private static String getRequestUrl(String host, String path, Map<String, String> paramsMap) {
        String uri = host;
        if (path != null && path.length() > 0) {
            uri += path;
        }
        StringBuilder reqUrl = new StringBuilder(uri);
        if (paramsMap != null && !paramsMap.isEmpty()) {
            StringBuilder params = new StringBuilder();
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                params.append("&" + entry.getKey() + "=" + entry.getValue());
            }
            String paramConnector = "?";
            if (!host.contains(paramConnector) && !path.contains(paramConnector)) {
                reqUrl.append(paramConnector);
                reqUrl.append(params.toString().substring(1));
            } else {
                reqUrl.append(params.toString());
            }
        }

        return reqUrl.toString();
    }




}
