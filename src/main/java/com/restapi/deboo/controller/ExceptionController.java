package com.restapi.deboo.controller;

import io.swagger.annotations.Api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/exception")
@Api(description = "예외처리 컨트롤러", tags = {"exception"})
public class ExceptionController {

    @GetMapping(value = "/entrypoint")
    public void entrypointException() {
        System.out.println("/exception/entrypoint");
//        throw new CAuthenticationEntryPointException();
    }

    @GetMapping(value = "/accessdenied")
    public void accessdeniedException() {
//        throw new CAccessDeniedException("");
        System.out.println("/exception/accessdenied");
    }
}