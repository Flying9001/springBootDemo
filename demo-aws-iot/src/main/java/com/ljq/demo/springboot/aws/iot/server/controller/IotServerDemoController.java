package com.ljq.demo.springboot.aws.iot.server.controller;

import com.ljq.demo.springboot.aws.iot.server.param.*;
import com.ljq.demo.springboot.aws.iot.server.util.AwsIotServerClientComponent;
import com.ljq.demo.springboot.aws.iot.server.vo.CertificateVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: iot 服务端示例控制层
 * @Author: junqiang.lu
 * @Date: 2022/7/25
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/iot/server")
public class IotServerDemoController {

    @Autowired
    private AwsIotServerClientComponent iotClientComponent;

    /**
     * 创建策略
     *
     * @param createPolicyParam
     * @return
     */
    @PostMapping(value = "/policy/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> createPolicy(@RequestBody IotServerCreatePolicyParam createPolicyParam) {
        boolean flag = iotClientComponent.createPolicy(createPolicyParam.getPolicyName(),
                createPolicyParam.getPolicyContent());
        return ResponseEntity.ok(flag);
    }

    /**
     * 更新策略
     *
     * @param updatePolicyParam
     * @return
     */
    @PutMapping(value = "/policy/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> updatePolicy(@RequestBody IotServerUpdatePolicyParam updatePolicyParam) {
        boolean flag = iotClientComponent.updatePolicy(updatePolicyParam.getPolicyName(),
                updatePolicyParam.getPolicyContent());
        return ResponseEntity.ok(flag);
    }

    /**
     * 创建物品类型
     *
     * @param createThingTypeParam
     * @return
     */
    @PostMapping(value = "/thingType/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> createThingType(@RequestBody IotServerCreateThingTypeParam createThingTypeParam) {
        boolean flag = iotClientComponent.createThingType(createThingTypeParam.getThingTypeName());
        return ResponseEntity.ok(flag);
    }

    /**
     * 创建物品
     *
     * @param createThingParam
     * @return
     */
    @PostMapping(value = "/thing/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> createThing(@RequestBody IotServerCreateThingParam createThingParam) {
        boolean flag = iotClientComponent.createThing(createThingParam.getThingName(),
                createThingParam.getThingTypeName());
        return ResponseEntity.ok(flag);
    }

    /**
     * 创建证书
     *
     * @param createCertParam
     * @return
     */
    @PostMapping(value = "/cert/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> createCert(@RequestBody IotServerCreateCertParam createCertParam) {
        CertificateVo cert = iotClientComponent.createCert();
        return ResponseEntity.ok(cert);
    }

    /**
     * 绑定物品与证书
     *
     * @param bindThingAndCertParam
     * @return
     */
    @PostMapping(value = "/thing/cert/bind", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> bindThingAndCert(@RequestBody IotServerBindThingAndCertParam bindThingAndCertParam) {
        boolean flag = iotClientComponent.bindThingAndCert(bindThingAndCertParam.getCertArn(),
                bindThingAndCertParam.getThingName());
        return ResponseEntity.ok(flag);
    }

    /**
     * 绑定证书与策略
     *
     * @param bindCertAndPolicyParam
     * @return
     */
    @PostMapping(value = "/cert/policy/bind", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> bindCertAndPolicy(@RequestBody IotServerBindCertAndPolicyParam bindCertAndPolicyParam) {
        boolean flag = iotClientComponent.bindCertAndPolicy(bindCertAndPolicyParam.getCertArn(),
                bindCertAndPolicyParam.getPolicyName());
        return ResponseEntity.ok(flag);
    }









}
