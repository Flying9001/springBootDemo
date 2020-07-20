package com.ljq.demo.springboot.baseweb.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: person 配置类,yml 配置文件读取测试
 * @Author: junqiang.lu
 * @Date: 2018/10/27
 */
@Configuration
@Data
public class PersonYmlConfig {

    /**
     * 人物姓名
     */
    @Value("${person.name}")
    private String name;

    /**
     * 人物年龄
     */
    @Value("${person.age}")
    private Integer age;

    /**
     * 人物母亲姓名
     */
    @Value("${person.parent.mother.name}")
    private String motherName;

    /**
     * 人物母亲年龄
     */
    @Value("${person.parent.mother.age}")
    private Integer motherAge;
    /**
     * 人物父亲姓名
     */
    @Value("${person.parent.father.name}")
    private String fatherName;

    /**
     * 人物父亲年龄
     */
    @Value("${person.parent.father.age}")
    private Integer fatherAge;


}
