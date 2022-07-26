package com.ljq.demo.springboot.aws.iot.device.controller;

import com.amazonaws.services.iot.client.AWSIotException;
import com.ljq.demo.springboot.aws.iot.device.param.IotDevicePublishParam;
import com.ljq.demo.springboot.aws.iot.device.util.AwsIotDeviceClientComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

/**
 * @Description: iot 设备示例控制层
 * @Author: junqiang.lu
 * @Date: 2022/7/22
 */
@RestController
@RequestMapping(value = "/api/iot/device")
public class IotDeviceDemoController {

    @Autowired
    private AwsIotDeviceClientComponent deviceClientComponent;

    @PostMapping(value = "/publish", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> publish(@RequestBody IotDevicePublishParam publishParam) throws AWSIotException {
        String topic = "hello/demo/client/" + publishParam.getClientId();
        deviceClientComponent.pushMessage(topic, publishParam.getMessage().getBytes(StandardCharsets.UTF_8));
        return ResponseEntity.ok(System.currentTimeMillis());
    }

}
