package com.ljq.demo.springboot.websocketspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author junqiang.lu
 */
@SpringBootApplication(scanBasePackages = {"com.ljq.demo.springboot"})
public class DemoWebsocketSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoWebsocketSpringApplication.class, args);
    }

}
