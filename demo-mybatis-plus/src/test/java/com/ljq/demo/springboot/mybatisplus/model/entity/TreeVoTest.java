package com.ljq.demo.springboot.mybatisplus.model.entity;

import com.ljq.demo.springboot.mybatisplus.common.util.ProtobufBeanUtil;
import com.ljq.demo.springboot.mybatisplus.model.param.student.StudentReceiveParam;
import org.junit.Test;

import java.io.IOException;

public class TreeVoTest {

    /**
     * 测试-赋值
     */
    @Test
    public void value() throws IOException {
        TreePb.Tree tree = TreePb.Tree.newBuilder()
                .setTreeType("杨树")
                .setPlantDate(1628233076)
                .setStudent(StudentPb.Student.newBuilder()
                        .setId(123L)
                        .setName("王五")
                        .setBirthDate(1628233076))
                .build();
        // 输出值
        System.out.println("tree type: " + tree.getTreeType());
        System.out.println("plant date: " + tree.getPlantDate());
        System.out.println("student :" + ProtobufBeanUtil.toPojoBean(StudentReceiveParam.class, tree.getStudent()));


    }

}