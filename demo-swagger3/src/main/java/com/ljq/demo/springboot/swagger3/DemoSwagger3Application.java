package com.ljq.demo.springboot.swagger3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author junqiang.lu
 */
@SpringBootApplication(scanBasePackages = {"com.ljq.demo.springboot"})
public class DemoSwagger3Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoSwagger3Application.class, args);
        System.out.println("----------Swagger3 demo project starting -----------------");
    }

    

}
