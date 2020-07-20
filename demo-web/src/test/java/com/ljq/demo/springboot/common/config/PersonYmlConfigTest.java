package com.ljq.demo.springboot.common.config;

import com.ljq.demo.springboot.baseweb.config.PersonYmlConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {com.ljq.demo.springboot.baseweb.config.PersonYmlConfig.class})
@ContextConfiguration
public class PersonYmlConfigTest {

    @Autowired
    private PersonYmlConfig personYmlConfig;

    @Test
    public void readPersonConfigTest(){

        System.out.println("personYmlConfig info: " + personYmlConfig);


    }

}