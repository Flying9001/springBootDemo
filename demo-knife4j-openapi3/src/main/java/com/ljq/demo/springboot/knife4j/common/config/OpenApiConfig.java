package com.ljq.demo.springboot.knife4j.common.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: openapi 界面配置
 * @Author: junqiang.lu
 * @Date: 2023/8/15
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Knife4j OpenApi 3")
                        .description("Knife4j OpenApi 3 example application")
                        .version("v1.0")
                        .contact(new Contact().name("Flying9001").url("https://github.com/Flying9001")))
                .externalDocs(new ExternalDocumentation()
                        .description("Github example code")
                        .url("https://github.com/Flying9001/springBootDemo/demo-knife4j-openapi3"));
    }

    
}
