package com.ljq.demo.springboot.aws.iot.device.util;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.amazonaws.services.iot.client.AWSIotQos;
import com.amazonaws.services.iot.client.auth.Credentials;
import com.amazonaws.services.iot.client.auth.StaticCredentialsProvider;
import com.ljq.demo.springboot.aws.iot.common.config.AwsIotAccountConfig;
import com.ljq.demo.springboot.aws.iot.device.pubsub.IotConnectEventListener;
import com.ljq.demo.springboot.aws.iot.device.pubsub.IotDemoListener;
import com.ljq.demo.springboot.aws.iot.device.pubsub.IotPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Description: AWS iot 设备端初始化主题订阅
 * @Author: junqiang.lu
 * @Date: 2022/7/21
 */
@Slf4j
@Component
public class AwsIotDeviceClientComponent implements ApplicationRunner {

    public AwsIotDeviceClientComponent(){
    }

    public static final String CLIENT_ID = "demo-server-202207";

    @Autowired
    private AwsIotAccountConfig iotAccountConfig;

    private AWSIotMqttClient mqttClient;

    @Autowired
    private IotDemoListener iotDemoListener;
    @Autowired
    private IotConnectEventListener iotConnectEventListener;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        mqttClient.connect();
        log.info("aws mqtt server 客户端连接成功");
        // 主题订阅
        mqttClient.subscribe(iotDemoListener, true);
        mqttClient.subscribe(iotConnectEventListener, true);
    }

    @Bean(name = "awsIotMqttClient")
    public AWSIotMqttClient awsIotMqttClient() {
        log.info("创建 awsIotMqttClient bean");
        Credentials credentials = new Credentials(iotAccountConfig.getAccessKeyId(),
                iotAccountConfig.getSecretAccessKey());
        StaticCredentialsProvider credentialsProvider = new StaticCredentialsProvider(credentials);
        mqttClient = new AWSIotMqttClient(iotAccountConfig.getClientEndpoint(), CLIENT_ID, credentialsProvider,
                Regions.CN_NORTHWEST_1.getName());
        return mqttClient;
    }

    /**
     * 消息推送
     * @param topic
     * @param data
     * @throws AWSIotException
     */
    public void pushMessage(String topic, byte[] data) throws AWSIotException {
        log.info("server topic={},>>> {}", topic, new String(data));
        mqttClient.publish(new IotPublisher(topic, AWSIotQos.QOS1, data));
    }



}
