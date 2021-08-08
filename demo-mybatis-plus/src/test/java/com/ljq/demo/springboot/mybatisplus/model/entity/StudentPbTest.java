package com.ljq.demo.springboot.mybatisplus.model.entity;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.ljq.demo.springboot.mybatisplus.model.param.student.StudentReceiveParam;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StudentPbTest {


    /**
     * 测试-赋值与取值
     */
    @Test
    public void value() {
        // 赋值
        StudentPb.Student student = StudentPb.Student.newBuilder()
                .setId(111L)
                .setName("张三")
                .setBirthDate(1628233076)
                .build();
        System.out.println(student.toString());
        // 测试结果:
//        id: 111
//        name: "\345\274\240\344\270\211"
//        birthDate: 1628233076

        // 测试结论: 字符串的 toString 为编码

        // 取值
        System.out.println("id: " + student.getId());
        System.out.println("name: " + student.getName());
        System.out.println("birthDate: " + student.getBirthDate());
        // 测试结果:
//        id: 111
//        name: 张三
//        birthDate: 1628233076

        // 测试结论: 逐个属性取值不存在编码问题

        // BeanUtil 属性赋值测试
        StudentReceiveParam receiveParam = new StudentReceiveParam();
        BeanUtil.copyProperties(student, receiveParam, CopyOptions.create().ignoreError().ignoreNullValue());
        System.out.println(receiveParam);
        // 测试结果:
//        StudentReceiveParam(id=null, name=null, birthDate=null)

        // 测试结论: Protobuf 生成的 java 类无法使用 beanUtil 进行属性复制
    }

    /**
     * 测试-数据转换
     */
    @Test
    public void convert() throws IOException {
        StudentPb.Student student = StudentPb.Student.newBuilder()
                .setId(111L)
                .setName("张三")
                .setBirthDate(1628233076).build();
        // 转化为字节数组
        byte[] bytes = student.toByteArray();

        // 写入本地
        String localPath = "/Users/ljq/Downloads/student.bin";
        Files.write(Paths.get(localPath), bytes);

        // 从流中读取数据
        byte[] readBytes = Files.readAllBytes(Paths.get(localPath));
        StudentPb.Student readStudent = StudentPb.Student.parseFrom(readBytes);
        // 取值
        System.out.println("id: " + readStudent.getId());
        System.out.println("name: " + readStudent.getName());
        System.out.println("birthDate: " + readStudent.getBirthDate());
    }

    /**
     * 测试-列表字段
     */
    @Test
    public void list() {
        // 赋值
        StudentPb.ElectiveCourse electiveCourse = StudentPb.ElectiveCourse.newBuilder()
                .setStuId(6L)
                .addCourseName("物理")
                .addCourseName("化学")
                .addCourseName("生物")
                .build();
        // 取值
        System.out.println("Student id: " + electiveCourse.getStuId());
        List<String> courseNameList = electiveCourse.getCourseNameList();
        System.out.println("elective course: ");
        courseNameList.stream().forEach(s -> System.out.println(s));

    }

    /**
     * 测试-字段为对象
     */
    @Test
    public void object() {
        // 赋值
        StudentPb.StudentClass studentClass = StudentPb.StudentClass.newBuilder()
                .setId(7L)
                .setStudent(StudentPb.Student.newBuilder()
                        .setId(123L)
                        .setName("德玛西亚")
                        .setBirthDate(1628233076).build())
                .setClassInfo(StudentPb.ClassInfo.newBuilder()
                        .setId(456L)
                        .setGrade(3)
                        .setNumber(2).build())
                .build();
        // 取值
        System.out.println("id: " + studentClass.getId());
        // 学生信息
        System.out.println("student id: " + studentClass.getStudent().getId());
        System.out.println("student name: " + studentClass.getStudent().getName());
        System.out.println("student birthDate: " + studentClass.getStudent().getBirthDate());
        // 班级信息
        System.out.println("class id: " + studentClass.getClassInfo().getId());
        System.out.println("class grade: " + studentClass.getClassInfo().getGrade());
        System.out.println("class number: " + studentClass.getClassInfo().getNumber());

    }

    /**
     * 测试-枚举类
     */
    @Test
    public void enums() {
        StudentPb.StudentSex studentSex = StudentPb.StudentSex.newBuilder()
                .setSex(StudentPb.StudentSex.Sex.MAN)
                .build();
        // 取值
        System.out.println("student sex: " + studentSex.getSex());
        System.out.println("student sex value: " + studentSex.getSexValue());
        // 测试结果
//        student sex: MAN
//        student sex value: 0

        // 测试结论: 直接获取枚举类，返回的是枚举对象/字符串;获取枚举类的值，返回的是枚举类中对应的属性值
        //  protobuf 在生成枚举类的时候会默认给每一个枚举值赋值为整数,从 0 开始



    }

}