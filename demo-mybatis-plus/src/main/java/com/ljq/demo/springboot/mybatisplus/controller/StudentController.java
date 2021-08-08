package com.ljq.demo.springboot.mybatisplus.controller;

import com.ljq.demo.springboot.mybatisplus.common.api.ApiResult;
import com.ljq.demo.springboot.mybatisplus.model.entity.StudentPb;
import com.ljq.demo.springboot.mybatisplus.model.param.student.StudentReceiveParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 学生信息控制层
 * @Author: junqiang.lu
 * @Date: 2021/8/8
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/protocol/buffer/student")
@Api(value = "protobuf-学生信息控制层", tags = "protobuf-学生信息控制层")
public class StudentController {

    /**
     * 发送学生信息
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/send", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "发送学生信息(pb)",  notes = "发送学生信息(pb)")
    public ResponseEntity<?> send(HttpServletRequest request) throws Exception{
        // 读取字节流
        byte[] studentBytes = StreamUtils.copyToByteArray(request.getInputStream());
        // protocol buffer 反序列化
        StudentPb.Student student = StudentPb.Student.parseFrom(studentBytes);
        // 读取属性
        log.info("Student info,id: {}, name: {}, birthDate: {}", student.getId(), student.getName(),
                student.getBirthDate());
        /**
         * 业务处理
         * ... ...
         */

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(ApiResult.success(), headers, HttpStatus.OK);
    }

    /**
     * 接收学生信息(pb)
     *
     * @param receiveParam
     * @return
     */
    @GetMapping(value = "/receive", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    @ApiOperation(value = "接收学生信息(pb)",  notes = "接收学生信息(pb)")
    public ResponseEntity<?> receive(@Validated StudentReceiveParam receiveParam) throws Exception {
        /**
         * 业务处理
         * ... ...
         */

        // 赋值
        StudentPb.Student student = StudentPb.Student.newBuilder()
                .setId(receiveParam.getId())
                .setName(receiveParam.getName())
                .setBirthDate(receiveParam.getBirthDate())
                .build();
        // 封装成 byte 数组
        byte[] studentBytes = student.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(studentBytes, headers, HttpStatus.OK);
    }

}
