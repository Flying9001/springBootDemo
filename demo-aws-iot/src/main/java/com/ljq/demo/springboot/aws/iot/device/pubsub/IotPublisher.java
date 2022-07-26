package com.ljq.demo.springboot.aws.iot.device.pubsub;

import com.amazonaws.services.iot.client.AWSIotMessage;
import com.amazonaws.services.iot.client.AWSIotQos;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: AWS iot 消息推送类
 * @Author: junqiang.lu
 * @Date: 2022/7/21
 */
@Slf4j
public class IotPublisher extends AWSIotMessage {

    public IotPublisher(String topic, AWSIotQos qos, byte[] payload) {
        super(topic, qos, payload);
    }

    @Override
    public void onSuccess() {
        log.info("[MQTT消息推送-Success]topic: {},data: {}",topic, getStringPayload());
        // 业务处理
    }

    @Override
    public void onFailure() {
        log.info("[MQTT消息推送-Error]topic: {},data: {}",topic, getStringPayload());
        // 业务处理
    }

    @Override
    public void onTimeout() {
        log.info("[MQTT消息推送-time out]topic: {},data: {}",topic, getStringPayload());
        // 业务处理
    }

}
