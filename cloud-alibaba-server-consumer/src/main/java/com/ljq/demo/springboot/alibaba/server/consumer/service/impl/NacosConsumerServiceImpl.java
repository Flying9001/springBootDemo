package com.ljq.demo.springboot.alibaba.server.consumer.service.impl;

import com.ljq.demo.springboot.alibaba.server.consumer.common.constant.NacosConst;
import com.ljq.demo.springboot.alibaba.server.consumer.model.param.HelloParam;
import com.ljq.demo.springboot.alibaba.server.consumer.service.NacosConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Nacos 服务消费者业务实现类
 * @Author: junqiang.lu
 * @Date: 2020/12/2
 */
@Slf4j
@Service("nacosConsumerService")
public class NacosConsumerServiceImpl implements NacosConsumerService {

    private static final String HELLO_URL = NacosConst.NACOS_SERVER_PROVIDER_PATH + "/api/nacos/hello";
    private static final String REPLAY_URL = NacosConst.NACOS_SERVER_PROVIDER_PATH +"/api/nacos/replay";

    @Resource
    private RestTemplate template;

    /**
     * hello
     *
     * @param helloParam
     * @return
     */
    @Override
    public String hello(HelloParam helloParam) {
        Map<String, Object> param = new HashMap<>(16);
        param.put("name", helloParam.getName());
        String url = convertGetUrl(NacosConst.NACOS_SERVER_PROVIDER_NAME, HELLO_URL, param);
        ResponseEntity<String> response = template.getForEntity(url, String.class);
        log.info("response: {}", response.getBody());
        return response.getBody();
    }

    /**
     * 回复
     *
     * @param helloParam
     * @return
     */
    @Override
    public String replay(HelloParam helloParam) {
        String url = convertGetUrl(NacosConst.NACOS_SERVER_PROVIDER_NAME, REPLAY_URL, null);
        ResponseEntity<String> response = template.postForEntity(url, helloParam, String.class);
        log.info("response: {}", response.getBody());
        return response.getBody();
    }

    /**
     * 组装 Get 请求 URL
     * @param serverName 服务名称
     * @param apiAddress 接口地址
     * @param param 请求参数
     * @return
     */
    private String convertGetUrl(String serverName, String apiAddress, Map<String, Object> param) {
        StringBuilder urlBuilder = new StringBuilder("http://");
        urlBuilder.append(serverName).append(apiAddress);
        if (!CollectionUtils.isEmpty(param)) {
            urlBuilder.append("?");
            param.forEach((k, v) -> {
                urlBuilder.append(k).append("=").append(v).append("&");
            });
            urlBuilder.deleteCharAt(urlBuilder.lastIndexOf("&"));
        }
        return urlBuilder.toString();
    }


}
