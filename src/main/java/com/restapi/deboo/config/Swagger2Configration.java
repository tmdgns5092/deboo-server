package com.restapi.deboo.config;

import com.fasterxml.classmate.TypeResolver;
import com.restapi.deboo.entity.RequestEntity;
import com.restapi.deboo.entity.UserEntity;
import com.restapi.deboo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Swagger2Configration {

    private final TypeResolver typeResolver;

    @Autowired
    public Swagger2Configration(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }

    @Autowired
    public void createAdditionalModelDocumentation(Docket docket) {
        docket.additionalModels(
                typeResolver.resolve(UserEntity.class),     typeResolver.resolve(UserEntity.class),
                typeResolver.resolve(RequestEntity.class),  typeResolver.resolve(RequestEntity.class)
        );
    }


    @Bean
    public Docket api() {

        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder.name("X-AUTH-TOKEN")
                .description("Access TOKEN")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();

        List<Parameter> parameter = new ArrayList<>();
        parameter.add(parameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(parameter)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.restapi.deboo"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("대부업체 매칭 플렛폼 build product.")
                .version("1.0")
                .description("SPAC: Spring boot, Spring Security, Gradle, swagger2, JPA, Log4j, Jasypt, JWT \nBACK DOOR ACCESS TOKEN: MVPICK!?")
                .license("License : MVPICK!?")
                .build();
    }
}
