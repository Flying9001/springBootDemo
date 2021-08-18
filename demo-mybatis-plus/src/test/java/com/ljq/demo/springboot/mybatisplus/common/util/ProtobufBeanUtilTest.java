package com.ljq.demo.springboot.mybatisplus.common.util;

import com.ljq.demo.springboot.mybatisplus.model.entity.StudentPb;
import com.ljq.demo.springboot.mybatisplus.model.param.student.StudentReceiveParam;
import org.junit.Test;

import java.io.IOException;


public class ProtobufBeanUtilTest {

    @Test
    public void toPojoBean() throws IOException {
        // 赋值
        StudentPb.Student student = StudentPb.Student.newBuilder()
                .setId(111L)
                .setName("张三")
                .setBirthDate(1628233076)
                .build();
        // 属性拷贝
        StudentReceiveParam receiveParam = ProtobufBeanUtil.toPojoBean(StudentReceiveParam.class, student);
        System.out.println(receiveParam);
    }

    @Test
    public void toProtoBean() throws IOException {
        StudentReceiveParam receiveParam = new StudentReceiveParam();
        receiveParam.setId(123L);
        receiveParam.setName("李四");
        receiveParam.setBirthDate(1628233076);
        // 属性拷贝
        StudentPb.Student.Builder studentBuilder = StudentPb.Student.newBuilder();
        ProtobufBeanUtil.toProtoBean(studentBuilder, receiveParam);
        // 打印
        System.out.println("id: " + studentBuilder.getId());
        System.out.println("name: " + studentBuilder.getName());
        System.out.println("birth_date: " + studentBuilder.getBirthDate());
    }

    /**
     * proto to pojo 压力测试
     *
     * @throws IOException
     */
    @Test
    public void toPojoBeanPressure() throws IOException {
        // 赋值
        StudentPb.Student student = StudentPb.Student.newBuilder()
                .setId(111L)
                .setName("张三")
                .setBirthDate(1628233076)
                .build();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            StudentReceiveParam receiveParam = ProtobufBeanUtil.toPojoBean(StudentReceiveParam.class, student);
        }
        long stop = System.currentTimeMillis();
        System.out.println("耗时: " + (stop-start));
    }

    /**
     * pojo to proto 压力测试
     */
    @Test
    public void toProtoBeanPressure() throws IOException {
        StudentReceiveParam receiveParam = new StudentReceiveParam();
        receiveParam.setId(123L);
        receiveParam.setName("李四");
        receiveParam.setBirthDate(1628233076);
        // 属性拷贝
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            StudentPb.Student.Builder studentBuilder = StudentPb.Student.newBuilder();
            ProtobufBeanUtil.toProtoBean(studentBuilder, receiveParam);
        }
        long stop = System.currentTimeMillis();
        System.out.println("耗时: " + (stop-start));

    }
}