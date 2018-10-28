package com.ljq.demo.common.config;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {com.ljq.demo.common.config.PersonPropertiesConfig.class})
@ContextConfiguration
public class PersonPropertiesConfigTest {

    @Autowired
    private PersonPropertiesConfig personPropertiesConfig;

    @Test
    public void readPersonConfigTest(){

        System.out.println(personPropertiesConfig);

    }

}