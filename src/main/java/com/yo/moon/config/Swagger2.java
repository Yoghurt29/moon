package com.yo.moon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

  @Bean
  public Docket config() {
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
        .useDefaultResponseMessages(false).select()
        .apis(RequestHandlerSelectors.basePackage("com.yo.moon.controller")).build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("Spring Boot Demo Api")
        .contact(new Contact("Trulon", "http://localhost:8088/v2/api-docs", "-")).build();
  }
}
