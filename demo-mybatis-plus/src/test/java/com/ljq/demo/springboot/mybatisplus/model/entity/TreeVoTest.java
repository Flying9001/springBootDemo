package com.ljq.demo.springboot.mybatisplus.model.entity;

import com.ljq.demo.springboot.mybatisplus.common.util.ProtobufBeanUtil;
import com.ljq.demo.springboot.mybatisplus.model.param.student.StudentReceiveParam;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TreeVoTest {

    /**
     * 测试-赋值
     */
    @Test
    public void value() {
        TreePb.Tree tree = TreePb.Tree.newBuilder()
                .setTreeType("杨树")
                .setPlantDate(1628233076)
                .setStudent(StudentPb.Student.newBuilder()
                        .setId(123L)
                        .setName("王五")
                        .setBirthDate(1628233076))
                .setElectiveCourse(StudentPb.ElectiveCourse.newBuilder()
                        .setStuId(123L)
                        .addCourseNames("语文")
                        .addCourseNames("数学")
                        .addCourseNames("物理"))
                .addTeachers(StudentPb.Teacher.newBuilder()
                        .setId(666L)
                        .setName("化学王子秦老师"))
                .addTeachers(StudentPb.Teacher.newBuilder()
                        .setId(888L)
                        .setName("马保国"))
                .build();
        // 输出值
        System.out.println("tree type: " + tree.getTreeType());
        System.out.println("plant date: " + tree.getPlantDate());
        System.out.println("tree protobuf: " + tree);

    }

    @Test
    public void convertToPojo() throws IOException {
        TreePb.Tree tree = TreePb.Tree.newBuilder()
                .setTreeType("杨树")
                .setPlantDate(1628233076)
                .setStudent(StudentPb.Student.newBuilder()
                        .setId(123L)
                        .setName("王五")
                        .setBirthDate(1628233076))
                .setElectiveCourse(StudentPb.ElectiveCourse.newBuilder()
                        .setStuId(123L)
                        .addCourseNames("语文")
                        .addCourseNames("数学")
                        .addCourseNames("物理"))
                .addTeachers(StudentPb.Teacher.newBuilder()
                        .setId(666L)
                        .setName("化学王子秦老师"))
                .addTeachers(StudentPb.Teacher.newBuilder()
                        .setId(888L)
                        .setName("马保国"))
                .build();
        TreeEntity treeEntity = ProtobufBeanUtil.toPojoBean(TreeEntity.class, tree);
        System.out.println("tree entity: " + treeEntity);

    }

    @Test
    public void convertToProtobuf() throws IOException {
        List<String> courseNameList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            courseNameList.add("科目" + (i+1));
        }
        List<TeacherEntity> teacherList = new ArrayList<>();
        TeacherEntity teacher;
        for (int i = 0; i < 3; i++) {
            teacher = new TeacherEntity();
            teacher.setId((i+1L));
            teacher.setName("老师" + (i+1));
            teacherList.add(teacher);
        }
        TreeEntity treeEntity = TreeEntity.builder()
                .treeType("梧桐树")
                .plantDate(1628233076)
                .student(StudentReceiveParam.builder()
                        .id(1234L)
                        .name("张三")
                        .birthDate(1628233076)
                        .build())
                .electiveCourse(ElectiveCourseEntity.builder()
                        .stuId(1234L)
                        .courseNames(courseNameList)
                        .build())
                .teachers(teacherList)
                .build();
        // 树木信息
        System.out.println("TreeEntity: " + treeEntity);
        // pojoBean -> protobuf
        TreePb.Tree.Builder tree = TreePb.Tree.newBuilder();
        ProtobufBeanUtil.toProtoBean(tree, treeEntity);
        System.out.println("tree protobuf:" + tree.build());


    }

}