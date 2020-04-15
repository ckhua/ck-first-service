package com.example.demo.document;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 Api configuration
 * Created by Gem1ni on 2019-07-17 017.
 */
@Component
@EnableSwagger2
public class Swagger2 {

    @Value("${swagger.document.title:Swagger2 RESTful Api}")
    private String title;

    @Value("${swagger.document.description:Swagger2 RESTful Api}")
    private String description;

    @Value("${swagger.document.terms.service.url:https://swagger.io/}")
    private String termsOfServiceUrl;

    @Value("${swagger.document.contact.name:Swagger2 Developer}")
    private String name;

    @Value("${swagger.document.contact.url:https://swagger.io/}")
    private String url;

    @Value("${swagger.document.contact.mail:chenkh666@163.com}")
    private String mail;

    @Value("${swagger.document.version:0.0.1}")
    private String version;

    @Value("${swagger.document.base.package:com.example.demo}")
    private String basePackage;

    @Value("${swagger.enable:true}")
    private boolean enable;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(this.enable)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(termsOfServiceUrl)
                .contact(new Contact(name, url, mail))
                .version(version)
                .build();
    }
}
