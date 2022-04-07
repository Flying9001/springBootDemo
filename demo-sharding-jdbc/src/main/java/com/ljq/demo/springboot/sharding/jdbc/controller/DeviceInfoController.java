package com.ljq.demo.springboot.sharding.jdbc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljq.demo.springboot.sharding.jdbc.model.entity.DeviceInfoEntity;
import com.ljq.demo.springboot.sharding.jdbc.model.param.DeviceInfoAddParam;
import com.ljq.demo.springboot.sharding.jdbc.model.param.DeviceInfoQueryOneParam;
import com.ljq.demo.springboot.sharding.jdbc.model.param.DeviceInfoQueryPageParam;
import com.ljq.demo.springboot.sharding.jdbc.service.DeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 设备信息控制层
 * @Author: junqiang.lu
 * @Date: 2022/4/2
 */
@RestController
@RequestMapping(value = "/api/sharding/jdbc/device")
public class DeviceInfoController {

    @Autowired
    private DeviceInfoService deviceInfoService;

    /**
     * 设备信息新增单条
     *
     * @param addParam
     * @return
     */
    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DeviceInfoEntity> add(@RequestBody DeviceInfoAddParam addParam) {
        return ResponseEntity.ok(deviceInfoService.add(addParam));
    }

    /**
     * 设备信息查询单条
     *
     * @param queryOneParam
     * @return
     */
    @GetMapping(value = "/query/one", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DeviceInfoEntity> queryOne(DeviceInfoQueryOneParam queryOneParam) {
        return ResponseEntity.ok(deviceInfoService.queryOne(queryOneParam));
    }

    /**
     * 设备信息分页查询
     *
     * @param queryPageParam
     * @return
     */
    @GetMapping(value = "/query/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<IPage<DeviceInfoEntity>> queryPage(DeviceInfoQueryPageParam queryPageParam) {
        return ResponseEntity.ok(deviceInfoService.queryPage(queryPageParam));
    }

}
