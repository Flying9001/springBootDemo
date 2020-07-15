package com.ljq.demo.springboot.activiti;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author junqiang.lu
 */
@EnableSwagger2
@MapperScan("com.ljq.demo.springboot.activiti.dao")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ActivitiWorkflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivitiWorkflowApplication.class, args);

    }

}
