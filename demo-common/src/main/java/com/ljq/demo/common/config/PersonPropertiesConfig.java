package com.ljq.demo.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @Description: person 配置类, properties 格式配置文件读取测试
 * @Author: junqiang.lu
 * @Date: 2018/10/27
 */
@Configuration
@PropertySource(value = "classpath:person.properties")
@Data
public class PersonPropertiesConfig {
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


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

}
