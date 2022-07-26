package com.ljq.demo.springboot.aws.iot.device.pubsub;

import com.amazonaws.services.iot.client.AWSIotMessage;
import com.amazonaws.services.iot.client.AWSIotQos;
import com.amazonaws.services.iot.client.AWSIotTopic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description: AWS iot 设备上下线事件监听类
 * @Author: junqiang.lu
 * @Date: 2022/7/21
 */
@Slf4j
@Component
public class IotConnectEventListener extends AWSIotTopic {

    private static final String TOPIC = "hello/demo/connect/event";

    public IotConnectEventListener() {
        super(TOPIC, AWSIotQos.QOS1);
    }

    @Override
    public void onMessage(AWSIotMessage message) {
        String content = message.getStringPayload();
        log.info("设备上下线通知: {}", content);
        // 业务处理

    }
}
