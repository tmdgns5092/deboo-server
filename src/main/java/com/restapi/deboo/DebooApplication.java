package com.restapi.deboo;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@EnableEncryptableProperties
@SpringBootApplication
public class DebooApplication {
    public static void main(String[] args) {
        SpringApplication.run(DebooApplication.class, args);
    }
}
