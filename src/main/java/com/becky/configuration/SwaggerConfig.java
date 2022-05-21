package com.becky.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerConfig {
  @Bean
  public Docket docket(){
    return new Docket(DocumentationType.OAS_30)
        .apiInfo(apiInfo()).enable(true)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.becky"))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo apiInfo(){
    return new ApiInfoBuilder()
        .title("CBound 项目接口文档")
        .description("For Becky")
        .contact(new Contact("wang", "url", "Email"))
        .version("1.0")
        .build();
  }
}

