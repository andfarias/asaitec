package com.andersonfariasdev.asaitec.config.swagger;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.andersonfariasdev.asaitec.controller"))
                .paths(Predicates.not(
                        PathSelectors.ant("/info")))
                .paths(Predicates.not(
                        PathSelectors.ant("/")))
                .build()
                .useDefaultResponseMessages(false)
                .ignoredParameterTypes(ServletWebRequest.class)
                .globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
                .globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
                .globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessages())
                .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
                .apiInfo(info())
                .tags(
                        new Tag("asaitec", "Asaitec project")
                );
    }

    private ApiInfo info() {
        return new ApiInfoBuilder()
                .title("Asaitec API")
                .description("Asaitec API")
                .version("1.0.0")
                .license("Asaitec")
                .licenseUrl("https://www.asaitec.eu/")
                .build();
    }

    private List<ResponseMessage> globalGetResponseMessages() {
        return Arrays.asList(
                new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Internal Server Error").build(),
                new ResponseMessageBuilder().code(HttpStatus.NOT_ACCEPTABLE.value())
                        .message("Resource has no representation that can be accepted by the consumer").build());
    }

    private List<ResponseMessage> globalPostPutResponseMessages() {
        return Arrays.asList(
                new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value())
                        .message("Invalid request (client error)").build(),
                new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Internal server error").build(),
                new ResponseMessageBuilder().code(HttpStatus.NOT_ACCEPTABLE.value())
                        .message("Resource has no representation that could be accepted by the consumer").build(),
                new ResponseMessageBuilder().code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                        .message("Request refused because the body is in an unsupported format").build());
    }

    private List<ResponseMessage> globalDeleteResponseMessages() {
        return Arrays.asList(
                new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value())
                        .message("Invalid request (client error)").build(),
                new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Internal server error").build());
    }

}
