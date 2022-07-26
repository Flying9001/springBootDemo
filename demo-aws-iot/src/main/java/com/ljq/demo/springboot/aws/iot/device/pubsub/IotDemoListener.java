package com.ljq.demo.springboot.aws.iot.device.pubsub;

import com.amazonaws.services.iot.client.AWSIotMessage;
import com.amazonaws.services.iot.client.AWSIotQos;
import com.amazonaws.services.iot.client.AWSIotTopic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description: AWS iot 主题监听类
 * @Author: junqiang.lu
 * @Date: 2022/7/21
 */
@Slf4j
@Component
public class IotDemoListener extends AWSIotTopic {

    private static final String TOPIC = "hello/demo/xxx";

    public IotDemoListener() {
        super(TOPIC, AWSIotQos.QOS1);
    }

    @Override
    public void onMessage(AWSIotMessage message) {
        String content = message.getStringPayload();
        log.info("message content: {}", content);
        // 业务处理
    }
}
