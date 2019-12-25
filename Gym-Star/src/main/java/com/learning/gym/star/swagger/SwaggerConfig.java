package com.learning.gym.star.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    @Autowired
    public Docket swaggerConfiguration () {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo () {
        return new ApiInfo("Gym Starr Application",
                "",
                "",
                "",
                new Contact("Bartek", "someurl", "email"),
                "",
                "",
                new ArrayList <>());
    }
}
